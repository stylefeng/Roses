# roses-kernel 项目骨架，开发利器

---
   
## 介绍
本项目为Roses系列微服务框架的模块之一，Roses基于`Spring Boot 2`和`Spring Cloud Finchley.RELEASE`，致力做更简洁的**分布式**和**服务化**解决方案，Roses拥有高效率的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace）等等，**一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战**，请关注Roses微服务框架[https://gitee.com/naan1993/roses](https://gitee.com/naan1993/roses)

---

## 本项目模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| kernel-actuator | actuator监控 | 无 | 无 |
| kernel-core | 核心包 | 无 | 自动配置和工具类 |
| kernel-generator | 代码生成 | 无 | 根据数据库生成实体，dao，service等 |
| kernel-jwt | jwt模块 | 无 | 实现jwt鉴权 |
| kernel-logger | 日志记录 | 无 | 日志记录工具类 |
| kernel-model | 通用实体 | 无 | 通用实体，接口，枚举，异常规范等 |
| kernel-scanner | 资源扫描 | 无 | 统一搜集各个服务的接口 |
| kernel-sign | 签名模块 | 无 | 实现数据签名 |
| kernel-validator | 校验器 | 无 | 参数校验工具类 |

---

## 注意事项

> * 开发环境为jdk 1.8
> * maven推荐使用阿里云镜像，拉取jar包保证成功
> * （重要）因为目前roses-kernel没有推送到maven中央仓库，所以为了别的项目的正常运行，请mvn install到本地仓库，或者deploy到您的私服中

---

## 项目特点

roses-kernel的定位是项目开发的基础骨架，包含自动配置，工具类封装，代码生成工具，jwt校验等等。需要注意的是，pom中有些依赖是provided，有些jar包还是需要具体应用中具体引入，roses-kernel
不仅仅适用于**微服务的构建**，也同样适用于**单体项目的构建**

### kernel-actuator

当开启spring boot admin，需要引入kernel-actuator模块，这个模块包含了基本的spring 
security的配置，需要注意的是NoneSecurityAutoConfiguration这个自动配置中，spring security的配置为`permitAll()`，会绕过spring 
security的所有拦截器，若需要开启安全验证可参考[http://codecentric.github.io/spring-boot-admin/2.0.1/](http://codecentric.github
.io/spring-boot-admin/2.0.1/)修改相关配置

### kernel-core

项目构架的核心模块，包含常用的自动配置，例如mybatis-plus的自动配置，fastjson，redis，web应用的等等。还有一些上下文工具类，例如RequestNoContext，存放当前请求唯一请求号的工具类，还有数据库初始化的工具类DbInitializer，还有Roses框架自己封装的feign错误解码器，feign的拦截器，构造树的工具类DefaultTreeBuildFactory，另外Roses集成了hutool工具类，还有一些拓展的工具类在com.stylefeng.roses.core.util包中

### kernel-generator

代码生成器模块，简单包装了mybatis-plus的代码生成器，可自动根据数据库表结构自动生成entity，mapper，service，使用时，只需要两部来生成，如下：
```
//初始化参数
GenerateParams generateParams = new GenerateParams();
generateParams.setXXX();
generateParams.setXXX();
generateParams.setXXX();
generateParams.setXXX();

//执行代码生成
SimpleGenerator.doGeneration(generateParams);
```

关于代码生成器各个参数的含义可参考如下：
```
//生成代码里，注释的作者
private String author = "fengshuonan";

//代码生成输出的目录，可为项目路径的相对路径
private String outputDirectory = "temp";

//jdbc驱动
private String jdbcDriver = "com.mysql.jdbc.Driver";

//数据库连接地址
private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=UTC";

//数据库账号
private String jdbcUserName = "root";

//数据库密码
private String jdbcPassword = "root";

//去掉表的前缀
private String[] removeTablePrefix = {"xx_"};

//代码生成包含的表，可为空，为空默认生成所有
private String[] includeTables;

//代码生成的类的父包名称
private String parentPackage = "com.stylefeng.roses.xxx.modular";

//service是否生成接口，这个根据自己项目情况决定
private Boolean generatorInterface = false;
```

### kernel-jwt

jwt模块封装了jwt操作相关的方法，JwtTokenUtil类中包含构造token，获取token中的信息，校验token，查看token是否过期等等操作方法，JwtProperties中包含了jwt
需要的配置，有过期时间和私钥的配置，JwtAutoConfiguration为jwt的自动配置，如果您引入了kernel-jwt模块，那么这个模块也会帮您把配置自动配置好，需要注意的是JwtProperties类的配置映射在jwt
开头的配置文件中，在引入后一定要把这个配置替换掉，例如在application.yml中，改为如下
```
jwt:
  secret: abcdefg
  expiration: 82800
```

### kernel-logger

logger模块主要包含两个功能，一个是调用链治理的日志记录（ChainAop），一个是记录系统里的业务日志和系统日志（LogUtil）。调用链治理ChainOnConsumerAop，ChainOnControllerAop
，ChainOnProviderAop对控制器层，消费者层，提供者层进行日志记录，具体记录了哪些日志，可参考这三个类，也可直接参考RpcPhaseEnum这个枚举，里边记录了所有的日志类型
```
G1,     //网关发送请求(网关前置拦截器)

P1,     //控制器接受到请求(controllerAOP)

P2,     //准备调用远程服务(consumerAOP)

P3,     //被调用端接收到请求(providerAOP)

EP1,    //控制器处理过程中出错(controllerAOP)

EP2,    //feign远程调用，调用方出错(consumerAOP)

EP3,    //feign远程调用，被调用方出错(providerAOP)

G2,     //网关接收到成功请求(网关后置拦截器)

EG2,    //网关接收到错误响应(网关后置拦截器)

TC      //记录请求耗时的类型
```

LogUtil日志记录工具类的使用方法可参考如下：

```
LogUtil.info(String message);

LogUtil.error(String message, Throwable exception);

LogUtil.debug(String message);

LogUtil.trace(String message);

LogUtil.warn(String message);
```

### kernel-model

这个模块包含了整个框架中使用的常量，枚举，异常，分页类，通用model等等。这些类是框架里需要用到的，也可以供业务项目中调用使用。kernel-model中还包含一些接口，例如AuthService和ResourceService
，定义了鉴权和资源收集的方法，还有获取当前登录用户的接口AbstractLoginUser，这些接口在roses-system有默认的实现。还有分页相关的请求和响应model的封装，PageQuery和PageResult。

### kernel-scanner

这个模块是Roses独有的资源扫描工具，Roses中设立了@ApiResource注解，用来标注控制器里的接口，在Roses
框架中，用户对应了角色，角色又对应着资源，菜单关联资源。资源扫描器的作用一方面是便于管理所有的接口资源的权限控制，另一方面是简化了资源录入系统的方式
 （不用手写），当程序启动时，会自动扫描带有@ApiResource注解的方法，扫描之后会对资源进行包装，写入到数据库。所以当使用了资源扫描器之后可以很方便的搜集所有服务上面的接口资源，并通过roses-system
 的接口，统一的汇报到roses-system服务上面去，从而实现了资源的集中管理。
 
 ### kernel-sign
 
 这个模块定义了数据签名的方法和自动配置，通过本模块提供的工具类，可以实现基本的数据签名和校验，方式数据在传输的过程中被别人窃取和篡改。
 
 ### kernel-validator
 
 kernel-validator是一套轻量级的参数校验工具，利用aop拦截，通过在service的方法上加注解@ParamValidator即可实现对请求参数的校验，使用过程中只要把参数继承BaseValidatingParam
 并实现checkParam()方法即可，checkParam()方法返回null表示没有空的值，如果有空的值就返回字符串类型的提示。