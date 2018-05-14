package roses.scanner.modular.factory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认权限资源工厂(map缓存)
 *
 * @author fengshuonan
 * @date 2018-01-03-下午3:00
 */
public class DefaultApiResourceFactory implements ApiResourceFactory {

    /**
     * 以资源编码为标识的存放
     */
    private Map<String, ResourceDefinition> resourceDefinitions = new ConcurrentHashMap<>();

    /**
     * 以模块名(控制器名),资源编码为标识的存放
     */
    private Map<String, Map<String, ResourceDefinition>> modularResourceDefinitions = new ConcurrentHashMap<>();

    /**
     * 模块名称(控制器),对应的编码和中文名称的字典
     */
    private Map<String, String> resourceCodeNameDict = new HashMap<>();

    /**
     * 资源url为标识存放资源声明
     */
    private Map<String, ResourceDefinition> urlDefineResources = new ConcurrentHashMap<>();

    @Override
    public synchronized void registerDefinition(List<ResourceDefinition> apiResource) {
        if (apiResource != null || apiResource.size() > 0) {
            for (ResourceDefinition resourceDefinition : apiResource) {
                ResourceDefinition alreadyFlag = resourceDefinitions.get(resourceDefinition.getCode());
                if (alreadyFlag != null) {
                    throw new RuntimeException("资源扫描过程中存在重复资源！\n已存在资源：" + alreadyFlag + "\n新资源为： " + resourceDefinition);
                }
                resourceDefinitions.put(resourceDefinition.getCode(), resourceDefinition);
                urlDefineResources.put(resourceDefinition.getUrl(), resourceDefinition);

                //store modularResourceDefinitions
                Map<String, ResourceDefinition> modularResources = modularResourceDefinitions.get(StrUtil.toUnderlineCase(resourceDefinition.getModularCode()));
                if (modularResources == null) {
                    modularResources = new HashMap<>();
                    modularResources.put(resourceDefinition.getCode(), resourceDefinition);
                    modularResourceDefinitions.put(StrUtil.toUnderlineCase(resourceDefinition.getModularCode()), modularResources);
                } else {
                    modularResources.put(resourceDefinition.getCode(), resourceDefinition);
                }

                //添加资源code-中文名称字典
                this.bindResourceName(resourceDefinition.getCode(), resourceDefinition.getName());
            }
        }
    }

    @Override
    public ResourceDefinition getResource(String resourceCode) {
        return resourceDefinitions.get(resourceCode);
    }

    @Override
    public List<ResourceDefinition> getAllResources() {
        Set<Map.Entry<String, ResourceDefinition>> entries = resourceDefinitions.entrySet();
        ArrayList<ResourceDefinition> resourceDefinitions = new ArrayList<>();
        for (Map.Entry<String, ResourceDefinition> entry : entries) {
            resourceDefinitions.add(entry.getValue());
        }
        return resourceDefinitions;
    }

    @Override
    public List<ResourceDefinition> getResourcesByModularCode(String code) {
        Map<String, ResourceDefinition> stringResourceDefinitionMap = modularResourceDefinitions.get(code);
        ArrayList<ResourceDefinition> resourceDefinitions = new ArrayList<>();
        for (String key : stringResourceDefinitionMap.keySet()) {
            ResourceDefinition resourceDefinition = stringResourceDefinitionMap.get(key);
            resourceDefinitions.add(resourceDefinition);
        }
        return resourceDefinitions;
    }

    @Override
    public String getResourceName(String code) {
        return resourceCodeNameDict.get(code);
    }

    @Override
    public void bindResourceName(String code, String name) {
        resourceCodeNameDict.putIfAbsent(code, name);
    }

    @Override
    public Map<String, Map<String, ResourceDefinition>> getModularResources() {
        return this.modularResourceDefinitions;
    }

    @Override
    public String getResourceUrl(String code) {
        ResourceDefinition resourceDefinition = this.resourceDefinitions.get(code);
        if (resourceDefinition == null) {
            return null;
        } else {
            return resourceDefinition.getUrl();
        }
    }

    @Override
    public ResourceDefinition getResourceByUrl(String url) {
        return this.urlDefineResources.get(url);
    }
}
