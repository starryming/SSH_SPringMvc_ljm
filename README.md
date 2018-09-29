# SSH_SpringMvc_ljm
### 1. 引入Jwt，进行授权验证

-  需要依赖
```
<!--一款开源的成熟的JSON WEB TOKEN 解决方法-->
        <dependency>
            <groupId>com.nimbusds</groupId>
            <artifactId>nimbus-jose-jwt</artifactId>
            <version>4.28</version>
        </dependency>
<!--Jwt-->
```
-  结构问题
```
└── src
    └── main
        |── java.com.learn
        |   |──  aspect (日志整合）
		|	|──  controller）
        |   |──  dao
        |   |    └── impl
        |   |    └── Support（支持接口实现类继承）
        |   |──  entity
        |   |    └── extend（实体继承）
        |   |──  service
        |   |    └── impl
        |   |──  jwt（Jwt整合）
        |   └──  untils（工具类）
        |
        ├── junit4（测试）
        |
        ├── resources（配置文件）
        |   |── properities（数据库）
        |   └──
        └── webapp
            └── views（视图层）
```