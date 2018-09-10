package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.learn.entity.Extend.Instructor;
import com.learn.entity.Extend.President;
import com.learn.entity.LeaveRequest;
import com.learn.entity.Manager;
import com.learn.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/leaveRequest")
public class LeaveRequestController {

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String ToLeave(@RequestBody UserEntity userEntity){

        JSONObject jsonObject = new JSONObject();
        Manager instructor = new Instructor("辅");
        Manager president = new President("院");

        // 组织好责任链对象的关系
        instructor.setNextManager(president);

        // 开始请假操作
        LeaveRequest request = new LeaveRequest(userEntity.getUsername(), userEntity.getLeaveDay(), userEntity.getReason());
        instructor.handleRequest(request);

        jsonObject.put("data",JSONObject.toJSON("测试责任链"));
        jsonObject.put("msg",JSONObject.toJSON("true"));
        return jsonObject.toJSONString();
    }
}
