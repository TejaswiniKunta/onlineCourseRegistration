package com.service;


import com.dto.LoginResponse;
import com.dto.UserLogin;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface LoginService {

    public LoginResponse userAuth(UserLogin login);
}
