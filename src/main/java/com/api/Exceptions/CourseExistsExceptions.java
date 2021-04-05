package com.api.Exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourseExistsExceptions extends RuntimeException{


    public CourseExistsExceptions(String message) {
        super(message);

    }



}
