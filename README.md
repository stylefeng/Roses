# Roses v1.0
   
## 介绍
Roses基于Spring Boot, 是开源项目Guns（[https://gitee.com/naan1993/guns](https://gitee.com/naan1993/guns)）的升级版本，致力于做整套的**分布式**和**服务化**解决方案，Roses提供基于Spring Cloud的分布式框架，整合了springmvc + mybatis-plus + eureka + zuul + feign + ribbon + hystrix等等，提供Roses独有的便捷的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace），**一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战。**

---

## Roses模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| roses-api | 服务接口和model | 无 | 封装所有服务的接口，model，枚举等 |
| roses-auth | 鉴权服务 | 8001 | 提供用户，资源，权限等接口 |
| roses-config | 配置中心服务器 | 8002 | 配置集中管理 |
| roses-core | 项目骨架 | 无 | 封装框架所需的基础配置，工具类和运行机制等 |
| roses-gateway | 网关 | 8000 | 转发，资源权限校验，请求号生成等 |
| roses-logger | 日志服务 | 8003 | 消费并存储日志 |
| roses-scanner | 资源扫描器 | 无 | 接口资源无须录入，启动即可录入系统 |
| roses-register | 注册中心 | 8761 | eureka注册中心 |
| roses-monitor | 监控中心 | 9000 | 监控服务运行状况 |
| roses-message-service | 消息服务 | 9001 | 可靠消息最终一致性（柔性事务解决方案） | 
| roses-message-checker | 消息恢复和消息状态确认子系统 | 9002 | 可靠消息最终一致性（柔性事务解决方案） |
| roses-example-order | 订单服务 | 11001 | 演示如何解决分布式事务 |
| roses-example-account | 账户服务 | 11002 | 演示如何解决分布式事务 |

---

## 使用手册

### 运行环境
> * JDK 1.8
> * Redis最新版
> * ActiveMQ 5.9.1（可以根据`com.stylefeng.roses.message.core.activemq.MessageSender`接口自行拓展为自己公司需要的队列）
> * Mysql 5.6 +，项目运行前请初始化数据库，脚本在各自项目的sql文件夹

### 项目运行步骤
> 1. 启动注册中心roses-register
> 2. 启动消息服务roses-message-service
> 3. （可选，演示用）启动账户服务roses-example-account
> 4. （可选，演示用）启动订单服务roses-example-order
> 5. 启动消息恢复子系统roses-message-checker
> 6. 启动网关服务roses-gateway
> 7. 启动鉴权服务roses-auth

---

## 微服务的优缺点
微服务不一定适用于所有的系统构建，需要根据各自公司业务情况来评估。以下列举一些微服务的优缺点，仅供参考。

### 1. 微服务的优势
1. 打个比喻，单体服务就像在一个小超市中，一个人负责收银，打扫，摆货，咨询等各种事情，服务化就像是一个大超市，采取分工的方式更好的服务顾客，更加高效。
2. 多业务并行开发更加容易，单体应用按业务拆分后，一个微服务只需要关注某个方面的业务，例如订单，用户，积分，账户服务等，同时并行开发，代码提交互不影响，每个人只需要关注自己的领域迭代。
3. 上线发布不会因为某个微服务升级，影响到所有服务，每次某个微服务升级，只需要重新部署一个服务即可。
4. 技术栈不受限制，在微服务架构中，服务之间调用可用restful api方式进行交互，所以技术选型可以根据不同部门的需要设计不同的服务。
5. 通过对服务的划分，当系统中某些业务出现瓶颈时，可根据需要进行升级，或者直接增加某些服务的机器。

### 2. 微服务面临的挑战
1. 服务多级调用带来的调试，跟踪困难。访问一个业务功能，要经过网关，之后调用A服务，B服务，C服务，通常会先去寻找A服务的问题，之后去排查B服务，以此类推，排查所有服务后，也可能问题不在A，B，C三个服务上，也许只是A访问B时调用超时了，所以这就导致了服务出现问题，定位非常困难。
2. 分布式事务问题。A服务调用B服务，之后A又调用C服务，若C服务执行时出现异常，那么如何回滚B服务的执行过程。
3. 运维成本。更多的服务意味着更多的运维投入，单体架构中，只需部署一个项目即可，微服务中，少则部署数个多则部署几十个服务，并且某些服务可能为集群部署，机器的消耗和运维要比单体服务提升很多。
4. 开发成本，对开发人员的技术要求和开发一个业务的工作量有所不同。单体服务中，没有幂等性和分布式事务等问题，微服务开发中，不仅需要编写业务，还要考虑到可能会影响业务正常运行的其他因素。

### 一些建议
> 1. 项目刚成立之初，如果业务量不大，不建议直接上分布式架构，由于微服务有固有的复杂性，会增加运维和开发成本，所以这点可以根据项目和业务情况选择！
> 2. 如果您觉得分布式项目不适合您，不妨考虑一下作者的另一款单体项目框架[Guns](https://gitee.com/naan1993/guns)，Guns项目代码简洁,注释丰富,上手容易，是一个后台管理系统的脚手架，会让您降低不少开发成本！
> 3. 刚接触分布式框架的同学，请利用好手头的资源，运行前仔细阅读使用手册，按照步骤一步步来，一定可以成功运行！如果觉得过程过于复杂，对，分布式就是这么复杂，要不你试试[Guns](https://gitee.com/naan1993/guns)？
> 4. 有任何疑问和建议，不妨加入Roses的交流群`684163663`一起交流学习！

--- 

## Roses特点

### 1. 网关生成唯一请求号（RequestNoGenerateFilter）
Roses中，所有业务请求经过网关，网关做统一的鉴权，权限过滤，数据签名校验等。唯一请求号的作用如下：请求在业务流转过程中可能经过多个微服务，查看这次请求的info日志信息，或者error日志信息等，需要从多个微服务的日志记录里去查找，效率非常低，那么，有了唯一请求号标识之后，可以用唯一请求号把请求经过的所有业务流转串起来，并存储起来，当请求遇到问题后，可以通过唯一请求号快速把这次请求的所有日志搜集并展示起来，从而方便排查问题。

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

### 2. 分布式事务解决方案（可靠消息最终一致性）
首先，分布式事务在不同业务场景下，解决方案是不一样的，时效性要求较高的场景下，例如订单支付成功后，更改订单状态，给用户账户加款，给积分账户加积分，三个操作在三个不同的服务下，这个时候可以用TCC方式解决事务问题；在时效性要求较为不严格下，例如订单支付成功后，需要异步录入会计凭证（不严格要求时效性），这个时候可以用可靠消息最终一致性解决。

Roses中实现了可靠消息最终一致性的解决方案（如上所说第二个例子），TCC方案目前未集成到系统。

#### 实现原理
单纯依靠消息队列无法实现消息的可靠投递和消费，所以借助预发送待确认消息，业务执行成功后再发送确认消息来保证消息的可靠投递，并且通过中间服务（消息服务）来控制统一的消息预存储和确认发送，统一执行发送到消息队列的操作，并且消息服务有单独的消息表来记录消息是否已经投递成功。

Roses中roses-message-service为消息服务，为可靠消息最终一致性实现的核心，roses-message-checker为定时任务执行器，每隔一定时间来轮训消息表中是否有消息不一致的数据，若消息不一致则从业务系统中调用接口来查询具体业务的执行状态，从而来更新消息表中的消息。

roses-example-order和roses-example-account两个模块，模拟了分布式事务的场景，首先通过order模块下一个单（/place接口），再执行完成此订单（/finish接口），在完成订单过程中，先调用了预发送消息接口（preSaveMessage），之后执行完业务后调用确认并发送消息（confirmAndSendMessage）。在account模块中有消息的监听器，监听到消息后存储账号交易流水记录（recordFlow）。account和order模块为了演示业务流程用，数据库设计比较简单，不合理处请见谅。
```
//创建预发送消息
ReliableMessage reliableMessage = createMessage(order);

//预发送消息
messageServiceConsumer.preSaveMessage(reliableMessage);

//更新订单为成功状态(百分之50几率失败，模拟错误数据)（此处错误已添加到消息表的数据会被roses-message-checker轮询时删除掉）
updateToSuccess(order);

//确认消息
messageServiceConsumer.confirmAndSendMessage(reliableMessage.getMessageId());
```

#### 幂等性校验
消息的投递有重试机制，所以在消息的消费端需要加上幂等性校验，使得多次消费消息也可以让业务实际只执行一次，在account模块中幂等性的判断通过订单号来标识操作的唯一性。

```

//幂等判断
EntityWrapper<FlowRecord> wrapper = new EntityWrapper<>();
wrapper.eq("order_id", goodsFlowParam.getId());

List<FlowRecord> flowRecords = this.selectList(wrapper);
if (flowRecords != null && !flowRecords.isEmpty()) {
    return;
}
```

### 3. 一切请求基于RequestData和ResponseData
为了方便日常开发，Roses对控制器层的请求参数和响应进行了统一封装。所有post方式的请求，并且带有json请求body的都可以用RequestData类来作为参数接收请求数据，所有的响应都可以用ResponseBody来作响应的结果。

RequestData类中封装了对请求参数获取的常用方法，例如getString(),getInteger(),parse()等等，可以很方便的获取请求中包含的字符串数据，整型数据，以及解析请求为某个类。而ResponseData类中包含了对常用成功或者失败响应的封装，可以通过静态方法ResponseData.success()或者ResponseData.error()来响应成功的结果或者失败的响应。

例如，请求的数据是一段json：
```
{goodsName:"order001",count:20}
```

控制器中可以用如下写法获取参数,并响应成功的返回结果：
```
/**
 * 测试RequestData
 */
@RequestMapping("/test")
public Object test(RequestData requestData) {

    String orderId = requestData.getString("goodsName");
    System.out.println(orderId);

    Integer number = requestData.getInteger("count");
    System.out.println(number);

    GoodsOrder goodsOrder = requestData.parse(GoodsOrder.class);
    System.out.println(goodsOrder);

    return ResponseData.success(goodsOrder);
}
```

并不是所有请求和响应都必须用RequestData和ResponseData来接收和响应，这只是Roses提供的一种拓展功能，一种便利，可以选择使用或否。

所有接口的参数和响应都封装在两个对象里，开发时候不用构建许多的vo（view object）和qo（query object），但是也有不利的地方，由于不知道请求和响应包含哪些参数，维护时候可能会增加难度，所以，在使用这种机制的时候，配合类似于[RAP](https://github.com/thx/RAP)这样的接口管理工具会更加的好用。

### 4. 独创基于BeanPostProcessor的资源扫描器
Roses中设立了@ApiResource注解，用来标注控制器里的接口，在Roses框架中，用户对应了角色，角色又对应着资源，菜单关联资源。资源扫描器的作用一方面是便于管理所有的接口资源的权限控制，另一方面是简化了资源录入系统的方式 （不用手写），当程序启动时，会自动扫描带有@ApiResource注解的方法，扫描之后会对资源进行包装，写入到数据库。所以当使用了资源扫描器之后可以很方便的搜集所有服务上面的接口资源，并通过roses-auth的接口，统一的汇报到roses-auth服务上面去，从而实现了资源的集中管理。
                                                                          

### 5. 独特的Feign错误解码器
Roses继承了Guns框架的业务编写方式，在适当的的业务错误场景，如果不能再继续执行下去业务，可以抛出ServiceException，让DefualtExceptionHandler拦截到异常，直接返回给前端业务上的错误提示信息。那么，在分布式的场景下，如何利用ServiceException的抛出来返回前端提示呢，当A服务调用B服务，B服务又调用C服务过程中，如何把异常信息逐级返回给上个服务呢，Roses中利ErrorDecoder并覆盖其中的decode方法，当response中的信息为Roses中的错误编码格式的话，会在Feign错误解析过程中直接抛出ServiceException，从而直接在调用方服务中抛出被调用方同样的服务异常，实现逐级响应Service服务异常的功能，如果担心服务异常带来的性能问题，可以通过 override 掉异常类的 fillInStackTrace() 方法为空方法，使其不拷贝栈信息。


### 6. Log + Trace日志记录
为了方便业务异常以及分布式调用链中调用异常排查，Roses编写了LogUtil和TraceUtil两个类来记录业务中的调试，提示，错误日志和调用链调用过程中的信息日志，LogUtil中包含LogUtil.info()，LogUtil.debug()，LogUtil.error()等静态方法，TraceUtil中包含TraceUtil.trace()等静态方法，这两个类都采用线程池异步记录日志的方式来记录，目前日志是通过Redis的List放到队列在通过roses-logger模块监听队列来消费记录日志，当然，Roses提供了拓展，如果这种记录方式或是存储介质不符合您的业务需求，您可以通过继承com.stylefeng.roses.core.log.LogProducerService接口，实现您自己的日志记录方式，而不必修改LogUtil中的日志记录方法。总之，Log + Trace的日志记录方法，为多服务异常排查，和调用链服务治理提供了很好的保障。

---

## 快速开发微服务的秘籍 roses-core
在roses-core模块的`com.stylefeng.roses.core.config`包下整合了大量开发常用到的配置，其中包含默认异常拦截，登陆用户的上下文获取，默认缓存配置，默认fastjson的配置，默认mybatis-plus的配置，默认的swagger的配置，默认的web配置等等等等，使得在新业务开发中，只要pom引入roses-core这个模块，即可很方便的注入这些特性，直接上手开编写业务，大大减少了新业务，新模块的配置，调试，各种框架集成拼接的时间，因为这些在Roses中已经为您提供好了，利用Spring Boot的自动配置机制，同样的，这些配置在项目启动的时候会默认加载，因为在roses-core模块下的META-INF/spring.factories中配有这些类，当然，如果您不需要某些特性（自动配置类）您可以在@SpringBootApplication注解上增加exclude参数来排除这些自动配置。

**有了roses-core模块，开发别的模块时，您可以把百分之90的精力花在编写业务上，百分之10的精力花在搭建项目和配置项目上。**

---

## 未完待续...
更多有趣，实用，高效的功能尽在**Roses**