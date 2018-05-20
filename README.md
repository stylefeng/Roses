# Roses the next Guns v1.0
   [https://gitee.com/naan1993/guns](https://gitee.com/naan1993/guns)
   
## 介绍
Roses基于Spring Boot, 致力于做更完善的**分布式**和**服务化**解决方案，Roses提供基于Spring Cloud的分布式框架，整合了springmvc + mybatis-plus + eureka + zuul + feign + ribbon + hystrix等等，提供Roses独有的便捷的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace），个人认为，一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战。


## Roses模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| roses-api | 服务接口和model | 无 | 封装所有服务的接口，model，枚举等 |
| roses-core | 项目骨架 | 无 | 封装框架所需的基础配置，工具类和运行机制等 |
| roses-scanner | 资源扫描器 | 无 | 接口无须录入，启动即可录入系统，使资源控制更简单 |
| roses-register | 注册中心 | 8761 | eureka注册中心 |
| roses-gateway | 网关 | 8000 | 转发，资源权限校验，请求号生成等 |
| roses-monitor | 监控中心 | 9000 | 监控服务运行状况 |
| roses-auth | 鉴权服务 | 8001 | 提供用户，资源，权限等接口 |
| roses-message-service | 消息服务 | 10001 | 可靠消息最终一致性（柔性事务解决方案） | 
| roses-message-checker | 消息恢复和消息状态确认子系统 | 10002 | 可靠消息最终一致性（柔性事务解决方案） |
| roses-example-order | 订单服务 | 11001 | 演示如何解决分布式事务 |
| roses-example-account | 账户服务 | 11002 | 演示如何解决分布式事务 |

## 微服务的优缺点
微服务不一定适用于所有的系统构建，需要根据各自公司业务情况来评估。以下列举一些微服务的优缺点，仅供参考。

### 微服务的优势
1. 打个比喻，单体服务就像在一个小超市中，一个人负责收银，打扫，摆货，咨询等各种事情，服务化就像是一个大超市，采取分工的方式更好的服务顾客，更加高效。
2. 多业务并行开发更加容易，单体应用按业务拆分后，一个微服务只需要关注某个方面的业务，例如订单，用户，积分，账户服务等，同时并行开发，代码提交互不影响，每个人只需要关注自己的领域迭代。
3. 上线发布不会因为某个微服务升级，影响到所有服务，每次某个微服务升级，只需要重新部署一个服务即可。
4. 技术栈不受限制，在微服务架构中，服务之间调用可用restful api方式进行交互，所以技术选型可以根据不同部门的需要设计不同的服务。
5. 通过对服务的划分，当系统中某些业务出现瓶颈时，可根据需要进行升级，或者直接增加某些服务的机器。

### 微服务面临的挑战
1. 服务多级调用带来的调试，跟踪困难。访问一个业务功能，要经过网关，之后调用A服务，B服务，C服务，通常会先去寻找A服务的问题，之后去排查B服务，以此类推，排查所有服务后，也可能问题不在A，B，C三个服务上，也许只是A访问B时调用超时了，所以这就导致了服务出现问题，定位非常困难。
2. 分布式事务问题。A服务调用B服务，之后A又调用C服务，若C服务执行时出现异常，那么如何回滚B服务的执行过程。
3. 运维成本。更多的服务意味着更多的运维投入，单体架构中，只需部署一个项目即可，微服务中，少则部署数个多则部署几十个服务，并且某些服务可能为集群部署，机器的消耗和运维要比单体服务提升很多。
4. 开发成本，对开发人员的技术要求和开发一个业务的工作量有所不同。单体服务中，没有幂等性和分布式事务等问题，微服务开发中，不仅需要编写业务，还要考虑到可能会影响业务正常运行的其他因素。

## Roses特点

### 网关生成唯一请求号（RequestNoGenerateFilter）
Roses中，所有业务请求经过网关，网关做统一的鉴权，权限过滤，数据签名校验等。唯一请求号的作用如下：请求在业务流转过程中可能经过多个微服务，查看这次请求的info日志信息，或者error日志信息等，需要从多个微服务的日志记录里去查找，效率非常低，那么，有了唯一请求号标识之后，可以用唯一请求号把请求经过的所有业务流转串起来，并存储起来，当请求遇到问题后，可以通过唯一请求号快速把这次请求的所有日志搜集并展示起来，从而方便排查问题。

Roses中，请求号在中转过程中填充到请求的Request-Header中，与之对应，响应时也会在Response-Hedaer中把本次的请求号输出。过滤器中的写法如下：
```java
RequestContext currentContext = RequestContext.getCurrentContext();
HttpServletResponse response = currentContext.getResponse();

String requestNo = IdWorker.getIdStr();

currentContext.addZuulRequestHeader(RosesConstants.REQUEST_NO_HEADER_NAME, requestNo);
response.addHeader(RosesConstants.REQUEST_NO_HEADER_NAME, requestNo);

```

为了让Feign调用中，自动填充网关生成的唯一号，Roses增加了RosesFeignHeaderProcessInterceptor拦截器，实现如下：
```java
public class RosesFeignHeaderProcessInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        //当前feign远程调用环境不是由http接口发起，例如test单元测试中的feign调用或者项目启动后的feign调用
        HttpServletRequest request = null;

        try {
            request = HttpContext.getRequest();
        } catch (NullPointerException e) {

            //被调环境中不存在request对象，则不往header里添加当前请求环境的header
            return;
        }
        if (request != null) {
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                }
            }
        }
    }
}

```
   
### 独创资源扫描器

### 一切请求基于RequestData ResponseData

### feign异常处理

### 分布式事务解决方案（可靠消息最终一致性）