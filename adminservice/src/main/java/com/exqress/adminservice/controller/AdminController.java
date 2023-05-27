package com.exqress.adminservice.controller;

import com.exqress.adminservice.entity.Admin;
import com.exqress.adminservice.repository.AdminRepository;
import com.exqress.adminservice.service.LoginService;
import com.exqress.adminservice.service.SessionConstant;
import com.exqress.adminservice.vo.RequestAdmin;
import com.exqress.adminservice.vo.RequestLogin;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private ModelMapper mapper;
    private final LoginService loginService;
    private final AdminRepository adminRepository;

    @PostConstruct
    public void initMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @GetMapping("/signUp")
    public String signIn(@ModelAttribute("requestadmin") RequestAdmin requestAdmin, Model model){
        log.info("Come In Get Method");
        //model.addAttribute("requestadmin", new RequestAdmin());
        return "admin/signUpAdminForm";
    }

    @PostMapping("/signUp")
    public String save(@Valid @ModelAttribute("requestadmin") RequestAdmin requestAdmin, BindingResult bindingResult, Model model){
        log.info("Name : {}", requestAdmin.getName());
        log.info("PW : {}", requestAdmin.getPassword());
        if(bindingResult.hasErrors()) {
            return "admin/signUpAdminForm";
        }
        Admin admin = mapper.map(requestAdmin, Admin.class);
        adminRepository.save(admin);

        return "redirect:/";
    }

    @GetMapping("/signIn")
    public String signInForm(@ModelAttribute("loginForm") RequestLogin requestLogin){
        return "admin/signInAdminForm";
    }

    @PostMapping("/signIn")
    public String signIn(@Valid @ModelAttribute("loginForm") RequestLogin requestLogin, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) return "admin/signInAdminForm";
        Admin admin = loginService.signIn(requestLogin.getLoginId(), requestLogin.getPassword());
        if(admin == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "admin/signInAdminForm";
        }

        // Login Success TODO
        // 세션이 존재하면 세션 반환, 없으면 신규 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 정보 보관
        session.setAttribute(SessionConstant.LOGIN_MEMBER, admin);

        return "redirect:/";
    }

    @PostMapping("/signOut")
    public String signOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
