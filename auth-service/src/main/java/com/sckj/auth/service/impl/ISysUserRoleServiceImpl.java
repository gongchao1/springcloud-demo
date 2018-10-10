package com.sckj.auth.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sckj.auth.entity.SysUserRole;
import com.sckj.auth.mapper.SysUserRoleMapper;
import com.sckj.auth.service.ISysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

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
public class ISysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public List<Long> findRoleidsByUserid(long userid) {
        Wrapper<SysUserRole> wrapper = new EntityWrapper<SysUserRole>();
        wrapper.eq("user_id",userid);
        List<SysUserRole> sysUserRoleList =  this.selectList(wrapper);
        List<Long> result = new LinkedList<Long>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            if (sysUserRole == null) continue;
            result.add(sysUserRole.getRoleId());
        }
        return result;
    }
}
