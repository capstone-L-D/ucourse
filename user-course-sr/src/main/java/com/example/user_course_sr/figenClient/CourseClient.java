package com.example.user_course_sr.figenClient;

import com.example.user_course_sr.dto.Course;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "course-service", url = "http://localhost:7071/api/courses")
public interface CourseClient {
    @PostMapping("/batch")
    List<Course> getCoursesByIds(List<String> courseIds);
}
