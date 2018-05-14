package roses.scanner.config.properties;

/**
 * 扫描的常量
 *
 * @author fengshuonan
 * @date 2018-01-03 21:39
 */
public class ScannerProperties {

    /**
     * 资源扫描开关
     */
    private Boolean open;

    /**
     * 被扫描应用的名称
     */
    private String appName;

    /**
     * 应用的编码
     */
    private String appCode;

    /**
     * 链接符号
     */
    private String linkSymbol = "$";

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLinkSymbol() {
        return linkSymbol;
    }

    public void setLinkSymbol(String linkSymbol) {
        this.linkSymbol = linkSymbol;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
