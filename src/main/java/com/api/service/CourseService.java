package com.api.service;


import com.api.Exceptions.CourseExistsExceptions;
import com.api.entity.Course;

import java.util.List;

public interface CourseService {
    public Course createCourse(Course course) throws CourseExistsExceptions;
    public List<Course> getCourses(int studentid);
}
