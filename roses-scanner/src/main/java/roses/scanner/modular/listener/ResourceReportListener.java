package roses.scanner.modular.listener;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import share.core.util.LogUtil;
import share.model.auth.ResourceDefinition;
import share.model.auth.service.ResourceService;
import roses.scanner.config.properties.ScannerProperties;
import roses.scanner.modular.factory.ApiResourceFactory;

import java.util.Map;

/**
 * 监听项目初始化完毕,报告到服务器资源
 *
 * @author fengshuonan
 * @date 2018-02-06 13:05
 * Copyright: 2018赛鼎科技-版权所有
 */
public class ResourceReportListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        LogUtil.info("发送本系统的所有资源到share-system服务开始！");

        //获取当前系统的所有资源
        ApiResourceFactory resourceFactory = applicationContext.getBean(ApiResourceFactory.class);
        Map<String, Map<String, ResourceDefinition>> modularResources = resourceFactory.getModularResources();

        //发送资源到资源服务器
        ScannerProperties scannerProperties = applicationContext.getBean(ScannerProperties.class);
        ResourceService resourceService = applicationContext.getBean(ResourceService.class);
        resourceService.reportResources(scannerProperties.getAppCode(), modularResources);

        LogUtil.info("发送本系统的所有资源到share-system服务完毕！");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
