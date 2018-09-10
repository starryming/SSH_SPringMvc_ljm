package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.learn.entity.EmailEntity;
import com.learn.utils.SendMailUtils;
import com.learn.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/tool")//Contoller下所有接口统一入口
public class ToolController {

    /** 随机获取验证码图片 并存入session
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转
    public void getVerifyPicture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");// 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        System.out.println("verifyCode:"+verifyCode);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verifyCode");
        session.setAttribute("verifyCode", verifyCode.toLowerCase());
        // 生成图片
        int w = 100, h = 30;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /** 用户验证码匹配 从session取出
     * @param verify
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/verify/{verify}",method = RequestMethod.GET)
    @ResponseBody
    public String checkVerify(@PathVariable("verify") String verify,HttpSession httpSession){
        JSONObject jsonObject = new JSONObject();
        System.out.println(httpSession.getAttribute("verifyCode"));
        String realVerify = (String) httpSession.getAttribute("verifyCode");
        if(verify.equals(realVerify)){
            jsonObject.put("data",JSONObject.toJSON("验证成功！"));
            jsonObject.put("msg",JSONObject.toJSON("true"));
        }else{
            jsonObject.put("data",JSONObject.toJSON("验证失败！"));
            jsonObject.put("msg",JSONObject.toJSON("false"));
        }
        return jsonObject.toJSONString();
    }

    /**
     * 发送系统验证
     *
     * @param emailEntity 收件人邮箱
     * @return
     */
    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    @ResponseBody
    public String sendEmailSystem(@RequestBody EmailEntity emailEntity){
        System.out.println("给该邮箱发邮件："+emailEntity.getToEmailAddress());
        //toEmailAddress = "1462764424@qq.com";
        JSONObject jsonObject = new JSONObject();
        try{
            //生成验证码
            String verifyCode = "15q8e7d";

            //邮件主题
            String emailTitle = "【LJM_TEST】邮箱验证";

            //邮件内容
            String emailContent = "您正在【LJM_TEST】进行邮箱验证，您的验证码为：" + verifyCode + "，请于10分钟内完成验证！";

            //发送邮件
            SendMailUtils.sendEmail(emailEntity.getToEmailAddress(), emailTitle, emailContent);
            jsonObject.put("data", JSONObject.toJSON(verifyCode));
            jsonObject.put("msg", "true");
            return jsonObject.toJSONString();
        }catch (Exception e) {
            jsonObject.put("data", JSONObject.toJSON("邮件发送失败！"));
            jsonObject.put("msg","false" );
            return jsonObject.toJSONString();
        }
    }
}
