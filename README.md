# skeleton-spring-boot-java

## TODO残
- application.ymlでほかのプロパティとれない
- cognitoログイン設定

## アーキテクト残
- app
  - [ ] spring mvc
    - [x] 存在しないURLで404+json
    - [ ] validation errorで400
      - [ ] query param
        - [x] パラメータ名が正しいが、型がおかしい
        - [x] パラメータ名の指定間違い/指定無し
        - [ ] エラーメッセージをコントロールする
      - [ ] post param
  - [ ] spring security
    - [ ] OAuth2
      - [ ] amazon cognito
        - [ ] 登録API
        - [ ] 削除API
  - [x] spring session
    - [x] redis
  - [x] logger
    - [x] log設定
      - [x] 標準出力(STDOUT)
      - [x] エラー出力(STDERR)
      - [x] bootRun(bootRun/mac)はdebug
      - [x] compose(dev/mac)はdebug
      - [x] compose(prd/ecs)はinfo
  - [ ] swagger
    - [ ] swagger specification v3
  - [ ] test
    - [ ] 個別にcomponent scan指定？
    - [x] redis接続しない
- infra
  - [x] bootRun
    - [x] redisの初期化が動いて落ちる
      - [x] profileによって、redisのコンポーネント初期化をやめる
  - [x] docker compose
    - [x] localだけ
    - [x] redis+java
    - [x] 環境変数をわたして起動わけする
  - [x] GitHub Actions
    - [x] なんか動いた
    - [x] 全てでビルド && テスト
    - [x] masterだけdocker build && ECR push && デプロイ
    - [x] キャッシュ利かす  
    https://help.github.com/ja/actions/automating-your-workflow-with-github-actions/caching-dependencies-to-speed-up-workflows
    - [x] artifacts利かす  
    https://help.github.com/ja/actions/automating-your-workflow-with-github-actions/persisting-workflow-data-using-artifacts
  - [ ] ECS
    - [x] うごかす
    - [ ] 環境わける
  - [ ] cdk
    - [ ] cognito
    - [ ] ecs
    - [ ] s3
  - [ ] swagger
    - [ ] swagger ui + s3?

## develop
### build
`./gradlew build`

### docker-compose
#### init env
`touch .env`

```
SPRING_PROFILE=dev
AWS_COGNITO_POOL_ID=...
```

#### up
`make docker-compose-up`

#### down
Ctrl+C && `make docker-compose-down`

## Reference Documentation
### spring-framework
- https://docs.spring.io/spring/docs/5.1.9.RELEASE/spring-framework-reference
- https://docs.spring.io/spring/docs/5.1.9.RELEASE/spring-framework-reference/core.html#spring-core

### spring-boot
- https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/htmlsingle/#boot-features-external-config
- https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/htmlsingle/#boot-features-security
- https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/htmlsingle/#boot-features-security-oauth2
- https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications

### spring-security
- https://docs.spring.io/spring-security/site/docs/5.1.6.RELEASE/reference/htmlsingle/

### GitHub Actions
- https://github.com/marketplace?type=actions
- https://github.com/actions/checkout
- https://github.com/actions/setup-java
- https://github.com/actions/cache
- https://github.com/actions/upload-artifact
- https://github.com/actions/download-artifact
- https://github.com/aws-actions/configure-aws-credentials
- https://github.com/aws-actions/amazon-ecr-login
- https://github.com/aws-actions/amazon-ecs-render-task-definition
- https://github.com/aws-actions/amazon-ecs-deploy-task-definition
