package com.serviceImpl;

import com.dto.LoginResponse;
import com.dto.UserLogin;
import com.repositories.UserInfoRepository;
import com.service.LoginService;

import java.util.UUID;

public class LoginServiceImpl implements LoginService {
    LoginServiceImpl() {

    }

    private UserInfoRepository repository;

    @Override
    public LoginResponse userAuth(UserLogin login) {

        LoginResponse response = new LoginResponse();
        if (!repository.findById(login.getUsername()).equals(login.getPassword())) {

        }
        response.setToken(UUID.randomUUID());
        return response;
    }
}
