package com.practice.micro.all.AF.pattern;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("pro")
public class ProdNotificationFactory implements NotificationFactory{
    private NotificationService notificationService;
    @Override
    public NotificationService createPrimary() {
        return new EmailNotificationService();
    }

    @Override
    public NotificationService createSecondary() {
        return new SMSNotificationService();
    }
}
