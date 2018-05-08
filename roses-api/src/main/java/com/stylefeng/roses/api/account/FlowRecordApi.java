package com.stylefeng.roses.api.account;


import com.stylefeng.roses.api.account.model.FlowRecord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 消息服务的接口
 *
 * @author stylefeng
 * @Date 2018/4/16 21:47
 */
@RequestMapping("/api/flowRecord")
public interface FlowRecordApi {

    /**
     * 根据订单id查询流水记录
     */
    @RequestMapping("/preSaveMessage")
    FlowRecord findOrderFlowRecord(@RequestParam("orderId") Long orderId);

}
