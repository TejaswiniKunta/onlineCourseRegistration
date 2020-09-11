package com.api.controller;

import com.api.entity.Course;
import com.api.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @RequestMapping(value = "/course", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "course", notes = "course creation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")
    })
    public Course createCourse(@ApiParam(value = "please fill in the details to create course", required = true, name = "course creation")
                                   @RequestBody Course course) throws IOException {
        return courseService.createCourse(course);

    }

}
