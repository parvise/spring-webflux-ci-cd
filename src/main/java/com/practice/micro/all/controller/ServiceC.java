package com.practice.micro.all.controller;

import com.practice.micro.all.service.TargetService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ServiceC {


    private TargetService targetService;

    public ServiceC(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping("/serviceC/{request}")
    public String getServiceC(@PathVariable("request") String request) {
        String response=targetService.getServiceB(request);
        return "Service C is running / "+response;
    }

    @GetMapping("/users")
    public Mono<List<Object>> getUsers(){
        // https://jsonplaceholder.typicode.com/users
        WebClient webClient = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList();
    }

    @GetMapping(value="/dummyData",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getDummyData(){
        // https://jsonplaceholder.typicode.com/users
        String url="http://localhost:9090/api/v1";
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .build();

        return  webClient.get()
                .uri("/dummyList")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class);


    }
}
