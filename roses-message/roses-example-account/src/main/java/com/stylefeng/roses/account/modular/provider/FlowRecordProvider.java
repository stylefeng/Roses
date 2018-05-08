package com.stylefeng.roses.account.modular.provider;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.roses.account.modular.service.IFlowRecordService;
import com.stylefeng.roses.api.account.FlowRecordApi;
import com.stylefeng.roses.api.account.model.FlowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 消息服务的接口
 *
 * @author stylefeng
 * @Date 2018/4/16 21:47
 */
@RestController
public class FlowRecordProvider implements FlowRecordApi {

    @Autowired
    private IFlowRecordService flowRecordService;

    @Override
    public FlowRecord findOrderFlowRecord(@RequestParam("orderId") Long orderId) {

        List<FlowRecord> orders = flowRecordService.selectList(
                new EntityWrapper<FlowRecord>().eq("order_id", orderId));

        if (orders != null && !orders.isEmpty()) {
            return orders.get(0);
        } else {
            return null;
        }

    }

}