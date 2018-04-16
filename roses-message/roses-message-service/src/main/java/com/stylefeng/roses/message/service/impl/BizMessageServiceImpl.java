package com.stylefeng.roses.message.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.roses.api.message.model.BizMessage;
import com.stylefeng.roses.message.mapper.BizMessageMapper;
import com.stylefeng.roses.message.service.IBizMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-04-16
 */
@Service
public class BizMessageServiceImpl extends ServiceImpl<BizMessageMapper, BizMessage> implements IBizMessageService {

}
