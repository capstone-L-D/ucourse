package com.example.user_course_sr.repo;

import com.example.user_course_sr.model.UserCourse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserCourseRepository extends MongoRepository<UserCourse, String> {
    List<UserCourse> findByUserId(String userId);

    List<UserCourse> findByUserIdAndIsCompleted(String userId, boolean b);
}
