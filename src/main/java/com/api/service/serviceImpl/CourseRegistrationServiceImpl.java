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

import java.util.ArrayList;
import java.util.List;
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
        List<Course> newList = new ArrayList<>();
        Optional<CourseRegistration> old= registrationRepository.findById(courseList.getStudentId());
        if(old!=null && old.isPresent() &&  old.get().getCourseList()!=null) {
            newList.addAll(old.get().getCourseList());
        }
        newList.addAll(courseList.getCourseList());
        courseList.setCourseList(newList);
           registrationRepository.save(courseList);
           log.info("submission successful");
          courseList.getCourseList().forEach(course -> {
              updateEnrolledStudents(course,courseList.getStudentId(),1);
          });

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
        Optional<CourseRegistration> old = registrationRepository.findById(studentId);

        old.get().getCourseList().forEach(c->{
            updateEnrolledStudents(c,studentId,-1);

        });
        registrationRepository.deleteById(studentId);
        log.info("deleted registration");
    }

    @Override
    public CourseRegistration updateRegistration(int studentId, CourseRegistration courseRegistration) {
      Optional<CourseRegistration> old= registrationRepository.findById(studentId);
      if(!old.isPresent()){
         throw new IndexOutOfBoundsException();
      }
        old.get().getCourseList().forEach(course -> {
            updateEnrolledStudents(course,studentId,-1);
        });
        registrationRepository.save(courseRegistration);
        courseRegistration.getCourseList().forEach(c->{
            updateEnrolledStudents(c,studentId,1);
        });
        return courseRegistration;
    }
}
