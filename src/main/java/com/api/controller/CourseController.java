package com.api.controller;

import com.api.Exceptions.CourseExistsExceptions;
import com.api.entity.Course;
import com.api.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
                                   @RequestBody Course course) throws CourseExistsExceptions {
        return courseService.createCourse(course);
    }

    @RequestMapping(value = "/courses",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "courses", notes = "list of courses")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")
    })
    public List<Course> getCourses(@ApiParam(value = "provide student id",required = true) @RequestParam int studentId) throws IOException{
     return courseService.getCourses(studentId);
    }

    @RequestMapping(value = "/course",method = RequestMethod.DELETE)
    @ApiOperation(value = "course deletion")
    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "DELETED"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")

    })
    public void deleteCourses(@ApiParam(value = "provide course id",required = true)
        @RequestParam UUID courseId){
     courseService.deleteCourse(courseId);
    }


    @RequestMapping(value = "/course",method = RequestMethod.PUT)
    @ApiOperation(value = "course update")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "UPDATED"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")

    })
    public Course updateCourses(@ApiParam(value = "provide modified course",required = true)
                                @RequestBody Course course){
        return courseService.updateCourse(course);
    }


    @RequestMapping(value = "/courseByWord",method = RequestMethod.GET)
    @ApiOperation(value = "course list")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "FETCHED"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "bad request")

    })
    public List<Course> getCoursesBasedOnWord(@ApiParam(value = "provide a word",required = true)
                                                      @RequestParam String word){
        return courseService.filterByWord(word);
    }


}
