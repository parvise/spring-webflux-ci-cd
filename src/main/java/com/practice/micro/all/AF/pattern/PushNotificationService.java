package com.practice.micro.all.AF.pattern;

public class PushNotificationService implements  NotificationService{
    @Override
    public void send() {
        System.out.println("Push Notification Service");
    }
}
