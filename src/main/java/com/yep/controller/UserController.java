package com.yep.controller;

import com.yep.bean.UserInfo;
import com.yep.exception.ResultResponse;
import com.yep.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/loginByName")
    public ResultResponse loginByName(@RequestParam String uname, @RequestParam String pwd){
        return userService.loginByName(uname, pwd);
    }

    @PostMapping("/loginByEmail")
    public ResultResponse loginByEmail(@RequestParam String email, @RequestParam String pwd){
        return userService.loginByEmail(email, pwd);
    }

    @PostMapping("/register")
    public ResultResponse registController(@RequestBody UserInfo newUser){
        return userService.registerUserInfo(newUser);
    }

    @PostMapping("/sendEmail")
    public ResultResponse sendEmail(@RequestParam String email) {
        return userService.sendVerifyWord(email);
    }

}
