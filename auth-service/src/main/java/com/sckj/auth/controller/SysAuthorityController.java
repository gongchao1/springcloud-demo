package com.sckj.auth.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sckj.auth.entity.SysAuthority;
import com.sckj.auth.exception.ExceptionEnum;
import com.sckj.auth.exception.ServiceException;
import com.sckj.auth.service.ISysAuthorityService;
import com.sckj.auth.util.Result;
import com.sckj.auth.util.ResultUtil;
import com.sckj.auth.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangheduo
 * @since 2018-09-05
 */
@RestController
@RequestMapping("/sysAuth")
public class SysAuthorityController {
    @Autowired
    private ISysAuthorityService sysAuthorityService;

    /**
     * 查询权限列表
     */
    @GetMapping(value= "/list")
    public List<SysAuthority> list(@RequestParam String name,
                                    @RequestParam String code){
        Wrapper<SysAuthority> wrapper = new EntityWrapper<SysAuthority>();
        if (ToolUtil.isNotEmpty(name)) wrapper.like("name", name);
        if (ToolUtil.isNotEmpty(code)) wrapper.like("code", code);
        List<SysAuthority> authList = sysAuthorityService.selectList(wrapper);
        return authList;
    }

    /**
     * 添加权限
     */
    @PostMapping(value= "/add")
    public Result<String> add(SysAuthority SysAuthority){
        Wrapper<SysAuthority> wrapper = new EntityWrapper<SysAuthority>();
        wrapper.eq("code",SysAuthority.getCode()).or().eq("url",SysAuthority.getUrl());
        List<SysAuthority> authList = sysAuthorityService.selectList(wrapper);
        if(authList.size()>0) throw new ServiceException(ExceptionEnum.AUTH_ALREADY_EXIST);
        boolean flag = sysAuthorityService.insert(SysAuthority);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"添加权限失败！");
    }

    /**
     * 修改权限
     */
    @PostMapping(value= "/update")
    public Result<String> update(SysAuthority SysAuthority){
        Wrapper<SysAuthority> wrapper = new EntityWrapper<SysAuthority>();
        wrapper.eq("code",SysAuthority.getCode()).or().eq("url",SysAuthority.getUrl());
        List<SysAuthority> authList = sysAuthorityService.selectList(wrapper);
        if(authList.size()>0&&authList.get(0).getId()!= SysAuthority.getId()) throw new ServiceException(ExceptionEnum.AUTH_ALREADY_EXIST);
        boolean flag = sysAuthorityService.updateById(SysAuthority);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"修改权限失败！");
    }

    /**
     * 删除权限
     */
    @GetMapping(value= "/delete")
    public Result<String> delete(String authIds){
        List<String> idList = Arrays.asList(authIds.split(","));
        boolean flag = sysAuthorityService.deleteBatchIds(idList);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"删除权限失败！");
    }

    /**
     * 根据id查询权限
     */
    @GetMapping(value= "/getById")
    public Result<SysAuthority> getById(String authId){
        SysAuthority sysAuthority = sysAuthorityService.selectById(authId);
        return ResultUtil.success(sysAuthority);
    }




}

