package com.example.user_course_sr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String courseId;
    private String courseTitle;
    private String  courseDescription;
    private String courseDuration;
    private String courseLevel;
    private String courseCategory;
    private String imgUrl;
    private String userCourseId;
    private LocalDate enrollmentDate;
    private Double progress;
    private LocalDate completionDate;
    private Boolean isCompleted;
}
