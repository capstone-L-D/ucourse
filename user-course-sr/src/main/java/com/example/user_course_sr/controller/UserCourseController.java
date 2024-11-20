package com.example.user_course_sr.controller;
import com.example.user_course_sr.dto.Course;
import com.example.user_course_sr.dto.Response;
import com.example.user_course_sr.model.UserCourse;
import com.example.user_course_sr.service.MailService;
import com.example.user_course_sr.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/user-courses")
@CrossOrigin
public class UserCourseController {



        @Autowired
        private UserCourseService userCourseService;


        @Autowired
        private MailService emailService;

        @PostMapping("/enroll")
        public UserCourse enrollUser(@RequestBody  UserCourse userCourse) {
            return userCourseService.enrollUserInCourse(userCourse.getUserId(), userCourse.getCourseId());

        }
        ////////////////////////////////

        @PostMapping("/notify")
//        public ResponseEntity<String> notifyUser(@RequestBody Map<String, String> enrollmentData) {
        public ResponseEntity<String> notifyUser() {
//            String userId = enrollmentData.get("userId");
//            String courseId = enrollmentData.get("courseId");

            // Retrieve user email and course name using Feign Client
//        String userEmail = userCourseClient.getUserEmail(userId);
//        String courseName = userCourseClient.getCourseName(courseId);

            String userEmail = "abhimanyujoy1605@gmail.com";
            String courseName = "Robotic Process Automation";

            // Send email
            emailService.sendEnrollmentMail(userEmail,courseName );

            return ResponseEntity.ok("Email sent successfully to " + userEmail);
        }







    /////////////////////////////////////////
    @GetMapping("/{userId}")
    public List<UserCourse> getUserCourses(@PathVariable String userId) {
        return userCourseService.getUserCourses(userId);
    }
    @GetMapping("/courses/{userId}")
    public List<Response> getCoursesForUser(@PathVariable String userId) {
        return userCourseService.getCoursesForUser(userId);
    }
//    @PatchMapping("/{userCourseId}/progress")
//    public void updateProgress(@PathVariable String userCourseId, @RequestParam Double progress) {
//        userCourseService.updateProgress(userCourseId, progress);
//    }

    @GetMapping("/{userId}/completed")
    public List<UserCourse> getCompletedCourses(@PathVariable String userId) {
        return userCourseService.getCompletedCoursesByUser(userId);
    }

    @PostMapping("/update-course-progress")
    public ResponseEntity<Map<String, String>> updateCourseProgress(@RequestBody UserCourse courseUpdated) {
        boolean success = userCourseService.updateCourseProgress(courseUpdated);
        Map<String, String> response = new HashMap<>();

        if (success) {
            response.put("message", "Course progress updated successfully");
            return ResponseEntity.ok(response); // Return JSON response
        } else {
            response.put("message", "Failed to update course progress");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }}



