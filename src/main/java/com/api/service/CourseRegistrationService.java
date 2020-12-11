package com.api.service;

import com.api.entity.CourseRegistration;
import javassist.tools.web.BadHttpRequest;

import java.util.UUID;

public interface CourseRegistrationService {

    public CourseRegistration submitRegistration(CourseRegistration courses) throws BadHttpRequest;
    public CourseRegistration getCourseregistration(int studentId);
    public void deleteCourseregistration(int studentId);
    public CourseRegistration updateRegistration(int studentId, CourseRegistration courseRegistration);
}
