package com.stylefeng.roses.logger.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.roses.api.log.entity.RosesTraceLog;
import org.springframework.stereotype.Repository;

/**
 * 日志链mapper类
 *
 * @author yaoliguo
 * @date 2018-05-16 09:26
 */
@Repository
public interface TraceLogMapper extends BaseMapper<RosesTraceLog> {

}
