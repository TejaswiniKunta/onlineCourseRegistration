package com.api.dto;


import org.springframework.data.cassandra.core.mapping.Table;

@Table("userlogin")
public class LoginResponse extends UserLogin{

    private String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
