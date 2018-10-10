package com.sckj.auth.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sckj.auth.entity.SysRole;
import com.sckj.auth.mapper.SysRoleMapper;
import com.sckj.auth.service.ISysRoleService;
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
public class ISysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

}
