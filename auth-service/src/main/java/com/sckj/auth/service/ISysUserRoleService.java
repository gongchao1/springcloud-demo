package com.sckj.auth.service;


import com.baomidou.mybatisplus.service.IService;
import com.sckj.auth.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangheduo
 * @since 2018-09-04
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    //根据用户id查询角色id集合
    List<Long> findRoleidsByUserid(long userid);
}
