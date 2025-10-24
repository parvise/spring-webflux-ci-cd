package com.practice.micro.all.AF.pattern;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevNotificationFactory implements NotificationFactory{
    @Override
    public NotificationService createPrimary() {
        return new PushNotificationService();
    }

    @Override
    public NotificationService createSecondary() {
        return new InAppNotificationService();
    }
}
