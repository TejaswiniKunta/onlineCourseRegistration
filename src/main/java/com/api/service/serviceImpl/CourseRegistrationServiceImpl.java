package com.api.service.serviceImpl;

import com.api.entity.CourseRegistration;
import com.api.repositories.CourseRegistrationRepository;
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

    @Override
    public CourseRegistration submitRegistration(CourseRegistration courseList) throws BadHttpRequest {

        if(courseList.getCourseList().size()>3){
            throw new BadHttpRequest();
         } else {
           registrationRepository.save(courseList);

        }
        return courseList;
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
        return courseRegistration;
    }
}
