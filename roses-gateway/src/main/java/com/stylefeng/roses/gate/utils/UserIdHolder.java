package com.stylefeng.roses.gate.utils;

/**
 * userId holder
 *
 * @author fengshuonan
 * @date 2017-11-14-上午11:14
 */
public class UserIdHolder {

    private static ThreadLocal<String> tl = new ThreadLocal();

    public static void put(String userId) {
        tl.set(userId);
    }

    public static String get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
