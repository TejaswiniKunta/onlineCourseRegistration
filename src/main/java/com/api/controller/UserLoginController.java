package com.api.controller;

import com.api.dto.UserLogin;
import com.api.service.LoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(description = "user login", tags = "user login")

public class UserLoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "authentication", notes = "users' login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "authenticated"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 401, message = "unauthorized")
    })
    public String loginRequest(@ApiParam(value = "login details", required = true, name = "userLogin")
                                   @RequestBody UserLogin login){

        return loginService.userAuth(login);
    }

}
