package com.sckj.auth.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sckj.auth.entity.SysAuthority;
import com.sckj.auth.mapper.SysAuthorityMapper;
import com.sckj.auth.service.ISysAuthorityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ISysAuthorityServiceImpl extends ServiceImpl<SysAuthorityMapper, SysAuthority> implements ISysAuthorityService {

    @Override
    public List<SysAuthority> findAuthByIds(Set<Long> ids) {
        Wrapper<SysAuthority> wrapper = new EntityWrapper<SysAuthority>();
        wrapper.in("id",ids);
        return this.selectList(wrapper);
    }
}
