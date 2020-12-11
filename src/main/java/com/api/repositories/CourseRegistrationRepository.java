package com.api.repositories;

import com.api.entity.Course;
import com.api.entity.CourseRegistration;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableCassandraRepositories
public interface CourseRegistrationRepository extends CassandraRepository<CourseRegistration, Integer> {

 @Query("SELECT * FROM courseregistration WHERE studentid = ?0 ALLOW FILTERING")
public CourseRegistration getCourses(int studentId);

 @Query("DELETE FROM courseregistration WHERE studentid=?0")
  public void delete(int studentId);
//
// @Query("UPDATE courseregistration SET courses=?1 WHERE studentid=?0 ALLOW FILTERING")
// public CourseRegistration updateCourse(int studentId, List<Course> courses);
}
