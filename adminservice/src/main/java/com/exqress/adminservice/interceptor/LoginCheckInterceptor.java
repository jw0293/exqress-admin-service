package com.exqress.adminservice.interceptor;

import com.exqress.adminservice.service.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Check Authorizaion Excution : {}", request.getRequestURI());
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute(SessionConstant.LOGIN_MEMBER) == null){
            log.info("Not Check Auth");
            response.sendRedirect("/admin/signIn?redirectURL=" + request.getRequestURI());
            return false;
        }
        return true;
    }
}
