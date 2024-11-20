package com.example.user_course_sr.service;

import com.example.user_course_sr.dto.Course;
import com.example.user_course_sr.dto.Response;
import com.example.user_course_sr.dto.UserCourseModule;
import com.example.user_course_sr.figenClient.CourseClient;
import com.example.user_course_sr.figenClient.CourseModuleClient;
import com.example.user_course_sr.figenClient.UserCourseModuleClient;
import com.example.user_course_sr.model.UserCourse;
import com.example.user_course_sr.repo.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserCourseService {
    @Autowired
    private  UserCourseRepository userCourseRepository;

    @Autowired
    private CourseClient courseClient;


    @Autowired

    private CourseModuleClient courseModuleClient;

    @Autowired
    private UserCourseModuleClient userCourseModuleClient;

    public UserCourse enrollUserInCourse( String userId, String courseId) {
        UserCourse userCourse = new UserCourse();

        userCourse.setUserId(userId);
        userCourse.setCourseId(courseId);
        userCourse.setEnrollmentDate(LocalDate.now());
        userCourse.setProgress(0.0);
        userCourse.setIsCompleted(false);
        UserCourse savedUserCourse =userCourseRepository.save(userCourse);
        List<String> courseModules = courseModuleClient.getCourseModulesByCourseId(courseId);
        List<UserCourseModule> userCourseModules = new ArrayList<>();
        for (String courseModule : courseModules) {
            UserCourseModule userCourseModule = new UserCourseModule();
           userCourseModule.setCourseModuleId(courseModule);
           userCourseModule.setUserCourseId(savedUserCourse.getUserCourseId());
            userCourseModules.add(userCourseModule);
        }
        ResponseEntity<List<UserCourseModule>> res =userCourseModuleClient.createAssociations(userCourseModules);

        if(res.getStatusCode().is2xxSuccessful()){
            return savedUserCourse;
        }
        else{
            throw new RuntimeException("Failed to create module with content");
        }
    }

    public List<UserCourse> getUserCourses(String userId) {
        return userCourseRepository.findByUserId(userId);
    }

//    public void updateProgress(String userCourseId, Double progress) {
//        UserCourse userCourse = userCourseRepository.findById(userCourseId)
//                .orElseThrow(() -> new RuntimeException("UserCourse not found"));
//        userCourse.setProgress(progress);
//
//        if (progress >= 100.0) {
//            userCourse.setIsCompleted(true);
//            userCourse.setCompletionDate(LocalDate.now());
//        }
//
//        userCourseRepository.save(userCourse);
//    }

    public List<UserCourse> getCompletedCoursesByUser(String userId) {
        return userCourseRepository.findByUserIdAndIsCompleted(userId, true);
    }

    public List<Response> getCoursesForUser(String userId) {
        // Retrieve the list of UserCourse objects for the user
        List<UserCourse> userCourses = userCourseRepository.findByUserId(userId);

        // Extract courseIds from the userCourses
        List<String> courseIds = userCourses.stream()
                .map(UserCourse::getCourseId)
                .toList();

        // Fetch Course details for each courseId
        List<Course> courses = courseClient.getCoursesByIds(courseIds);

        // Map Course and UserCourse objects to create the Response list
        return courses.stream().map(course -> {
            // Find the matching UserCourse object for the current course
            UserCourse userCourse = userCourses.stream()
                    .filter(uc -> uc.getCourseId().equals(course.getCourseId()))
                    .findFirst()
                    .orElse(null);

            // Build and return a Response object with combined information
            return new Response(
                    course.getCourseId(),
                    course.getCourseTitle(),
                    course.getCourseDescription(),
                    course.getCourseDuration(),
                    course.getCourseLevel(),
                    course.getCourseCategory(),
                    course.getImgUrl(),
                    userCourse != null ? userCourse.getUserCourseId() : null,
                    userCourse != null ? userCourse.getEnrollmentDate() : null,
                    userCourse != null ? userCourse.getProgress() : null,
                    userCourse != null ? userCourse.getCompletionDate() : null,
                    userCourse != null ? userCourse.getIsCompleted() : null
            );
        }).toList();
    }

    public boolean updateCourseProgress(UserCourse courseUpdated) {
        Optional<UserCourse> courseOptional = userCourseRepository.findById(courseUpdated.getUserCourseId());

        if (courseOptional.isPresent()) {
            UserCourse course = courseOptional.get();
            course.setProgress(courseUpdated.getProgress());
            course.setIsCompleted(courseUpdated.getIsCompleted());

            // Save updated course
            userCourseRepository.save(course);

            return true;
        }
        return false;
    }
    }


