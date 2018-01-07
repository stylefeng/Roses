package com.stylefeng.roses.core.context;

import com.stylefeng.roses.api.auth.api.AuthServiceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 用户中心服务
 *
 * @author fengshuonan
 * @date 2017-11-09-下午7:52
 */
@FeignClient("auth-service")
public interface AuthServiceConsumer extends AuthServiceApi {

}
