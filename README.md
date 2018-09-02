# Roses v2.0

## 介绍
Roses基于`Spring Boot 2`和`Spring Cloud Finchley.RELEASE`，致力做更简洁的**分布式**和**服务化**解决方案，Roses拥有高效率的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace）等等，**一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战**，请关注Roses微服务框架[https://gitee.com/naan1993/roses](https://gitee.com/naan1993/roses)

### Roses 2.0更新内容

拆分了子项目，在多个git仓库中单独维护。新加了很多功能，详情见各个子项目和下边的业务介绍。

---

## Roses子项目介绍

| 模块名称 | 说明 | 地址 |
| :---: | :---: | :---: |
| roses-kernel | Roses框架核心支撑 | https://gitee.com/stylefeng-Roses/roses-kernel |
| roses-biz-support | 基础业务服务支撑 | https://gitee.com/stylefeng-Roses/roses-biz-support |
| roses-cloud-support | 微服务支撑组件 | https://gitee.com/stylefeng-Roses/roses-cloud-support |
| roses-config-repo | 配置仓库 | https://gitee.com/stylefeng-Roses/roses-config-repo |
| roses-system | 系统管理服务 | https://gitee.com/stylefeng-Roses/roses-system |
| roses-gateway | 微服务网关 | https://gitee.com/stylefeng-Roses/roses-gateway |

**为什么Roses 2.0拆分项目**

一句话回答就是使`框架和业务分开维护`，`更快捷的开发新业务`

相信大家所在的公司都有自己的gitlab仓库，或者托管在码云平台，仓库中有很多项目，很多业务模块，每个业务模块都配置了spring mvc，mybaits，mybaits plus之类的，每个
项目都有一些共性的东西，当您开发一个微服务的时候，您是直接从旧项目复制一套东西，在新项目改一改名字，包名，类名更方便，还是直接新建一个项目，引入一个maven的pom
更方便。还有就是框架上的东西升级的时候，同样的类，同样的方法，您一定不喜欢修改两次或者更多次。

可能有的人会反驳这个观点，直接建一个大项目，里边建立多个maven模块来构建，新建微服务的时候我直接在下边加个模块不可以吗？

也可以，但是不推荐，到了项目后期，模块越来越多，可能光导入项目机器都很卡，还有开发A模块的人，公司规定不准看B模块的业务，这种情况无法解决。随着公司的发展，公司一定不是只有一个项目。

所以拆分项目，更加适合公司实际业务开发的情况，共有的东西提出来，以maven模块方式依赖进业务系统。升级框架的时候，通知业务负责人升级pom
版本即可。业务人员也可以更加专注于开发业务，导入项目开发时，也只需要导入开发人员开发的模块即可，非常方便。

---

## 使用手册

如果是刚接触微服务，可能有些吃力，不要气馁，相信自己，花点时间一定可以跑起来。

由于拆分了项目，使用方式略有不同，不了解maven的同学，需要先学习下maven，另外项目还用了lombok，需要安装lombok插件。

请先导入roses-kernel，根pom下执行`mvn clean install -Dmaven.test.skip=true`或者deploy到自己公司的私服上。

再导入roses-biz-support，根pom下执行`mvn clean install -Dmaven.test.skip=true`或者deploy到自己公司的私服上。

之后需要先启动配置中心，再启动注册中心，仓库地址地址在https://gitee
.com/stylefeng-Roses/roses-cloud-support上面，具体启动方法见仓库里readme

然后启动roses-system,仓库地址地址在https://gitee
.com/stylefeng-Roses/roses-system，具体启动方法见仓库里readme

再启动roses-gateway网关，仓库地址在https://gitee
.com/stylefeng-Roses/roses-gateway，具体启动方法见仓库里readme

---

## 微服务的优缺点
微服务不一定适用于所有的系统构建，需要根据各自公司业务情况来评估。以下列举一些微服务的优缺点，仅供参考。

### 1. 微服务的优势
1. 单体服务就像在一个小超市中，一个人负责收银，打扫，摆货，咨询等各种事情，服务化就像是一大超市，采取分工的方式更好的服务顾客，更加高效。
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
> 1. 如果您觉得分布式项目不适合您，不妨考虑一下作者的另一款单体项目框架[Guns](https://gitee.com/naan1993/guns)，Guns项目代码简洁,注释丰富,
上手容易，是一个后台管理系统的脚手架，会让您降低不少开发成本！
> 2. 刚接触分布式框架的同学，请利用好手头的资源，运行前仔细阅读使用手册，按照步骤一步步来，一定可以成功运行！
> 3. 有任何疑问和建议，不妨加入Roses的交流群`684163663`或者`207434260`一起交流学习！

--- 

## Roses特点

### 1. 快速开发微服务的秘籍 roses-kernel
在roses-kernel模块的`com.stylefeng.roses.core.config`包下整合了大量开发常用到的配置，其中包含默认异常拦截，登陆用户的上下文获取，默认缓存配置，默认fastjson的配置，默认mybatis-plus的配置，默认的swagger的配置，默认的web配置等等等等，使得在新业务开发中，只要pom引入roses-kernel这个模块，即可很方便的注入这些特性，直接上手开编写业务，大大减少了新业务，新模块的配置，调试，各种框架集成拼接的时间，因为这些在Roses中已经为您提供好了，利用Spring Boot的自动配置机制，同样的，这些配置在项目启动的时候会默认加载，因为在roses-kernel模块下的META-INF/spring.factories中配有这些类，当然，如果您不需要某些特性（自动配置类）您可以在@SpringBootApplication注解上增加exclude参数来排除这些自动配置。

**有了roses-kernel模块，开发别的模块时，您可以把百分之90的精力花在编写业务上，百分之10的精力花在搭建项目和配置项目上。**


### 2. 网关实现统一鉴权，资源权限过滤
Roses中，所有业务请求经过网关，网关做统一的鉴权，权限过滤，数据签名校验，这样做的好处就是业务系统中不用集成鉴权模块，只需要关心各自服务的业务编写。鉴权方面Roses依旧使用jwt，如果想对某个用户作某些资源访问的限制，需要开启`PathMatchFilter`，该过滤器会对用户当前访问的资源进行权限校验，资源的收集方式也做了大大的简化，Roses中通过资源搜集器`roses-scanner`收集资源（详见特点6），所有的资源只需要用`@ApiResource`注解标识，项目运行后会把所有资源发送到`roses-auth`服务中，对比以往的每新增一个资源都需要在后台管理系统中新增一条记录，省了不少工作量。

### 3. 网关生成唯一请求号（RequestNoGenerateFilter）
Roses中，所有业务请求经过网关，网关做统一的鉴权，权限过滤，数据签名校验，并为每个请求生成该次请求的唯一请求号。唯一请求号的作用如下：请求在业务流转过程中可能经过多个微服务，查看这次请求的info日志信息，或者error日志信息等，需要从多个微服务的日志记录里去查找，效率非常低，那么，有了唯一请求号标识之后，可以用唯一请求号把请求经过的所有业务流转串起来，并存储起来，当请求遇到问题后，可以通过唯一请求号快速把这次请求的所有日志搜集并展示起来，从而方便排查问题。

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

### 4. 分布式事务解决方案（可靠消息最终一致性）
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

### 5. 分布式配置中心roses-config
在Roses中，正如您所见，所有的模块都是在一个大工程下，但是实际日常开发中，模块往往分隔在多个项目中，每个项目有单独的小组来维护，小组与小组之间甚至代码都不是可见。当项目中的一些通用配置变动时，例如数据库地址，账号密码等，要么你通知各个小组修改他们的配置，要么你自己打开所有项目修改一遍。如果使用了分布式配置中心，可以把所有项目的配置收集起来，集中配置，那么你只需要打开配置中心的git仓库来修改配置，从而简化配置的维护工作。

除此之外，如果spring boot应用开启了actuator，配置仓库中的配置更改后，应用还可以通过/refresh动态刷新项目的配置，这在网关动态增加路由等配置上非常方便。

当然，开启分布式配置之后也有不完美的地方，在本地开发调试中，常常需要修改配置，直接修改远程仓库的配置又会影响到别人的本地开发，关于这种情况，Roses在每个项目的src/test/resources文件夹中，都保留了一个`application-local.yml`，这个配置文件是local环境下的应用的配置文件，您可以把它拷贝到src/main/resources中，并配置`bootstrap.yml`中的`spring.cloud.config.enable`配置为false，即可关闭配置中心的配置，使用本地的配置。

### 6. 一切请求基于RequestData和ResponseData
为了方便日常接口开发，Roses对控制器层的请求参数和响应进行了统一封装。所有post方式的请求，并且带有json请求body的都可以用RequestData类来作为参数接收请求数据，所有的响应都可以用ResponseBody来作响应的结果。

RequestData类中封装了对请求参数获取的常用方法，例如getString(),getInteger(),parse()等等，可以很方便的获取请求中包含的字符串数据，整型数据，以及解析请求为某个类。而ResponseData类中包含了对常用成功或者失败响应的封装，可以通过静态方法ResponseData.success()或者ResponseData.error()来响应成功的结果或者失败的响应。

例如，请求的数据是一段json：
```
{name:"order001",count:20}
```

控制器中可以用如下写法获取参数,并响应成功的返回结果：
```
/**
 * 测试RequestData
 */
@RequestMapping("/test")
public Object test(RequestData requestData) {

    String name = requestData.getString("name");
    System.out.println(name);

    Integer count = requestData.getInteger("count");
    System.out.println(count);

    MyOrder order = requestData.parse(MyOrder.class);
    System.out.println(order);

    return ResponseData.success(order);
}
```

并不是所有请求和响应都必须用RequestData和ResponseData来接收和响应，这只是Roses提供的一种拓展功能，一种便利，可以选择使用或否。

所有接口的参数和响应都封装在两个对象里，开发时候不用构建许多的vo（view object）和qo（query object），但是也有不利的地方，由于不知道请求和响应包含哪些参数，维护时候可能会增加难度，所以，在使用这种机制的时候，配合类似于[RAP](https://github.com/thx/RAP)这样的接口管理工具会更加的好用。

### 7. 独创基于BeanPostProcessor的资源扫描器
Roses中设立了@ApiResource注解，用来标注控制器里的接口，在Roses框架中，用户对应了角色，角色又对应着资源，菜单关联资源。资源扫描器的作用一方面是便于管理所有的接口资源的权限控制，另一方面是简化了资源录入系统的方式 （不用手写），当程序启动时，会自动扫描带有@ApiResource注解的方法，扫描之后会对资源进行包装，写入到数据库。所以当使用了资源扫描器之后可以很方便的搜集所有服务上面的接口资源，并通过roses-auth的接口，统一的汇报到roses-auth服务上面去，从而实现了资源的集中管理。
                                                                          

### 8. 独特的Feign错误解码器
Roses继承了Guns框架的业务编写方式，在适当的的业务错误场景，如果不能再继续执行下去业务，可以抛出ServiceException，让DefualtExceptionHandler拦截到异常，直接返回给前端业务上的错误提示信息。那么，在分布式的场景下，如何利用ServiceException的抛出来返回前端提示呢，当A服务调用B服务，B服务又调用C服务过程中，如何把异常信息逐级返回给上个服务呢，Roses中利ErrorDecoder并覆盖其中的decode方法，当response中的信息为Roses中的错误编码格式的话，会在Feign错误解析过程中直接抛出ServiceException，从而直接在调用方服务中抛出被调用方同样的服务异常，实现逐级响应Service服务异常的功能，如果担心服务异常带来的性能问题，可以通过 override 掉异常类的 fillInStackTrace() 方法为空方法，使其不拷贝栈信息。


### 9. Log + Trace日志记录
为了方便业务异常以及分布式调用链中调用异常排查，Roses编写了LogUtil和TraceUtil两个类来记录业务中的调试，提示，错误日志和调用链调用过程中的信息日志，LogUtil中包含LogUtil.info()，LogUtil.debug()，LogUtil.error()等静态方法，TraceUtil中包含TraceUtil.trace()等静态方法，这两个类都采用线程池异步记录日志的方式来记录，目前日志是通过Redis的List放到队列在通过roses-logger模块监听队列来消费记录日志，当然，Roses提供了拓展，如果这种记录方式或是存储介质不符合您的业务需求，您可以通过继承com.stylefeng.roses.core.log.LogProducerService接口，实现您自己的日志记录方式，而不必修改LogUtil中的日志记录方法。总之，Log + Trace的日志记录方法，为多服务异常排查，和调用链服务治理提供了很好的保障。

### 10. 统一的日志记录格式
Roses采用logback来记录日志，并且每个模块都有统一的规范的日志记录格式，具体可见每个模块下的`logback-spring.xml`配置文件，在这个配置中，定义了两种profile。

第一种profile是在`spring.profiles.active`为local时激活，也就是，在本地开发中，日志只打印到控制台中，不会输出到文件中，默认日志输出级别是info，但是`com.stylefeng.roses`包中的类的日志，以debug级别输出，为了方便开发人员本地调试。
```
<springProfile name="local">
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>===%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level %logger Line:%-3L - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="STDOUT"/>
    </root>

    <logger name="com.stylefeng.roses" level="debug" additivity="false">
        <appender-ref ref="STDOUT"/>
    </logger>
</springProfile>
```

第二种profile是在`spring.profiles.active`不是local时激活，也就是，不管在正式环境或者测试环境的linux服务器中，都不会在控制台打印logback记录的日志，都会把日志输出到文件中，在这种profile下，记录的日志文件分为两类，第一类日志文件是只记录ERROR级别的日志，可以定期查看这个文件的错误日志，排查服务问题，第二类则是记录所有级别的日志。在两类日志记录器中，日志的切割都是以日期和文件大小（默认2M）切分。
```
<springProfile name="!local">

    <appender name="FILE_ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        ...
    </appender>

    <appender name="FILE_ALL" class="ch.qos.logback.core.rolling.RollingFileAppender">
        ...
    </appender>

    <root level="info">
        <appender-ref ref="FILE_ERROR"/>
        <appender-ref ref="FILE_ALL"/>
    </root>

</springProfile>
```
