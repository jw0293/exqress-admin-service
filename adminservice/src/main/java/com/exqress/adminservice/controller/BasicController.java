package com.exqress.adminservice.controller;

import com.exqress.adminservice.entity.Admin;
import com.exqress.adminservice.service.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class BasicController {
    @GetMapping("/")
    public String basicScreen(
            @SessionAttribute(name = SessionConstant.LOGIN_MEMBER, required = false) Admin loginAdmin,
            Model model
    ){
        if(loginAdmin == null) return "basic";
        model.addAttribute("admin", loginAdmin);

        return "loginHome";
    }
}
