package com.sckj.auth.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sckj.auth.entity.SysRole;
import com.sckj.auth.exception.ExceptionEnum;
import com.sckj.auth.exception.ServiceException;
import com.sckj.auth.service.ISysRoleService;
import com.sckj.auth.util.PageFactory;
import com.sckj.auth.util.Result;
import com.sckj.auth.util.ResultUtil;
import com.sckj.auth.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangheduo
 * @since 2018-09-05
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 分页查询角色列表
     */
    @GetMapping(value= "/list")
    public Map<String, Object> list(@RequestParam String order,
                                    @RequestParam String offset, @RequestParam String pageSize,
                                    @RequestParam String sort, @RequestParam String roleName,
                                    @RequestParam String desc){
        Wrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        if (ToolUtil.isNotEmpty(roleName)) wrapper.like("name", roleName);
        if (ToolUtil.isNotEmpty(desc)) wrapper.like("desc", desc);
        Page<SysRole> page = new PageFactory<SysRole>().defaultPage(pageSize,offset,sort,order);
        List<SysRole> userList = sysRoleService.selectPage(page, wrapper).getRecords();
        Long total = page.getTotal();
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",userList);
        result.put("total",total);
        return result;
    }

    /**
     * 添加角色
     */
    @PostMapping(value= "/add")
    public Result<String> add(SysRole sysRole){
        Wrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        wrapper.eq("name",sysRole.getName());
        List<SysRole> roleList = sysRoleService.selectList(wrapper);
        if(roleList.size()>0) throw new ServiceException(ExceptionEnum.ROLE_ALREADY_EXIST);
        boolean flag = sysRoleService.insert(sysRole);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"创建角色失败！");
    }

    /**
     * 修改角色
     */
    @PostMapping(value= "/update")
    public Result<String> update(SysRole sysRole){
        Wrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
        wrapper.eq("name",sysRole.getName());
        List<SysRole> roleList = sysRoleService.selectList(wrapper);
        if(roleList.size()>0&&roleList.get(0).getId()!=sysRole.getId())throw new ServiceException(ExceptionEnum.ROLE_ALREADY_EXIST);
        boolean flag = sysRoleService.updateById(sysRole);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"修改角色失败！");
    }

    /**
     * 批量删除角色
     */
    @GetMapping(value= "/delete")
    public Result<String> delete(String roleIds){
        List<String> idList = Arrays.asList(roleIds.split(","));
        boolean flag = sysRoleService.deleteBatchIds(idList);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"删除角色失败！");
    }

    /**
     * 根据id查询角色
     */
    @GetMapping(value= "/getById")
    public Result<SysRole> getById(String roleId){
        SysRole sysRole = sysRoleService.selectById(roleId);
        return ResultUtil.success(sysRole);
    }

}

