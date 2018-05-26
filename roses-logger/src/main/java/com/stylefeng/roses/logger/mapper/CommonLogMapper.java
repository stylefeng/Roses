package com.stylefeng.roses.logger.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.roses.api.log.entity.RosesCommanLog;
import org.springframework.stereotype.Repository;


/**
 * 日志dao类
 *
 * @author yaoliguo
 * @date 2018-04-24 13:46
 */
@Repository
public interface CommonLogMapper extends BaseMapper<RosesCommanLog> {

}
