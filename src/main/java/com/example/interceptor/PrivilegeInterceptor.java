package com.example.interceptor;

import com.example.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivilegeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("PrivilegeInterceptor");
        System.out.println(user);
        if(user==null){
            //没有登录

            return false;
        }
        return true;
    }
}
