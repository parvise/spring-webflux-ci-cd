package com.practice.micro.all.AF.pattern;

public class InAppNotificationService implements  NotificationService{
    @Override
    public void send() {
        System.out.println("InApp Notification Service");
    }
}
