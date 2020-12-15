package com.api.service;


import com.api.entity.Course;

import java.util.List;

public interface CourseService {
    public Course createCourse(Course course);
    public List<Course> getCourses(int studentId);
}
