package roses.scanner.modular.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * get方式请求http的资源标识
 *
 * @author fengshuonan
 * @date 2018-01-03-下午2:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.POST)
public @interface GetResource {

    /**
     * <pre>
     * 资源编码唯一标识.
     *
     * 说明:
     *     1.可不填写此注解属性.
     *     2.若不填写,则默认生成的编码标识为: 控制器类名称 + 分隔符 + 方法名称.
     *     3.若编码存在重复则系统启动异常
     *
     * </pre>
     */
    String code() default "";

    /**
     * 资源名称(必填项)
     */
    String name() default "";

    /**
     * 是否是菜单(true-是菜单标识,false-不是菜单标识)
     */
    boolean menuFlag() default false;

    /**
     * 需要登录(true-需要登录,false-不需要登录)
     */
    boolean requiredLogin() default true;

    /**
     * 需要鉴权(true-需要鉴权,false-不需要鉴权)
     */
    boolean requiredPermission() default true;

    /**
     * 请求路径(同RequestMapping)
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};

    /**
     * 请求的http方法(同RequestMapping)
     */
    @AliasFor(annotation = RequestMapping.class)
    RequestMethod[] method() default RequestMethod.GET;
}
