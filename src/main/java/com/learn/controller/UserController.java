package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.learn.entity.UserEntity;
import com.learn.service.UserService;
import com.learn.utils.MD5Util;
import com.learn.utils.PageBeanUtil;
import com.learn.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")//Contoller下所有接口统一入口
public class UserController {

    @Autowired
    UserService userService;

    //映射一个action
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转
    public String getUser() {
        //创建一个JSONObject，用于json输出
        JSONObject jsonObject = new JSONObject();
        List<UserEntity> allUserEntityList = userService.getAllUser();
        if(allUserEntityList != null){
           jsonObject.put("data",JSONObject.toJSON(allUserEntityList));
           jsonObject.put("msg",JSONObject.toJSON("true"));
           return jsonObject.toJSONString();
        }else{
           jsonObject.put("data",JSONObject.toJSON("数据为空！"));
           jsonObject.put("msg",JSONObject.toJSON("false"));
           return jsonObject.toJSONString();
        }
    }

    //分页显示
    @RequestMapping(value = "/getUserByPageId/{pageId}",method = RequestMethod.GET)
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转
    public String getUserByPageId(@PathVariable(value = "pageId") int pageId) {
        //创建一个JSONObject，用于json输出
        JSONObject jsonObject = new JSONObject();
        PageBeanUtil<UserEntity> pageBeanUtilList = userService.queryForPage(5,pageId);
        System.out.println("取到数量："+pageBeanUtilList.getList().size());
        if(pageBeanUtilList.getList().size() != 0){
            jsonObject.put("data",JSONObject.toJSON(pageBeanUtilList));
            jsonObject.put("msg",JSONObject.toJSON("true"));
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("data",JSONObject.toJSON("页数超出，无数据！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/getOne/{uid}",method = RequestMethod.GET)
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转
    public String getUserByUid(@PathVariable(value = "uid") int id) {
        //创建一个JSONObject，用于json输出
        JSONObject jsonObject = new JSONObject();
        List<UserEntity> userEntityList = userService.getUserByUid(id);
        if( userEntityList != null){
            jsonObject.put("data",JSONObject.toJSON(userEntityList));
            jsonObject.put("msg",JSONObject.toJSON("true"));
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("data",JSONObject.toJSON("无数据！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
            return jsonObject.toJSONString();
        }
    }

    /** 登陆验证 登陆成功记录 uid username 进 Session
     * @param user
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转
    public String Login(@RequestBody UserEntity user, HttpSession httpSession) {
        JSONObject jsonObject = new JSONObject();
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        //登陆时 同时获取uid 为了存入session
        UserEntity userEntity = userService.login(user);
        if(userEntity != null){
            System.out.println("session:"+httpSession.getId());
            httpSession.setAttribute("username", user.getUsername());
            httpSession.setAttribute("uid", userEntity.getUid());

            System.out.println("session_username:"+httpSession.getAttribute("username"));
            System.out.println("session_uid:"+httpSession.getAttribute("uid"));

            jsonObject.put("data",JSONObject.toJSON(userEntity));
            jsonObject.put("msgs",JSONObject.toJSON("true"));
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("data",JSONObject.toJSON("登陆失败！"));
            jsonObject.put("msgs",JSONObject.toJSON("false"));
            return jsonObject.toJSONString();
        }
    }

    /** Session获取登陆状态 uid + username
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/getLoginStatus",method = RequestMethod.GET)
    @ResponseBody //表示直接输出返回内容，不进行jsp或html跳转
    public String getLoginStatus(HttpSession httpSession) {
        JSONObject jsonObject = new JSONObject();
        //打印一下session
        System.out.println("session:"+httpSession.getId());
        System.out.println("session_username:"+httpSession.getAttribute("username"));
        if(httpSession.getAttribute("username") != null){
            jsonObject.put("username",JSONObject.toJSON(httpSession.getAttribute("username")));
            jsonObject.put("uid",JSONObject.toJSON(httpSession.getAttribute("uid")));
            jsonObject.put("msg",JSONObject.toJSON("true"));
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("data",JSONObject.toJSON("未登录状态！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转
    public String Register(@RequestBody UserEntity user) {
        JSONObject jsonObject = new JSONObject();
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        if(userService.Register(user)){
            jsonObject.put("data",JSONObject.toJSON("注册成功！"));
            jsonObject.put("msg",JSONObject.toJSON("true"));
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("data",JSONObject.toJSON("注册失败！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public String updateUser(@RequestBody UserEntity userEntity){
        JSONObject jsonObject = new JSONObject();
        if (userService.update(userEntity)){
            jsonObject.put("data",JSONObject.toJSON("更新成功！"));
            jsonObject.put("msg",JSONObject.toJSON("true"));
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("data",JSONObject.toJSON("更新失败！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转
    public String Delete(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if(userService.Delete(id)){
            jsonObject.put("data",JSONObject.toJSON("删除成功！"));
            jsonObject.put("msg",JSONObject.toJSON("true"));
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("data",JSONObject.toJSON("删除失败！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
            return jsonObject.toJSONString();
        }
    }
}