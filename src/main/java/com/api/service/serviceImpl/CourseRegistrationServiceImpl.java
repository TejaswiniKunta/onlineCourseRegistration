package com.api.service.serviceImpl;

import com.api.entity.Course;
import com.api.entity.CourseRegistration;
import com.api.repositories.CourseRegistrationRepository;
import com.api.repositories.CourseRepository;
import com.api.service.CourseRegistrationService;
import javassist.tools.web.BadHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

@Autowired
private CourseRegistrationRepository registrationRepository;

@Autowired
private CourseRepository courseRepository;

    @Override
    public CourseRegistration submitRegistration(CourseRegistration courseList) throws BadHttpRequest {

        if(courseList.getCourseList().size()>3){
            throw new BadHttpRequest();
         } else {
           registrationRepository.save(courseList);
           log.info("submission successful");
          courseList.getCourseList().forEach(course -> {
              updateEnrolledStudents(course,courseList.getStudentId(),1);
          });
        }
        return courseList;
    }

    private void updateEnrolledStudents(Course c,int s,int number){
      Optional<Course> courseInfo =  courseRepository.findById(c.getCourseId().toString());
     int students= courseInfo.get().getStudentsEnrolled();
     if(students!=0) {
         courseInfo.get().setStudentsEnrolled(students + number);
     }
     courseRepository.save(courseInfo.get());

    }
    @Override
    public CourseRegistration getCourseregistration(int studentId) {

      return  registrationRepository.getCourses(studentId);
    }

    @Override
    public void deleteCourseregistration(int studentId) {
        registrationRepository.delete(studentId);
    }

    @Override
    public CourseRegistration updateRegistration(int studentId, CourseRegistration courseRegistration) {
      Optional<CourseRegistration> old= registrationRepository.findById(studentId);
      if(!old.isPresent()){
         throw new IndexOutOfBoundsException();
      }
        registrationRepository.save(courseRegistration);
        courseRegistration.getCourseList().forEach(c->{
            updateEnrolledStudents(c,studentId,-1);
        });
        return courseRegistration;
    }
}
