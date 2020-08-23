package com.api.Exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends RuntimeException {

    private final String message;


    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}

