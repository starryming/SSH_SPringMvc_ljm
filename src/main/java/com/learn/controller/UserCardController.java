package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.learn.entity.UsercardEntity;
import com.learn.service.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usercard")
public class UserCardController {

    @Autowired
    UserCardService userCardService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public String addUserCard(@RequestBody UsercardEntity usercardEntity){
        JSONObject jsonObject = new JSONObject();
        if(userCardService.add(usercardEntity)){
            jsonObject.put("data",JSONObject.toJSON("添加成功！"));
            jsonObject.put("msg",JSONObject.toJSON("true"));
        }else{
            jsonObject.put("data",JSONObject.toJSON("插入失败！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String delUserCard(@PathVariable("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(userCardService.delete(id)){
            jsonObject.put("data",JSONObject.toJSON("删除成功！"));
            jsonObject.put("msg",JSONObject.toJSON("true"));
        }else{
            jsonObject.put("data",JSONObject.toJSON("删除失败！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public String updateUserCard(@RequestBody UsercardEntity usercardEntity){
        JSONObject jsonObject = new JSONObject();
        if(userCardService.update(usercardEntity)){
            jsonObject.put("data",JSONObject.toJSON("更新成功！"));
            jsonObject.put("msg",JSONObject.toJSON("true"));
        }else{
            jsonObject.put("data",JSONObject.toJSON("更新失败！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
        }
        return jsonObject.toJSONString();
    }
}
