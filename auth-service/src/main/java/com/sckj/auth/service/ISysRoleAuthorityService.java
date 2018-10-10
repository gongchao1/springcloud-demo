package com.sckj.auth.service;


import com.baomidou.mybatisplus.service.IService;
import com.sckj.auth.entity.SysRoleAuthority;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangheduo
 * @since 2018-09-04
 */
public interface ISysRoleAuthorityService extends IService<SysRoleAuthority> {
    //根据角色id集合查询权限id集合
    Set<Long> findAuthByRoleids(List<Long> roleIds);
}
