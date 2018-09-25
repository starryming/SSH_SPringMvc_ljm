package com.learn.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.learn.entity.UserEntity;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String home(){
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public List<UserEntity> json(){
        return userService.getAllUser();
    }

    @RequestMapping("/admin")
    @ResponseBody
    public List<String> admin(){
        return Arrays.asList("zhangsan", "lisi", "wangwu");
    }
}
