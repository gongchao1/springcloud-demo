package com.sckj.auth.service;


import com.baomidou.mybatisplus.service.IService;
import com.sckj.auth.entity.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangheduo
 * @since 2018-09-04
 */
public interface ISysUserService extends IService<SysUser> {

    //根据账号查询用户信息
    SysUser findUserByAccount(String account);

}
