package com.tekup.staffmanagementapi.user;

public interface EmailService {

    void sendEmail(String to, String subject, String body);
}
