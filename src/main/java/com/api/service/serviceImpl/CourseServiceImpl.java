package com.api.service.serviceImpl;

import com.api.entity.Course;
import com.api.repositories.CourseRepository;
import com.api.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository elasticsearchRepository;

    @Override
    public Course createCourse(Course course) {
       UUID id = UUID.randomUUID();
            course.setCourseId(id);
        elasticsearchRepository.save(course);
        return course;
    }

    @Override
    public List<Course> getCourses() {
        log.info("fetching courses");
        List<Course> courses = new ArrayList<>();
       Iterable<Course> iterator= elasticsearchRepository.findAll();
       iterator.forEach(c->{
           courses.add(c);
       });
        return courses;
    }
}
