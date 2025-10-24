package com.practice.micro.all.AF.pattern;

public class SMSNotificationService implements  NotificationService{
    @Override
    public void send() {
        System.out.println("SMS Notification Service");
    }
}
