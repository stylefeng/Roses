# Roses the next Guns v1.0
[https://gitee.com/naan1993/guns](https://gitee.com/naan1993/guns)

## 介绍
Roses基于Spring Boot, 致力于做更完善的**分布式**和**服务化**解决方案，Roses提供基于Spring Cloud的分布式框架，以mybatis-plus持久层框架，

## roses模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| roses-api | 服务接口和model | 8761 | 封装所有服务的接口，model，枚举等 |
| roses-register | 注册中心 | 8761 | eureka注册中心 |
| roses-gateway | 网关 | 8000 | 
| roses-monitor | 监控中心 | 9000 | 监控服务运行状况 |
| roses-auth | 鉴权服务 | 8001 | 转发，资源权限校验，请求号生成 |
| roses-config | 配置中心 | 8002 | 分布式配置server |
| roses-message-service | 消息服务 | 10001 | 可靠消息最终一致性（柔性事务解决方案） | 
| roses-message-checker | 消息恢复和消息状态确认子系统 | 10002 | 可靠消息最终一致性（柔性事务解决方案） |
| roses-example-order | 订单服务 | 11001 | 演示如何解决分布式事务 |
| roses-example-account | 账户服务 | 11002 | 演示如何解决分布式事务 |

# 