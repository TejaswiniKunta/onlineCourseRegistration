package com.api.service;


import com.api.Exceptions.CourseExistsExceptions;
import com.api.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    public Course createCourse(Course course) throws CourseExistsExceptions;
    public List<Course> getCourses(int studentid);
    public void deleteCourse(UUID courseId);
    public List<Course> filterByWord(String word);
    public Course updateCourse(Course course);
}
