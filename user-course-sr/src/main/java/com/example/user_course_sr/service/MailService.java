package com.example.user_course_sr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
        private JavaMailSender mailSender;

        public void sendEnrollmentMail(String to, String courseName) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Course Enrollment Confirmation");
            message.setText("Congrats for enrolling in the course " + courseName);

            mailSender.send(message);
        }
    }


