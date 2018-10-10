package com.sckj.auth.service;


import com.baomidou.mybatisplus.service.IService;
import com.sckj.auth.entity.SysAuthority;

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
public interface ISysAuthorityService extends IService<SysAuthority> {
    //根据权限id集合查询权限信息
    List<SysAuthority> findAuthByIds(Set<Long> ids);
}
