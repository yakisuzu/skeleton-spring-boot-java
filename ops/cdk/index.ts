#!/usr/bin/env node
import * as cdk from '@aws-cdk/core'
import { SampleAppStack } from './lib/sample-app-stack'
import { EcsSpringTest } from './lib/ecs-spring-test'

const state = process.env.APP_STAGE || 'dev'
const stageCamel = (() => {
  switch (state) {
    case '':
      return ''
    default:
      return ''
  }
})()

const app = new cdk.App()
new SampleAppStack(app, 'SampleAppStack')
new EcsSpringTest(app, `Spring${state}`)
