# roses-cloud-support 微服务支撑组件

---
   
## 介绍
本项目为Roses系列微服务框架的模块之一，Roses基于`Spring Boot 2`和`Spring Cloud Finchley.RELEASE`，致力做更简洁的**分布式**和**服务化**解决方案，Roses拥有高效率的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace）等等，**一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战**，请关注Roses微服务框架[https://gitee.com/naan1993/roses](https://gitee.com/naan1993/roses)

---

## 本项目模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| roses-cloud-register | 注册中心 | 8761 | eureka注册中心 |
| roses-config-server | 分布式配置中心 | 8002 | 拉取远程仓库配置并为各个微服务提供配置 |
| roses-spring-boot-admin | 监控中心 | 9000 | 监控程序是否正常启动或者挂掉 |

---

## 注意事项

> * 开发环境为jdk 1.8
> * maven推荐使用阿里云镜像，拉取jar包保证成功
> * 确保能连接上配置仓库，例如项目默认的https://gitee.com/stylefeng-Roses/roses-config-repo.git
> * 确保配置仓库中的配置没问题，您可以fork一下我的配置仓库，并可以参考我的配置https://gitee.com/stylefeng-Roses/roses-config-repo
> * 您可以把roses-config-server配置仓库换位您的配置仓库

---

## 启动步骤

> 1. 启动roses-config-server，直接运行RosesConfigServerApplication的main方法
> 2. 启动roses-cloud-register，直接运行RegisterApplication的main方法
> 3. 启动roses-spring-boot-admin，直接运行RosesSpringBootAdminApplication的main方法

---

## 如何使用

#### roses-config-server

roses-config-server为分布式配置中心，为了集中管理各个微服务的配置，启动后可根据如下表达式url查看配置仓库的配置,label为git仓库的分支名称
```
/{application}/{profile}/{label}
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

例如，您可以访问`http://localhost:8002/roses-system-local.yml`，即可获取roses-system的local profile的配置，如下
![输入图片说明](https://images.gitee.com/uploads/images/2018/0829/215558_92472316_551203.png "屏幕截图.png")

#### roses-cloud-register

roses-cloud-register为注册中心，微服务服务注册和发现的根本，当项目启动成功后可访问`http://localhost:8761/`查看运行情况，若可以访问则说明启动成功，如下
![注册中心](https://images.gitee.com/uploads/images/2018/0829/214759_824bef5d_551203.png "屏幕截图.png")

#### roses-spring-boot-admin

roses-spring-boot-admin为分布式监控中心，启动后访问`http://localhost:9000`，即可查看各个微服务的运行状态，环境变量，线程，内存占用率等信息，如下
![监控中心1](https://images.gitee.com/uploads/images/2018/0829/220005_38cdd5c9_551203.png "屏幕截图.png")
![监控中心2](https://images.gitee.com/uploads/images/2018/0829/215925_5605f5ff_551203.png "屏幕截图.png")
![监控中心3](https://images.gitee.com/uploads/images/2018/0829/220048_698acc8d_551203.png "屏幕截图.png")