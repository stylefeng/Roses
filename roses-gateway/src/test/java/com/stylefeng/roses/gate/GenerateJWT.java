package com.stylefeng.roses.gate;


import com.stylefeng.roses.gate.utils.JwtTokenUtil;

/**
 * 生成jwt的测试类
 */
public class GenerateJWT {

    public static void main(String[] args) {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil("mySecret", 60 * 60 * 24);
        System.out.println(jwtTokenUtil.generateToken("1"));
    }
}
