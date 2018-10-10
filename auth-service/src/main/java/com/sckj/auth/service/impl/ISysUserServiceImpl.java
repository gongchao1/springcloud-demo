package com.sckj.auth.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sckj.auth.entity.SysUser;
import com.sckj.auth.mapper.SysUserMapper;
import com.sckj.auth.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangheduo
 * @since 2018-09-04
 */
@Service
@Transactional
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser findUserByAccount(String account) {
        Wrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        wrapper.eq("account",account);
        return this.selectOne(wrapper);
    }
}
