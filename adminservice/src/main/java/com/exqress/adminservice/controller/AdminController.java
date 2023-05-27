package com.exqress.adminservice.controller;

import com.exqress.adminservice.entity.Admin;
import com.exqress.adminservice.repository.AdminRepository;
import com.exqress.adminservice.vo.RequestAdmin;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
}
