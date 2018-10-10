package com.sckj.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by wangheduo on 2018/9/2
 */
@RestController
public class UserController {

    //微服务获取授权信息，注意是security封装的user对象
    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
