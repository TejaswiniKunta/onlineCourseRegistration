package com.api.service.serviceImpl;

import com.api.Exceptions.UserNotFoundException;
import com.api.dto.LoginResponse;
import com.api.dto.UserLogin;
import com.api.repositories.UserInfoRepository;
import com.api.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Component
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public LoginResponse userAuth(UserLogin login)   {
        LoginResponse response = new LoginResponse();
        if(repository.findOneById(login.getUsername())==null) {
            throw new UserNotFoundException("incorrect login details");

        } else {
             response = repository.findOneById(login.getUsername());
            if (!response.getPassword().equals(login.getPassword())) {
                throw new UserNotFoundException("incorrect login details");
            }
        }
        
            log.info("accountType= "+ response.getAccountType() + " id= "+response.getId());



        return response;
    }
}
