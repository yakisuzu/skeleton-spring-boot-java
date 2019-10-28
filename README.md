# skeleton-spring-boot-java

## TODO
- app
  - [ ] spring mvc
    - [ ] 存在しないURLで404+json
    - [ ] validation errorで400
      - [ ] query param
        - [x] パラメータ名が正しいが、型がおかしい
        - [ ] パラメータ名の指定間違い/指定無し
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
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Security](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-security)
* [Spring Web Starter](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)
* [MyBatis Framework](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

## Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)

## Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
