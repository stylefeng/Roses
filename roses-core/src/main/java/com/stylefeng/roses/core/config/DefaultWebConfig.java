package com.stylefeng.roses.core.config;

import com.stylefeng.roses.core.support.DateTimeKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
public class DefaultWebConfig {
	
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;
    
    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }
    
    /**
     * 日期转换
     */
    public class StringToDateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String dateString) {
        	return DateTimeKit.parse(dateString);
        }
        
    }
}
