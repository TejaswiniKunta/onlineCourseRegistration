package com.api.service.serviceImpl;

import com.api.entity.Course;
import com.api.entity.CourseRegistration;
import com.api.repositories.CourseRegistrationRepository;
import com.api.repositories.CourseRepository;
import com.api.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository elasticsearchRepository;

    @Autowired
    CourseRegistrationRepository registrationRepository;
    @Override
    public Course createCourse(Course course) {
       UUID id = UUID.randomUUID();
            course.setCourseId(id);
        elasticsearchRepository.save(course);
        return course;
    }

    @Override
    public List<Course> getCourses(int studentId) {
        log.info("fetching courses");
       List<Course> courses = new ArrayList<>();
       CourseRegistration courseRegistration= registrationRepository.getCourses(studentId);
       Iterable<Course> iterator= elasticsearchRepository.findAll();
        List<Course> courseList = new ArrayList<>();
        iterator.forEach(course->{
            courses.add(course);
        });
        courseList = courses;
           if(courseRegistration!=null){
              List<UUID> courseIds = new ArrayList<>();
              courseRegistration.getCourseList().forEach(c->{
                  courseIds.add(c.getCourseId());
              });
                courseList=  courses.stream().filter(course -> !courseIds.contains(course.getCourseId())).collect(Collectors.toList());
               }

        return courseList;
    }
}
