package com.practice.micro.all.controller;

import com.practice.micro.all.AF.pattern.NotificationFactory;
import com.practice.micro.all.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    public NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/send")
    public String sendNotification() {
        service.sendNotification();
        return "Notification sent successfully!";
    }
}
