package com.exqress.adminservice.controller;

import com.exqress.adminservice.entity.Admin;
import com.exqress.adminservice.service.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class BasicController {
    @GetMapping("/")
    public String basicScreen(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session == null) return "basic";

        Admin loginAdmin = (Admin) session.getAttribute(SessionConstant.LOGIN_MEMBER);
        if(loginAdmin == null) return "basic";

        model.addAttribute("admin", loginAdmin);
        return "loginHome";
    }
}
