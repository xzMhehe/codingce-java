package cn.com.codingce.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginIntercepetor implements HandlerInterceptor {
    //判断什么情况下没有登录
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行前");
        HttpSession session = request.getSession();
        //登录页面放行
        if (request.getRequestURI().contains("goLogin")) {
            System.out.println("===========1===========");
            return true;
        }

        if (request.getRequestURI().contains("login")) {
            System.out.println("===========1===========");
            return true;
        }
        //放行: 判断什么情况下登录
        if(session.getAttribute("userLoginInfo") != null){
            System.out.println("===========2===========");
            return true;
        }
        System.out.println("===========3===========");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行后");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("清理");
    }
}
