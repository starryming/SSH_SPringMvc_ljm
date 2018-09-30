package com.learn.filter;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.learn.jwt.Jwt;
import com.learn.jwt.TokenState;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * toekn校验过滤器，所有的API接口请求都要经过该过滤器(除了登陆接口)
 * @author
 *
 */
public class FilterRequest  implements HandlerInterceptor {

        public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
                throws Exception {
            // TODO Auto-generated method stub

        }

        public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
                throws Exception {
            // TODO Auto-generated method stub

        }

        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) {
            // TODO Auto-generated method stub
            //从请求头中获取token
            response.setCharacterEncoding("UTF-8");
            String token = request.getHeader("token");
            JSONObject jsonObject = new JSONObject();
            try {
                if (token == null) {
                    jsonObject.put("data",JSONObject.toJSON("用户未登录，验证失败！"));
                    jsonObject.put("msg",JSONObject.toJSON("401"));
                    System.out.println("用户未登录，验证失败");
                } else {
                    Map<String, Object> resultMap = Jwt.validToken(token);
                    TokenState state = TokenState.getTokenState((String)resultMap.get("state"));

                    switch (state) {
                        case VALID:
                            //取出payload中数据,放入到request作用域中
                            request.setAttribute("data", resultMap.get("data"));
                            HashMap<String,String> dataobj =  (HashMap<String,String>) resultMap.get("data");
                            System.out.println("从token中取出的payload数据是：" + dataobj.toString());
                            //放行
                            return true;
                        case EXPIRED:
                        case INVALID:
                            System.out.println("token解析错误，验证失败");
                            jsonObject.put("data",JSONObject.toJSON("您的token不合法或者过期了，请重新登陆！"));
                            jsonObject.put("msg",JSONObject.toJSON("401"));
                            break;
                    }
                }
                response.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }
}

