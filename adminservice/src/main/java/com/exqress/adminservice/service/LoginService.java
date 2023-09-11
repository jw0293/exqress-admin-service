package com.exqress.adminservice.service;

import com.exqress.adminservice.entity.Admin;
import com.exqress.adminservice.entity.QRinfo;
import com.exqress.adminservice.entity.UserEntity;
import com.exqress.adminservice.repository.AdminRepository;
import com.exqress.adminservice.repository.QRinfoRepository;
import com.exqress.adminservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

    private final AdminRepository adminRepository;

    public Admin signIn(String loginId, String password){
        Admin admin = adminRepository.findByLoginId(loginId);
        if(admin == null || !admin.getPassword().equals(password)) return null;
        return admin;
    }
}
