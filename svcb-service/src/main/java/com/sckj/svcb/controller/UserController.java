package com.sckj.svcb.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sckj.svcb.entity.User;
import com.sckj.svcb.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangheduo
 * @since 2018-08-30
 */
@RestController
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping(value = "/user")
    public List<User> printServiceB() {
        Wrapper<User> wrapper = new EntityWrapper<User>();
       return userService.selectList(wrapper);
    }

}

