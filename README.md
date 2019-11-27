# skeleton-spring-boot-java

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
  - [ ] logger
    - [ ] log設定
      - [ ] 標準出力(STD) or エラー出力(STD_ERR)
      - [x] bootRun(bootRun/mac)はdebug
      - [x] compose(dev/mac)はdebug
      - [ ] compose(prd/ecs)はinfo
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
  - [ ] GitHub Actions
    - [x] なんか動いた
    - [ ] 全てでビルド && テスト
    - [ ] masterだけECRにpush && デプロイ
  - [ ] ECS
    - [ ] うごかす
  - [ ] cdk
    - [ ] cognito
    - [ ] ecs
    - [ ] s3
  - [ ] swagger
    - [ ] swagger ui + s3?

## TODO残
- application.ymlでほかのプロパティとれない
- cognitoログイン設定

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

