package roses.scanner.modular.model;

import java.util.List;

/**
 * 资源树
 *
 * @author fengshuonan
 * @date 2018-01-11 14:56
 */
public class ResourceTreeNode {

    /**
     * 资源中文名称
     */
    private String name;

    /**
     * 资源的编码
     */
    private String code;

    /**
     * 资源子节点
     */
    private List<ResourceTreeNode> children;

    public ResourceTreeNode() {
    }

    public ResourceTreeNode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public ResourceTreeNode(String name, String code, List<ResourceTreeNode> children) {
        this.name = name;
        this.code = code;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ResourceTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTreeNode> children) {
        this.children = children;
    }
}
