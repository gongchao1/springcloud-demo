package com.sckj.auth.security;

//import com.hfcsbc.domain.SysUser;
//import com.hfcsbc.repository.SysUserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;

import com.sckj.auth.entity.SysAuthority;
import com.sckj.auth.entity.SysUser;
import com.sckj.auth.service.ISysAuthorityService;
import com.sckj.auth.service.ISysRoleAuthorityService;
import com.sckj.auth.service.ISysUserRoleService;
import com.sckj.auth.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangheduo on 2018/9/4
 */
@Service("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysRoleAuthorityService roleAuthorityService;
    @Autowired
    private ISysAuthorityService authorityService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //用户权限集合
        Set<GrantedAuthority> userAuthotities = new HashSet<>();
        //根据账户查询用户信息
        SysUser sysUser = userService.findUserByAccount(username);
        //根据userid查询角色id集合
        List<Long> roleIds = userRoleService.findRoleidsByUserid(sysUser.getId());
        if (roleIds != null && roleIds.size() > 0) {
            //根据角色id集合查询权限id集合
            Set<Long> authIds = roleAuthorityService.findAuthByRoleids(roleIds);
            if (authIds != null && authIds.size() > 0) {
                //根据权限id集合查询权限信息
                List<SysAuthority> authList = authorityService.findAuthByIds(authIds);
                if (authList != null && authList.size() > 0) {
                    for (SysAuthority sysAuthority : authList) {
                        userAuthotities.add(new SimpleGrantedAuthority(sysAuthority.getCode()));
                    }
                }
            }
        }
        if(sysUser == null){
            throw new UsernameNotFoundException("用户名"  + username + "不存在");
        }
        return new User(sysUser.getAccount(), sysUser.getPassword(), userAuthotities);
    }
}
