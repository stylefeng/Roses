package com.stylefeng.roses.core.context;


import com.stylefeng.roses.core.base.request.RequestData;

/**
 * 请求数据的临时容器
 *
 * @author yaoliguo
 * @date 2018-05-04 11:33
 */
public class RequestDataHolder {

    private static ThreadLocal<RequestData> holder = new ThreadLocal<>();

    public static void put(RequestData s) {
        holder.set(s);
    }

    public static RequestData get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
