package com.stylefeng.roses.logger.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.roses.api.log.entity.RosesTraceLog;
import com.stylefeng.roses.logger.mapper.TraceLogMapper;
import com.stylefeng.roses.logger.service.TraceLogService;
import org.springframework.stereotype.Service;

/**
 * 日志链服务实现类
 *
 * @author yaoliguo
 * @date 2018-05-16 09:30
 */
@Service
public class TraceLogServiceImpl extends ServiceImpl<TraceLogMapper, RosesTraceLog> implements TraceLogService {

}
