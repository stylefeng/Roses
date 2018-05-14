package roses.scanner.modular.factory;


import com.stylefeng.roses.api.auth.ResourceDefinition;

import java.util.List;
import java.util.Map;

/**
 * 权限资源生产工厂
 *
 * @author fengshuonan
 * @date 2018-01-03-下午3:00
 */
public interface ApiResourceFactory {

    /**
     * 存储资源
     */
    void registerDefinition(List<ResourceDefinition> apiResource);

    /**
     * 获取资源他通过资源编码
     */
    ResourceDefinition getResource(String resourceCode);

    /**
     * 获取当前应用所有资源
     */
    List<ResourceDefinition> getAllResources();

    /**
     * 通过模块编码获取资源
     */
    List<ResourceDefinition> getResourcesByModularCode(String code);

    /**
     * 通过资源code获取资源中文名称
     */
    String getResourceName(String code);

    /**
     * 添加资源的code和名称
     */
    void bindResourceName(String code, String name);

    /**
     * 获取所有模块资源
     */
    Map<String, Map<String, ResourceDefinition>> getModularResources();

    /**
     * 通过编码获取url
     */
    String getResourceUrl(String code);

    /**
     * 通过url获取资源声明
     */
    ResourceDefinition getResourceByUrl(String url);
}
