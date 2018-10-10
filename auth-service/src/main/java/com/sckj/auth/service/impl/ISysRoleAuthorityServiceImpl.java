package com.sckj.auth.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sckj.auth.entity.SysRoleAuthority;
import com.sckj.auth.mapper.SysRoleAuthorityMapper;
import com.sckj.auth.service.ISysRoleAuthorityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class ISysRoleAuthorityServiceImpl extends ServiceImpl<SysRoleAuthorityMapper, SysRoleAuthority> implements ISysRoleAuthorityService {

    @Override
    public Set<Long> findAuthByRoleids(List<Long> roleIds) {
        Wrapper<SysRoleAuthority> wrapper = new EntityWrapper<SysRoleAuthority>();
        wrapper.in("sys_roleid",roleIds);
        List<SysRoleAuthority> roleAuthList = this.selectList(wrapper);
        Set<Long> result = new HashSet<Long>();//利用set集合去重
        for (SysRoleAuthority sysRoleAuthority : roleAuthList) {
            if(sysRoleAuthority==null) continue;
            result.add(sysRoleAuthority.getAuthorityId());
        }
        return result;
    }
}
