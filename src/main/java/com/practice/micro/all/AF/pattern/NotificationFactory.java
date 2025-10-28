package com.practice.micro.all.AF.pattern;

public interface NotificationFactory {
    NotificationService createPrimary();
    NotificationService createSecondary();
}
