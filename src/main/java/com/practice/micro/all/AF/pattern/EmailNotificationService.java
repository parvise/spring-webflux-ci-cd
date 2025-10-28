package com.practice.micro.all.AF.pattern;

public class EmailNotificationService implements  NotificationService{
    @Override
    public void send() {
    System.out.println("Email Notification Service");
    }
}
