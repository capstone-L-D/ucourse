package com.example.user_course_sr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourse {
    @Id
    private String userCourseId;
    private String userId;
    private String courseId;
    private LocalDate enrollmentDate;
    private Double progress;
    private LocalDate completionDate;
    private Boolean isCompleted;

}
