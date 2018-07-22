package roses.scanner.modular;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.stylefeng.roses.api.auth.ResourceDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import roses.scanner.config.properties.ScannerProperties;
import roses.scanner.modular.annotation.GetResource;
import roses.scanner.modular.annotation.PostResource;
import roses.scanner.modular.factory.ApiResourceFactory;
import roses.scanner.modular.stereotype.ApiResource;
import roses.scanner.modular.util.AopTargetUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资源扫描器
 *
 * @author fengshuonan
 * @Date 2018/1/3 下午2:58
 */
public class ApiResourceScaner implements BeanPostProcessor {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ApiResourceFactory apiResourceFactory;

    private ScannerProperties scannerProperties;

    private String springApplicationName;

    public ApiResourceScaner(ApiResourceFactory apiResourceFactory, ScannerProperties scannerProperties, String springApplicationName) {
        this.apiResourceFactory = apiResourceFactory;
        this.scannerProperties = scannerProperties;
        this.springApplicationName = springApplicationName;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        //如果controller是代理对象,则需要获取原始类的信息
        Object aopTarget = AopTargetUtils.getTarget(bean);

        if (aopTarget == null) {
            aopTarget = bean;
        }
        Class<?> clazz = aopTarget.getClass();

        //判断是不是控制器,不是控制器就略过
        boolean controllerFlag = getControllerFlag(clazz);
        if (!controllerFlag) {
            return bean;
        }

        //扫描控制器的所有带ApiResource注解的方法
        List<ResourceDefinition> apiResources = doScan(clazz);

        //将扫描到的注解转化为资源实体存储到缓存
        persistApiResources(apiResources);

        return bean;
    }


    /**
     * 判断一个类是否是控制器
     */
    private boolean getControllerFlag(Class<?> clazz) {
        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            if (RestController.class.equals(annotation.annotationType()) || Controller.class.equals(annotation.annotationType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 扫描整个类中包含的所有控制器
     */
    private List<ResourceDefinition> doScan(Class<?> clazz) {
        //绑定类的code-中文名称映射
        ApiResource classApiAnnotation = clazz.getAnnotation(ApiResource.class);
        if (classApiAnnotation != null) {
            if (StrUtil.isEmpty(classApiAnnotation.code())) {
                String className = clazz.getSimpleName();
                int controllerIndex = className.indexOf("Controller");
                if (controllerIndex == -1) {
                    throw new IllegalArgumentException("controller class name is not illegal, it should end with Controller!");
                }
                String code = className.substring(0, controllerIndex);
                this.apiResourceFactory.bindResourceName(StrUtil.toUnderlineCase(code), classApiAnnotation.name());
            } else {
                this.apiResourceFactory.bindResourceName(StrUtil.toUnderlineCase(classApiAnnotation.code()), classApiAnnotation.name());
            }
        }

        ArrayList<ResourceDefinition> apiResources = new ArrayList<>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (declaredMethods != null && declaredMethods.length > 0) {
            for (Method declaredMethod : declaredMethods) {
                ApiResource apiResource = declaredMethod.getAnnotation(ApiResource.class);
                GetResource getResource = declaredMethod.getAnnotation(GetResource.class);
                PostResource postResource = declaredMethod.getAnnotation(PostResource.class);

                Annotation annotation = null;
                if (apiResource != null) {
                    annotation = apiResource;
                }

                if (getResource != null) {
                    annotation = getResource;
                }

                if (postResource != null) {
                    annotation = postResource;
                }

                if (annotation != null) {
                    ResourceDefinition definition = createDefinition(clazz, declaredMethod, annotation);
                    apiResources.add(definition);
                    log.debug("扫描到资源: " + definition);
                }
            }
        }
        return apiResources;
    }

    /**
     * 存储扫描到的api资源
     */
    private void persistApiResources(List<ResourceDefinition> apiResources) {
        apiResourceFactory.registerDefinition(apiResources);
    }

    private ResourceDefinition createDefinition(Class<?> clazz, Method method, Annotation apiResource) {
        ResourceDefinition resourceDefinition = new ResourceDefinition();
        resourceDefinition.setClassName(clazz.getSimpleName());
        resourceDefinition.setMethodName(method.getName());

        String className = resourceDefinition.getClassName();
        int controllerIndex = className.indexOf("Controller");
        if (controllerIndex == -1) {
            throw new IllegalArgumentException("controller class name is not illegal, it should end with Controller!");
        }
        String modular = className.substring(0, controllerIndex);
        resourceDefinition.setModularCode(modular);

        //设置模块的中文名称
        ApiResource classApiAnnotation = clazz.getAnnotation(ApiResource.class);
        resourceDefinition.setModularName(classApiAnnotation.name());

        //如果控制器类上标识了appCode则应用标识上的appCode,如果控制器上没标识则用配置文件中的appCode
        if (StrUtil.isNotBlank(classApiAnnotation.appCode())) {
            resourceDefinition.setAppCode(classApiAnnotation.appCode());
        } else {
            resourceDefinition.setAppCode(scannerProperties.getAppCode());
        }

        //如果没有填写code则用"模块名称_方法名称"为默认的标识
        String code = invokeAnnotationMethod(apiResource, "code", String.class);
        if (StrUtil.isEmpty(code)) {
            resourceDefinition.setCode(resourceDefinition.getAppCode() + scannerProperties.getLinkSymbol() + StrUtil.toUnderlineCase(modular) + scannerProperties.getLinkSymbol() + StrUtil.toUnderlineCase(method.getName()));
        } else {
            resourceDefinition.setCode(resourceDefinition.getAppCode() + scannerProperties.getLinkSymbol() + StrUtil.toUnderlineCase(modular) + scannerProperties.getLinkSymbol() + StrUtil.toUnderlineCase(code));
        }

        //设置其他属性
        String name = invokeAnnotationMethod(apiResource, "name", String.class);
        String[] path = invokeAnnotationMethod(apiResource, "path", String[].class);
        RequestMethod[] requestMethods = invokeAnnotationMethod(apiResource, "method", RequestMethod[].class);
        Boolean menuFlag = invokeAnnotationMethod(apiResource, "menuFlag", Boolean.class);
        Boolean requiredLogin = invokeAnnotationMethod(apiResource, "requiredLogin", Boolean.class);
        Boolean requiredPermission = invokeAnnotationMethod(apiResource, "requiredPermission", Boolean.class);

        resourceDefinition.setRequiredLogin(requiredLogin);
        resourceDefinition.setRequiredPermission(requiredPermission);
        resourceDefinition.setMenuFlag(menuFlag);
        resourceDefinition.setName(name);
        resourceDefinition.setUrl(getControllerClassRequestPath(clazz) + path[0]);
        String methodNames = "";
        for (RequestMethod requestMethod : requestMethods) {
            methodNames += requestMethod.name() + ",";
        }
        resourceDefinition.setHttpMethod(StrUtil.removeSuffix(methodNames, ","));

        String localMacAddress = null;
        try {
            localMacAddress = NetUtil.getLocalhostStr();
        } catch (UtilException e) {
            log.error("获取当前机器ip地址错误！");
        }
        resourceDefinition.setIpAddress(localMacAddress == null ? "" : localMacAddress);
        resourceDefinition.setCreateTime(new Date());

        return resourceDefinition;
    }

    /**
     * 获取控制器类上的RequestMapping注解的映射路径,用于拼接path
     * <p>
     * 2018年5月2日修改，控制器路径前加上spring.application.name
     */
    private String getControllerClassRequestPath(Class<?> clazz) {
        String result;

        ApiResource controllerRequestMapping = clazz.getDeclaredAnnotation(ApiResource.class);
        if (controllerRequestMapping == null) {
            result = "";
        } else {
            String[] paths = controllerRequestMapping.path();
            if (paths.length > 0) {
                result = paths[0];
            } else {
                result = "";
            }
        }

        //拼接spring.application.name
        result = "/" + springApplicationName + result;
        return result;
    }

    private <T> T invokeAnnotationMethod(Annotation apiResource, String methodName, Class<T> resultType) {
        try {
            Class<? extends Annotation> annotationType = apiResource.annotationType();
            Method method = annotationType.getMethod(methodName);
            return (T) method.invoke(apiResource);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("扫描api资源时出错!", e);
        }
        throw new RuntimeException("扫描api资源时出错!");
    }
}
