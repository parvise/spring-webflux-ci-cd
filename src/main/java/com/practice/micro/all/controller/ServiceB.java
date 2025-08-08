package com.practice.micro.all.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ServiceB {

    @GetMapping("/serviceB/{request}")
    public String getServiceB(@PathVariable("request") String request) {
        return "Service B is running-" + request;
    }
}
