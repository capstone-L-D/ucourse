package com.example.user_course_sr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String courseId;
    private String courseTitle;
    private String  courseDescription;
    private String courseDuration;
    private String courseLevel;
    private String courseCategory;
    private String imgUrl;
}
