package com.stylefeng.roses.core.chain.enums;

/**
 * 远程调用阶段类型枚举
 *
 * @author fengshuonan
 * @date 2018-05-15-下午3:51
 */
public enum RpcPhaseEnum {


    G1,     //网关发送请求

    G2,     //接收网关请求（切controller）

    P1,     //调用端发送请求（切consumer）

    P2,     //被调用端接收到请求（切provider）

    P3,     //被调用端发送响应成功

    P4,     //调用端接收到响应成功

    EP3,    //被调用端发送响应失败

    EP4,    //调用端接收到响应失败

    G3,     //控制器响应网关成功

    G4,     //网关接收到成功请求

    EG3,    //控制器发送失败的响应

    EG4,    //网关接收到错误响应

}
