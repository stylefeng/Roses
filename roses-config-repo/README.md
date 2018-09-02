# roses-config-repo 配置仓库（非分布式配置中心服务）

---
   
## 介绍
本项目为Roses系列微服务框架的模块之一，Roses基于`Spring Boot 2`和`Spring Cloud Finchley.RELEASE`，致力做更简洁的**分布式**和**服务化**解决方案，Roses拥有高效率的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace）等等，**一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战**，请关注Roses微服务框架[https://gitee.com/naan1993/roses](https://gitee.com/naan1993/roses)

---

## 本项目模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| roses-cloud-register | 注册中心配置 | 无 | 无 |
| roses-gateway | 网关配置 | 无 | 无 |
| roses-spring-boot-admin | 监控中心配置 | 无 | 无 |
| roses-system | 系统管理服务配置 | 无 | 无 |

---

## 注意事项

> * 为保证服务正常运行，请仔细检查各个配置文件的配置是否和您的环境一致，例如数据库配置，kafka配置，oss配置等
> * 请保证各个配置文件的名称规则为`应用名称-profile.yml`，应用名称为`spring.application.name`的名字，`profile`为spring的profile的名称

---

## 项目特点

配置仓库的建立，便于集中维护各个微服务以及中间件的配置，让开发人员更加注重业务的编写，而不必去考虑项目的配置，也不会因为一个简单的配置的修改而通知各个服务的管理员作相应项目配置的变更。