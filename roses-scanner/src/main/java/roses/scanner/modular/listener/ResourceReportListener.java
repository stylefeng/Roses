package roses.scanner.modular.listener;

import com.stylefeng.roses.api.auth.ResourceDefinition;
import com.stylefeng.roses.api.auth.service.ResourceService;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import roses.scanner.config.properties.ScannerProperties;
import roses.scanner.modular.factory.ApiResourceFactory;

import java.util.Map;

/**
 * 监听项目初始化完毕,报告到服务器资源
 *
 * @author fengshuonan
 * @date 2018-02-06 13:05
 */
public class ResourceReportListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        System.out.println("发送本系统的所有资源到roses-auth服务开始！");

        //获取当前系统的所有资源
        ApiResourceFactory resourceFactory = applicationContext.getBean(ApiResourceFactory.class);
        Map<String, Map<String, ResourceDefinition>> modularResources = resourceFactory.getModularResources();

        //发送资源到资源服务器
        ScannerProperties scannerProperties = applicationContext.getBean(ScannerProperties.class);
        ResourceService resourceService = applicationContext.getBean(ResourceService.class);
        resourceService.reportResources(scannerProperties.getAppCode(), modularResources);

        System.out.println("发送本系统的所有资源到roses-auth服务完毕！");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
