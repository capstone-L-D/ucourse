package com.example.user_course_sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class UserCourseSrApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCourseSrApplication.class, args);
	}

}
