# roses-gateway 微服务网关

---
   
## 介绍
本项目为Roses系列微服务框架的模块之一，Roses基于`Spring Boot 2`和`Spring Cloud Finchley.RELEASE`，致力做更简洁的**分布式**和**服务化**解决方案，Roses拥有高效率的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace）等等，**一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战**，请关注Roses微服务框架[https://gitee.com/naan1993/roses](https://gitee.com/naan1993/roses)

---

## 本项目模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| roses-gateway | 网关 | 8000 | 网关 |

---

## 注意事项

> * 开发环境为jdk 1.8
> * maven推荐使用阿里云镜像，拉取jar包保证成功
> * 运行roses-gateway之前，需要先运行roses-config-server（如果启用分布式配置的话），roses-cloud-register（服务注册发现）和roses-system（调用鉴权相关接口）
> * 本项目没有调用数据库，所以排除了数据源的自动配置DataSourceAutoConfiguration

---

## 启动步骤

> 1. 直接运行RosesGatewayApplication的main方法

---

## 使用介绍

当前版本过滤器介绍：
> 1. JwtTokenFilter，jwt鉴权过滤器，其中登陆接口AuthConstants.AUTH_ACTION_URL和校验token接口AuthConstants.VALIDATE_TOKEN_URL会放过过滤，不被校验，其他接口都需要携带token才可以访问，另外，可通过`jwt.secret`配置秘钥，`jwt.expiration`设置jwt失效时间，默认1天
> 2. PathMatchFilter，资源权限校验过滤器，Roses通过@ApiResource来搜集所有微服务中的资源，网关通过调用roses-system提供的用户权限数据，来确定当前用户是否有权限访问当前访问接口
> 3. RequestNoGenerateFilter，请求唯一号生成器，每次经过网关的请求，不管后续经过多少个微服务都会生成这样一个唯一请求号，用来日志追踪和异常排查

控制器接口介绍：
> 1. /gatewayAction/auth接口，相当于登陆接口，传递账号密码返回用户token，然后访问别的接口就带着这个token访问即可
> 2. /gatewayAction/validateToken接口，相当于单点登陆SSO接口，多个内部系统之间，通过此接口来确定当前登陆用户（携带的token）是否正确
> 3. /gatewayAction/logout接口，退出接口，服务器清除掉登陆记录缓存

---

## 项目特点

Roses中，所有业务请求经过网关，网关做统一的鉴权，权限过滤，数据签名校验，这样做的好处就是业务系统中不用集成鉴权模块，只需要关心各自服务的业务编写。鉴权方面Roses依旧使用jwt，如果想对某个用户作某些资源访问的限制，需要开启`PathMatchFilter`(默认没开启)，该过滤器会对用户当前访问的资源进行权限校验，资源的收集方式也做了大大的简化，Roses中通过资源搜集器`roses-scanner`收集资源，所有的资源只需要用`@ApiResource`注解标识，项目运行后会把所有资源发送到`roses-system`服务中，对比以往的每新增一个资源都需要在后台管理系统中新增一条记录，省了不少工作量。

另外，roses-gateway网关还为每个请求生成该次请求的唯一请求号。唯一请求号的作用如下：请求在业务流转过程中可能经过多个微服务，查看这次请求的info日志信息，或者error日志信息等，需要从多个微服务的日志记录里去查找，效率非常低，那么，有了唯一请求号标识之后，可以用唯一请求号把请求经过的所有业务流转串起来，并存储起来，当请求遇到问题后，可以通过唯一请求号快速把这次请求的所有日志搜集并展示起来，从而方便排查问题。

Roses中，请求号在中转过程中填充到请求的Request-Header中，与之对应，响应时也会在Response-Hedaer中把本次的请求号输出。过滤器中的写法如下：
```
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
        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            if (log.isDebugEnabled()) {
                log.debug("被调环境中不存在request对象，则不往header里添加当前请求环境的header!");
            }
            return;
        } else {
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                }
            }
        }
        this.addOtherHeaders(requestTemplate);
    }

    public void addOtherHeaders(RequestTemplate requestTemplate) {
    
    }
}
```

---

