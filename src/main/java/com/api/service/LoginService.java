package com.api.service;


import com.api.dto.LoginResponse;
import com.api.dto.UserLogin;

public interface LoginService {

    public LoginResponse userAuth(UserLogin login);
}
