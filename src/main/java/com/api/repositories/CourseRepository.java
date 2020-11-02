package com.api.repositories;

import com.api.entity.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends ElasticsearchRepository<Course,String> {


}
