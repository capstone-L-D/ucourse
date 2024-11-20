package com.example.user_course_sr.figenClient;

import com.example.user_course_sr.dto.UserCourseModule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-course-module", url = "http://localhost:7076/api/user-course-modules")

public interface UserCourseModuleClient {

    @PostMapping("/bulk")
    public ResponseEntity<List<UserCourseModule>> createAssociations(@RequestBody List<UserCourseModule> userCourseModules);
}
