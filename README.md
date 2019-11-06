# skeleton-spring-boot-java

## TODO
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
      - [x] bootRun(ローカル)はdebug
      - [ ] compose(本番,ローカル)はinfo
  - [ ] swagger
    - [ ] swagger specification v3
  - [ ] test
    - [ ] 個別にcomponent scan指定？
    - [ ] そもそもデフォルトのyamlで接続先指定するのがおかしい？
- infra
  - [ ] bootRun
    - [ ] redisの初期化が動いて落ちる
      - [ ] profileによって、redisのコンポーネント初期化をやめる？
  - [x] docker compose
    - [x] localだけ
    - [x] redis+java
  - [ ] cdk
    - [ ] cognito
    - [ ] ecs
    - [ ] s3
  - [ ] swagger
    - [ ] swagger ui + s3?


## Reference Documentation
### spring-boot
- https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/htmlsingle/#boot-features-external-config
- https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/htmlsingle/#boot-features-security
- https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/htmlsingle/#boot-features-security-oauth2
- https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications

### spring-security
- https://docs.spring.io/spring-security/site/docs/5.1.6.RELEASE/reference/htmlsingle/
