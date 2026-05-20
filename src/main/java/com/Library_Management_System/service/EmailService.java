package com.Library_Management_System.service;

public interface EmailService {
    void sendEmails(String to, String subject, String body);
}