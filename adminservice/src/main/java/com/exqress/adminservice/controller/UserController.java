package com.exqress.adminservice.controller;

import com.exqress.adminservice.entity.UserEntity;
import com.exqress.adminservice.repository.UserRepository;
import com.exqress.adminservice.vo.RequestUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public String getUsers(Model model){
        Iterable<UserEntity> allUsers = userRepository.findAll();
        List<UserEntity> users = new ArrayList<>();
        allUsers.forEach(users::add);
        model.addAttribute("users", users);
        return "form/users";
    }

    @PostMapping("/assign/user")
    public void assignUser(@RequestBody RequestUser user){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setUserId(user.getUserId());
        userRepository.save(userEntity);
    }


}
