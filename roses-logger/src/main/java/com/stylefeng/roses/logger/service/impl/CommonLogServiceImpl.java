package com.stylefeng.roses.logger.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.roses.api.log.entity.RosesCommanLog;
import com.stylefeng.roses.logger.mapper.CommonLogMapper;
import com.stylefeng.roses.logger.service.CommonLogService;
import org.springframework.stereotype.Service;


/**
 * 日志实现类
 *
 * @author yaoliguo
 * @date 2018-04-24 13:48
 */
@Service
public class CommonLogServiceImpl extends ServiceImpl<CommonLogMapper, RosesCommanLog> implements CommonLogService {

}
