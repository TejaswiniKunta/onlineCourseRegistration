package com.api.service.serviceImpl;

import com.api.Exceptions.CourseExistsExceptions;
import com.api.entity.Course;
import com.api.entity.CourseRegistration;
import com.api.repositories.CourseRegistrationRepository;
import com.api.repositories.CourseRepository;
import com.api.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository elasticsearchRepository;

    @Autowired
    CourseRegistrationRepository registrationRepository;
    @Override
    public Course createCourse(Course course) throws CourseExistsExceptions {

       UUID id = UUID.randomUUID();
            course.setCourseId(id);
          Iterable<Course> courseIterator=  elasticsearchRepository.findAll();
        for (Course c : courseIterator) {
            if (c.getCourseName().equals(course.getCourseName())) {
                throw new CourseExistsExceptions("Course already exists");
            }
        }
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
           if(courseRegistration!=null && courseRegistration.getCourseList()!=null){
              List<UUID> courseIds = new ArrayList<>();
              courseRegistration.getCourseList().forEach(c->{
                  courseIds.add(c.getCourseId());
              });
                courseList=  courses.stream().filter(course -> !courseIds.contains(course.getCourseId())).collect(Collectors.toList());
               }

        return courseList;
    }

    @Override
    public void deleteCourse(UUID courseId) {
           elasticsearchRepository.deleteById(courseId.toString());
        log.info("successfully deleted");
    }


    @Override
    public Course updateCourse(Course course){
        Optional<Course> cc= elasticsearchRepository.findById(course.getCourseId().toString());
        if(cc!=null && cc.get()!=null){
            elasticsearchRepository.save(course);
            log.info("successfully updates");
        }


        return course;
    }

    @Override
    public List<Course> filterByWord(String word){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(word).field("courseName").field("description").field("instructor"))
                .build();
        Page<Course> courses = elasticsearchRepository.search(searchQuery);
        return courses.getContent();
    }

}
