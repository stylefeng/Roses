# roses-system 系统管理服务

---
   
## 介绍
本项目为Roses系列微服务框架的模块之一，Roses基于`Spring Boot 2`和`Spring Cloud Finchley.RELEASE`，致力做更简洁的**分布式**和**服务化**解决方案，Roses拥有高效率的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace）等等，**一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战**，请关注Roses微服务框架[https://gitee.com/naan1993/roses](https://gitee.com/naan1993/roses)

---

## 本项目模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| roses-system-api | system接口 | 无 | 接口 |
| roses-system-app | system微服务 | 8001 | 具体服务 |

---

## 注意事项

> * 开发环境为jdk 1.8
> * maven推荐使用阿里云镜像，拉取jar包保证成功
> * 运行roses-system之前，需要先运行roses-config-server（如果启用分布式配置的话），roses-cloud-register（服务注册发现）
> * 请确数据库url，账号和密码等配置设置正确，如果开启了分布式配置，这些配置都在git配置仓库中，如果没开启请配置本地的application.yml

---

## 启动步骤

> 1. 直接运行SystemApplication的main方法

---

## 项目特点

Roses微服务框架提供了全套基于spring cloud的开发平台，并且roses-kernel内又包含了微服务开发的所有的基本配置，以至于您刚看到roses-system这个项目感觉啥也没有，其实不然。

首先roses-system集成了roses-biz-support（基础业务支撑），详情见[https://gitee.com/stylefeng-Roses/roses-biz-support](https://gitee.com/stylefeng-Roses/roses-biz-support)，所以roses-system包含了字典管理，文件管理，日志管理，这三个业务模块，这三个模块本身带biz-support-dict-api，biz-support-file-api，biz-support-log-api接口，roses-system启动后，其他微服务可引入这三个jar来远程调用文件，字典，日志这三个业务模块。

roses-system也提供了鉴权服务，AuthServiceProvider提供了登陆，退出，校验token和获取当前登陆用户的服务，网关服务roses-gateway就是通过调用roses-system的鉴权服务来实现权限校验的。

另外，在roses-system上开发公司自己的基础服务也很容易，框架上我们依然采用springmvc + mybatis-plus的组合，如果您之前熟悉Guns那么您可以在几分钟之内上手开发，业务的编写还是需要遵循controller，mapper，service三层写法，另外provider包是远程服务提供者的包，远程服务通过feign来调用。

---

## roses-system-api

这是roses-system对其他微服务暴露的远程接口，一般情况其他微服务都要引入这个jar包，具体如下：

roses-system-api提供了获取当期登陆用户的上下文工具类--LoginContext，您可以通过LoginContext.me().getLoginUser();在任何微服务中获取调用当前方法的人是谁（如果这个人登陆的话）

另外，roses-system是基础服务，那么别的微服务更需要集成了，因为基础服务包含了基本的鉴权，文件，和字典操作的一系列方法，这些方法都集成在了roses-system中，别的微服务中您可以很方便的直接使用这些模块。

 