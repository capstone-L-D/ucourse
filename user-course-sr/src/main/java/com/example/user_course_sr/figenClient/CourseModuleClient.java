package com.example.user_course_sr.figenClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "course-module-sr", url = "http://localhost:7073/api/course-modules")
public interface CourseModuleClient {

    @GetMapping("/courseModules/{courseId}")
    public List<String> getCourseModulesByCourseId(@PathVariable String courseId);
}
