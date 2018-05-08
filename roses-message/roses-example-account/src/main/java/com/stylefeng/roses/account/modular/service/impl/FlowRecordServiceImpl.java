package com.stylefeng.roses.account.modular.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.roses.account.modular.mapper.FlowRecordMapper;
import com.stylefeng.roses.account.modular.service.IFlowRecordService;
import com.stylefeng.roses.api.account.model.FlowRecord;
import com.stylefeng.roses.api.common.exception.CoreExceptionEnum;
import com.stylefeng.roses.api.common.exception.ServiceException;
import com.stylefeng.roses.api.order.GoodsFlowParam;
import com.stylefeng.roses.core.util.ToolUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 流水记录 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-05
 */
@Service
public class FlowRecordServiceImpl extends ServiceImpl<FlowRecordMapper, FlowRecord> implements IFlowRecordService {

    @Override
    public void recordFlow(GoodsFlowParam goodsFlowParam) {

        if (goodsFlowParam == null) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }

        if (ToolUtil.isOneEmpty(goodsFlowParam.getUserId(), goodsFlowParam.getGoodsName(), goodsFlowParam.getSum())) {
            throw new ServiceException(CoreExceptionEnum.REQUEST_NULL);
        }

        FlowRecord flowRecord = new FlowRecord();
        flowRecord.setUserId(goodsFlowParam.getUserId());
        flowRecord.setSum(goodsFlowParam.getSum());
        flowRecord.setOrderId(goodsFlowParam.getId());
        flowRecord.setName(goodsFlowParam.getGoodsName());
        flowRecord.setCreateTime(new Date());

        this.insert(flowRecord);
    }
}
