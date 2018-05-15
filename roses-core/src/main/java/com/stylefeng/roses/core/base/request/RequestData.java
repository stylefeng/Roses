package com.stylefeng.roses.core.base.request;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.Serializable;
import java.util.*;

/**
 * 响应结果封装
 *
 * @author fengshuonan
 * @Date 2018/2/11 23:04
 */
public class RequestData implements Serializable {

    private static final long serialVersionUID = 9081406366569775542L;

    /**
     * 封装前端请求的json数据
     */
    private JSONObject data;

    /**
     * 请求号,标识请求的唯一性,用来定位多个服务调用链的日志记录
     */
    private String requestNo;

    /**
     * 客户端请求的ip
     */
    private String ip;

    /**
     * 客户端请求的地址
     */
    private String url;

    /**
     * 获取请求原始json
     */
    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    /**
     * 解析请求json为指定类
     */
    public <T> T parse(Class<T> clazz) {
        Map<String, Object> innerMap = this.data.getInnerMap();
        HashMap<String, Object> resultMap = new HashMap<>();
        Set<Map.Entry<String, Object>> entries = innerMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String fieldName = StrUtil.toCamelCase(key);
            resultMap.put(fieldName, entry.getValue());
        }
        return BeanUtil.mapToBean(resultMap, clazz, true);
    }

    /**
     * 解析请求json中指定key,并转化为指定类
     */
    public <T> T parse(String key, Class<T> clazz) {
        return this.data.getObject(key, clazz);
    }

    /**
     * <pre>
     * 解析请求json中指定key,并转化为string数组
     *
     * 例如请求json:
     *  {names:"zhangsan,lisi,wangwu", num:3}
     *
     * 执行:
     *  getStringArray("names")
     *
     * 运行结果得到一个string数组:
     *  ["zhangsan","lisi","wangwu"]
     *  </pre>
     */
    public String[] getStringArray(String key) {
        String values = this.data.getString(key);
        if (StrUtil.isBlank(values)) {
            return new String[]{};
        }
        return values.split(",");
    }

    /**
     * 解析指定key,转化为object数组
     */
    public Object[] getObjectArray(String key) {
        JSONArray jsonArray = this.data.getJSONArray(key);
        if (jsonArray != null) {
            return jsonArray.toArray();
        } else {
            return new Object[]{};
        }
    }

    /**
     * 解析指定key,转化为带有类类型的list
     */
    public <T> List<T> getList(String key, Class<T> clazz) {
        JSONArray jsonArray = this.data.getJSONArray(key);
        if (jsonArray != null) {
            return jsonArray.toJavaList(clazz);
        } else {
            return new ArrayList<T>();
        }
    }

    /**
     * 解析指定key,转化为指定数组
     */
    public <T> T[] getArray(String key, T[] array) {
        JSONArray jsonArray = this.data.getJSONArray(key);
        if (jsonArray != null) {
            return jsonArray.toArray(array);
        } else {
            return array;
        }
    }

    /**
     * 获取指定key对应的值
     */
    public Object get(String key) {
        return this.data.get(key);
    }

    /**
     * 获取指定key对应的string值
     */
    public String getString(String key) {
        return this.data.getString(key);
    }

    /**
     * 获取指定key对应的integer值
     */
    public Integer getInteger(String key) {
        return this.data.getInteger(key);
    }

    /**
     * 解析请求数据抓华为map
     */
    public Map<String, Object> parseMap() {
        return this.jsonObjet2Map(this.data);
    }

    private Map<String, Object> jsonObjet2Map(JSONObject jsonObj) {
        Map<String, Object> map = new HashMap();
        Set<Map.Entry<String, Object>> entries = jsonObj.getInnerMap().entrySet();
        Iterator<Map.Entry<String, Object>> itera = entries.iterator();
        Map.Entry<String, Object> entry = null;
        Object value = null;
        while (itera.hasNext()) {
            entry = itera.next();
            value = entry.getValue();
            map.put(entry.getKey(), traversalData(value));
        }
        return map;
    }

    private Object jsonArray2List(JSONArray array) {
        List<Object> list = new ArrayList<>();
        Iterator<Object> itera = array.iterator();
        Object value;
        while (itera.hasNext()) {
            value = itera.next();
            list.add(traversalData(value));
        }
        return list;
    }

    private Object traversalData(Object value) {
        if (value instanceof JSONObject) {
            return this.jsonObjet2Map((JSONObject) value);
        } else if (value instanceof JSONArray) {
            return this.jsonArray2List((JSONArray) value);
        } else {
            return value;
        }
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "data=" + data +
                ", requestNo='" + requestNo + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
