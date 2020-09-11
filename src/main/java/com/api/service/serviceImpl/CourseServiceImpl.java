package com.api.service.serviceImpl;

import com.api.entity.Course;
import com.api.repositories.CourseRepository;
import com.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository elasticsearchRepository;

    @Override
    public Course createCourse(Course course) {
       UUID id = UUID.randomUUID();
//        while(elasticsearchRepository.getCourseById(id)!=null) {
//            id = UUID.randomUUID();
            course.setCourseId(id);
//        }
        elasticsearchRepository.save(course);
        return course;
    }
}
