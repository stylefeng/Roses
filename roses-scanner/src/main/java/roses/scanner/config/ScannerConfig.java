package roses.scanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roses.scanner.modular.factory.DefaultApiResourceFactory;
import roses.scanner.modular.service.ResourceCollectService;
import roses.scanner.config.properties.ScannerProperties;
import roses.scanner.modular.ApiResourceScaner;
import roses.scanner.modular.factory.ApiResourceFactory;
import roses.scanner.modular.listener.ResourceReportListener;

/**
 * 扫描器默认配置
 *
 * @author fengshuonan
 * @date 2018-02-06 17:25
 * Copyright: 2018赛鼎科技-版权所有
 */
@Configuration
public class ScannerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    @ConfigurationProperties(prefix = "share.scanner")
    public ScannerProperties scannerProperties() {
        return new ScannerProperties();
    }

    /**
     * 资源工厂
     */
    @Bean
    public ApiResourceFactory apiResourceFactory() {
        return new DefaultApiResourceFactory();
    }

    /**
     * 资源收集服务
     */
    @Bean
    public ResourceCollectService resourceCollectService(ApiResourceFactory apiResourceFactory, ScannerProperties scannerProperties) {
        return new ResourceCollectService(apiResourceFactory, scannerProperties);
    }

    /**
     * 资源扫描器
     */
    @Bean
    public ApiResourceScaner apiResourceScaner(ApiResourceFactory apiResourceFactory, ScannerProperties scannerProperties) {
        return new ApiResourceScaner(apiResourceFactory, scannerProperties, applicationName);
    }

    /**
     * 资源扫描之后的资源汇报操作（向share-system服务）
     */
    @Bean
    @ConditionalOnProperty(prefix = "share.scanner", name = "open", havingValue = "true")
    public ResourceReportListener resourceReportListener() {
        return new ResourceReportListener();
    }
}
