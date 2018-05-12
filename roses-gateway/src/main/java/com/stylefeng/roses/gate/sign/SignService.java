package com.stylefeng.roses.gate.sign;

import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.core.context.AuthServiceConsumer;
import com.stylefeng.roses.core.util.SignUtil;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.gate.exception.SignExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 签名缓存服务
 *
 * @author fengshuonan
 * @date 2018-05-08-上午10:09
 */
@Component
public class SignService {

    /**
     * 签名缓存服务标识前缀
     */
    private static String SIGN_CACHE_PREFIX_NAME = "SIGN_CACHE_";

    /**
     * 默认签名缓存失效时间 120秒
     */
    private static long DEFAULT_SIGN_CACHE_EXPIRED_SECONDS = 120L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    /**
     * 验证签名
     *
     * @author fengshuonan
     * @Date 2018/5/8 上午10:15
     */
    public boolean validateSign(String appId, String timestamp, String token, String data) {

        //获取appId对应的secret
        String appSecret = getAppSecret(appId);

        //校验签名
        return SignUtil.validateSign(appId, appSecret, timestamp, data, token);
    }

    /**
     * 获取appSecret（先从缓存读，如果缓存没有就远程调用服务获取签名）
     *
     * @author fengshuonan
     * @Date 2018/5/8 上午10:18
     */
    private String getAppSecret(String appId) {

        String cacheKey = SIGN_CACHE_PREFIX_NAME + appId;
        BoundValueOperations<String, Object> appSecretOpt = redisTemplate.boundValueOps(cacheKey);

        String appSecretCache = appSecretOpt.get() == null ? null : appSecretOpt.get().toString();
        if (ToolUtil.isEmpty(appSecretCache)) {

            String secretByAppId = authServiceConsumer.getSecretByAppId(appId);
            if (secretByAppId == null) {
                throw new ServiceException(SignExceptionEnum.GET_SECRET_ERROR);
            } else {
                appSecretOpt.set(secretByAppId);
                appSecretOpt.expire(DEFAULT_SIGN_CACHE_EXPIRED_SECONDS, TimeUnit.SECONDS);
                return secretByAppId;
            }
        } else {
            Long expire = appSecretOpt.getExpire();
            appSecretOpt.expire(expire, TimeUnit.SECONDS);
            return appSecretOpt.get().toString();
        }
    }

    public static void main(String[] args) {
        String s = SignUtil.generateSign("aaa", "secret", "2018-05-08 16:29:00", "{appId:1}");
        System.out.println(s);
    }
}