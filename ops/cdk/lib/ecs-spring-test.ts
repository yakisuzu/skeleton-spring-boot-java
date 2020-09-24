import * as cdk from '@aws-cdk/core'
import * as ecs from '@aws-cdk/aws-ecs'
import * as ecr from '@aws-cdk/aws-ecr'
import * as ec2 from '@aws-cdk/aws-ec2'
import * as as from '@aws-cdk/aws-autoscaling'
import * as iam from '@aws-cdk/aws-iam'
import * as logs from '@aws-cdk/aws-logs'

export class EcsSpringTest extends cdk.Stack {
  constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
    super(scope, id, props)
    // TODO cdkない？
    const keyPairName = 'spring-test'
    // TODO subnet vpcに2つ？
    // TODO ALB - 80をtgへ
    // TODO target group + health check
    // TODO security group private + public

    const vpc = new ec2.Vpc(this, `${id}-vpc`, {
      cidr: ec2.Vpc.DEFAULT_CIDR_RANGE,
      enableDnsHostnames: true,
      enableDnsSupport: true,
      defaultInstanceTenancy: ec2.DefaultInstanceTenancy.DEFAULT,
      maxAzs: 3,
      natGateways: 1,
      natGatewaySubnets: undefined,
      natGatewayProvider: ec2.NatProvider.gateway(),
      subnetConfiguration: undefined,
      vpnGateway: true,
      vpnGatewayAsn: undefined,
      vpnConnections: {},
      vpnRoutePropagation: undefined,
      gatewayEndpoints: {},
    })

    const clusterCapacity: ecs.AddCapacityOptions = {
      instanceType: ec2.InstanceType.of(ec2.InstanceClass.T2, ec2.InstanceSize.MICRO),
      machineImage: new ec2.AmazonLinuxImage({
        generation: ec2.AmazonLinuxGeneration.AMAZON_LINUX_2,
        edition: ec2.AmazonLinuxEdition.STANDARD,
        virtualization: ec2.AmazonLinuxVirt.HVM,
        storage: ec2.AmazonLinuxStorage.GENERAL_PURPOSE,
        userData: ec2.UserData.forLinux({
          shebang: '#!/bin/bash',
        }),
      }),
      // AddAutoScalingGroupCapacityOptions
      canContainersAccessInstanceRole: false,
      taskDrainTime: cdk.Duration.minutes(0), // 無効
      spotInstanceDraining: false,
      // CommonAutoScalingGroupProps
      minCapacity: 0,
      maxCapacity: 1,
      // desiredCapacity: 1, // デフォルトインスタンス数 非推奨
      keyName: keyPairName,
      vpcSubnets: undefined,
      notificationsTopic: undefined,
      allowAllOutbound: true,
      updateType: as.UpdateType.NONE,
      rollingUpdateConfiguration: {
        maxBatchSize: 1,
        minInstancesInService: 0,
        minSuccessfulInstancesPercent: 100,
        pauseTime: cdk.Duration.minutes(5),
        waitOnResourceSignals: true,
        suspendProcesses: [
          as.ScalingProcess.HEALTH_CHECK,
          as.ScalingProcess.REPLACE_UNHEALTHY,
          as.ScalingProcess.AZ_REBALANCE,
          as.ScalingProcess.ALARM_NOTIFICATION,
          as.ScalingProcess.SCHEDULED_ACTIONS,
        ],
      },
      replacingUpdateMinSuccessfulInstancesPercent: 100,
      ignoreUnmodifiedSizeProperties: true,
      resourceSignalCount: 1,
      resourceSignalTimeout: cdk.Duration.minutes(5),
      cooldown: cdk.Duration.minutes(5),
      associatePublicIpAddress: undefined,
      spotPrice: undefined,
      healthCheck: as.HealthCheck.ec2({
        grace: cdk.Duration.seconds(0),
      }),
    }

    const cluster = new ecs.Cluster(this, `${id}-cluster`, {
      clusterName: `${id}-cluster-name`,
      vpc,
      defaultCloudMapNamespace: undefined,
      capacity: clusterCapacity,
    })

    const ciRole: iam.IRole = new iam.Role(this, 'ci-role', {
      assumedBy: new iam.AnyPrincipal(),
      externalId: undefined,
      externalIds: undefined,
      managedPolicies: [iam.ManagedPolicy.fromAwsManagedPolicyName('AdministratorAccess')], // TODO
      inlinePolicies: {},
      path: '/',
      permissionsBoundary: undefined,
      roleName: 'ci-role-name',
      maxSessionDuration: cdk.Duration.hours(1),
    })

    const appRole: iam.IRole = new iam.Role(this, 'app-role', {
      assumedBy: new iam.AnyPrincipal(),
      externalId: undefined,
      externalIds: undefined,
      managedPolicies: [], // TODO
      inlinePolicies: {},
      path: '/',
      permissionsBoundary: undefined,
      roleName: 'app-role-name',
      maxSessionDuration: cdk.Duration.hours(1),
    })

    const taskDefinition = new ecs.Ec2TaskDefinition(this, `${id}-task`, {
      networkMode: ecs.NetworkMode.BRIDGE,
      placementConstraints: [],
      // CommonTaskDefinitionProps
      family: `${id}-task-name`,
      executionRole: ciRole,
      taskRole: appRole,
      proxyConfiguration: undefined,
      volumes: [],
    })

    const repo = new ecr.Repository(this, `${id}-repo-app`, {
      repositoryName: `${id}-repo-app-name`,
      lifecycleRules: [],
      lifecycleRegistryId: undefined,
      removalPolicy: cdk.RemovalPolicy.RETAIN,
    })

    const logGroup = new logs.LogGroup(this, `${id}-log-app`, {
      logGroupName: `${id}-log-app-name`,
      retention: logs.RetentionDays.INFINITE,
      removalPolicy: cdk.RemovalPolicy.RETAIN,
    })

    taskDefinition.addContainer(`${id}-container-app`, {
      image: ecs.ContainerImage.fromEcrRepository(repo),
      command: undefined,
      cpu: undefined,
      disableNetworking: false,
      dnsSearchDomains: [],
      dnsServers: [],
      dockerLabels: {},
      dockerSecurityOptions: [],
      entryPoint: undefined,
      environment: {},
      secrets: {},
      startTimeout: undefined,
      stopTimeout: undefined,
      essential: true,
      extraHosts: {},
      healthCheck: {
        command: ['CMD-SHELL', 'curl -f http://localhost/ || exit 1'],
        interval: cdk.Duration.seconds(30),
        retries: 3,
        startPeriod: undefined, // TODO
        timeout: cdk.Duration.seconds(5),
      },
      hostname: undefined,
      memoryLimitMiB: 512,
      // memoryReservationMiB: undefined, // memoryLimitMiB or memoryReservationMiB
      privileged: false,
      readonlyRootFilesystem: false,
      user: 'root',
      workingDirectory: '/',
      logging: new ecs.AwsLogDriver({
        streamPrefix: `ecs/${id}/app`,
        logGroup: logGroup,
        // logRetention: undefined, // logRetention or logGroup
        datetimeFormat: undefined,
        multilinePattern: undefined,
      }),
      linuxParameters: new ecs.LinuxParameters(this, `${id}-linux-param-app`, {
        initProcessEnabled: false,
        sharedMemorySize: undefined,
      }),
      gpuCount: undefined,
    })

    new ecs.Ec2Service(this, `${id}-service-app`, {
      taskDefinition,
      assignPublicIp: undefined,
      vpcSubnets: undefined,
      securityGroup: undefined,
      placementConstraints: [],
      placementStrategies: [
        ecs.PlacementStrategy.spreadAcross(ecs.BuiltInAttributes.INSTANCE_ID),
        ecs.PlacementStrategy.spreadAcross(ecs.BuiltInAttributes.AVAILABILITY_ZONE),
      ],
      daemon: false,
      propagateTaskTagsFrom: undefined, // 非推奨
      // BaseServiceOptions
      cluster,
      desiredCount: 1, // サービス数
      serviceName: `${id}-service-app-name`,
      maxHealthyPercent: 100,
      minHealthyPercent: 0,
      healthCheckGracePeriod: cdk.Duration.seconds(60),
      cloudMapOptions: undefined,
      propagateTags: ecs.PropagatedTagSource.NONE,
      enableECSManagedTags: false,
      deploymentController: {
        type: ecs.DeploymentControllerType.ECS,
      },
    })
  }
}
