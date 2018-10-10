package com.sckj.svcb.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sckj.svcb.dao.UserMapper;
import com.sckj.svcb.entity.User;
import com.sckj.svcb.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangheduo
 * @since 2018-08-30
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
