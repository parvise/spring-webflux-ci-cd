package com.practice.micro.all.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Service-B",url="http://localhost:8080/api/v1")
public interface TargetService {

    @GetMapping("/serviceB/{request}")
    public String getServiceB(@PathVariable("request") String request);
}
