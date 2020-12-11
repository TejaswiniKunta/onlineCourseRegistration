package com.api.controller;

import com.api.entity.CourseRegistration;
import com.api.service.CourseRegistrationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private CourseRegistrationService service;

    @RequestMapping(value = "/registration/submit",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "course registration")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")
    })
    public CourseRegistration courseRegistration(@ApiParam(value = "please fill in the details to add courses", required = true, name = "course creation")
                                               @RequestBody CourseRegistration courses) throws BadHttpRequest {
        return service.submitRegistration(courses);
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "get list of courses")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")
    })
    public CourseRegistration getRegistration(@ApiParam(value = "provide student id",required = true) @RequestParam int studentId){

        return service.getCourseregistration(studentId);
    }

    @RequestMapping(value = "/registration",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "get list of courses")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "DELETED"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")
    })
    public void deleteRegistration(@ApiParam(value = "provide student id",required = true) @RequestParam int studentId){

         service.deleteCourseregistration(studentId);
    }

    @RequestMapping(value = "/registration",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "to update course registration")
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "UPDATED"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")
    })
    public CourseRegistration updateRegistration(@ApiParam(value = "provide studentId and new course info")@RequestParam int studentId,
                                                 @RequestBody CourseRegistration courseRegistration){
        return service.updateRegistration(studentId,courseRegistration);
    }

}
