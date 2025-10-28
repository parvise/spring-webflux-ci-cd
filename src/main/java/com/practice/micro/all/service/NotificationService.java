package com.practice.micro.all.service;

import com.practice.micro.all.AF.pattern.NotificationFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public NotificationFactory factory;

    public NotificationService(NotificationFactory factory) {
        this.factory = factory;
    }

    public void sendNotification() {
        if (factory != null) {
            factory.createPrimary().send();
            factory.createSecondary().send();
        } else {
            System.out.println("No notification factory configured.");
        }
    }
}
