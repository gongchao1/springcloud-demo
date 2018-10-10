package com.sckj.auth.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sckj.auth.entity.SysUser;
import com.sckj.auth.exception.ExceptionEnum;
import com.sckj.auth.exception.ServiceException;
import com.sckj.auth.service.ISysUserService;
import com.sckj.auth.util.PageFactory;
import com.sckj.auth.util.Result;
import com.sckj.auth.util.ResultUtil;
import com.sckj.auth.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;


//    @GetMapping("/list")
//    public Result<SysUser> list(){
//        SysUser sysUser = new SysUser();
//        int c = 6/0;
//        if(true) {
//            throw new ServiceException(ExceptionEnum.UNKONW_ERROR);
//        }
//        sysUser.setName("fsfs");
//        sysUser.setEmail("4464@qq.com");
//        return ResultUtil.success(sysUser);
//    }

    /**
     * 分页查询用户列表
     */
    @GetMapping(value= "/list")
    public Map<String, Object> list(@RequestParam String order,
                                       @RequestParam String offset, @RequestParam String pageSize,
                                       @RequestParam String sort, @RequestParam String userName,
                                       @RequestParam String email){
        Wrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        if (ToolUtil.isNotEmpty(email)) wrapper.like("email", email);
        if(ToolUtil.isNotEmpty(userName)) wrapper.like("name",userName);
        Page<SysUser> page = new PageFactory<SysUser>().defaultPage(pageSize,offset,sort,order);
        List<SysUser> userList = sysUserService.selectPage(page, wrapper).getRecords();
        Long total = page.getTotal();
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",userList);
        result.put("total",total);
        return result;
    }

    /**
     * 添加用户
     */
    @PostMapping(value= "/add")
    public Result<String> add(SysUser sysUser){
        Wrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        wrapper.eq("account",sysUser.getAccount()).or().eq("email",sysUser.getEmail());
        List<SysUser> userList = sysUserService.selectList(wrapper);
        if(userList.size()>0) throw new ServiceException(ExceptionEnum.USER_ALREADY_REG);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(hashedPassword);
        boolean flag = sysUserService.insert(sysUser);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"添加用户失败！");
    }


    /**
     * 修改用户
     */
    @PostMapping(value= "/update")
    public Result<String> update(SysUser sysUser){
        Wrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        wrapper.eq("account",sysUser.getAccount()).or().eq("email",sysUser.getEmail());
        List<SysUser> userList = sysUserService.selectList(wrapper);
        if(userList.size()>0 && userList.get(0).getId()!=sysUser.getId()) throw new ServiceException(ExceptionEnum.USER_ALREADY_REG);
        boolean flag = sysUserService.updateById(sysUser);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"修改用户失败！");
    }

    /**
     * 删除用户
     */
    @GetMapping(value= "/delete")
    public Result<String> delete(String userIds){
        List<String> idList = Arrays.asList(userIds.split(","));
        boolean flag = sysUserService.deleteBatchIds(idList);
        if(flag){
            return ResultUtil.success();
        }
        return ResultUtil.error(-1,"删除用户失败！");
    }

    /**
     * 根据id查询用户
     */
    @GetMapping(value= "/getById")
    public Result<SysUser> getById(String userId){
        SysUser sysUser = sysUserService.selectById(userId);
        return ResultUtil.success(sysUser);
    }


}

