package com.stylefeng.roses.account.modular.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.roses.api.account.model.FlowRecord;
import com.stylefeng.roses.api.order.GoodsFlowParam;

/**
 * <p>
 * 流水记录 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-05
 */
public interface IFlowRecordService extends IService<FlowRecord> {

    /**
     * 记录订单流水
     *
     * @author stylefeng
     * @Date 2018/5/6 14:00
     */
    void recordFlow(GoodsFlowParam goodsFlowParam);

}
