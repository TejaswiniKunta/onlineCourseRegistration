package com.api.repositories;

import com.api.entity.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends ElasticsearchRepository<Course,String> {

    public Optional<Course> findById(String id);



}
