package com.stylefeng.roses.message.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.roses.api.message.model.ReliableMessage;
import com.stylefeng.roses.message.mapper.ReliableMessageMapper;
import com.stylefeng.roses.message.service.IReliableMessageService;
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
public class ReliableMessageServiceImpl extends ServiceImpl<ReliableMessageMapper, ReliableMessage> implements IReliableMessageService {

}
