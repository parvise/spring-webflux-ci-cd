package com.practice.micro.all.controller;

import com.practice.micro.all.service.TargetService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/api/v1")
public class ServiceC {


    private TargetService targetService;

    private static final String SERVICE_NAME = "paymentService";

    public ServiceC(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping("/serviceC/{request}")
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackPayment1")
    @Retry(name = SERVICE_NAME, fallbackMethod = "fallbackPayment1")
    @RateLimiter(name = SERVICE_NAME, fallbackMethod = "fallbackPayment1")
    @Bulkhead(name = SERVICE_NAME, fallbackMethod = "fallbackPayment1")
    public String getServiceC(@PathVariable("request") String request) {
        String response = targetService.getServiceB(request);
        return "Service C is running / " + response;
    }


    //    Circuit Breaker → trips open after multiple failures.
//            Retry → retries failed request 3 times before fallback.
//    Rate Limiter → allows only 2 requests per 10 seconds.
//            Timeout → if payment takes >2s → fallback.
//            Bulkhead → only 2 concurrent calls allowed.
//            Fallback → graceful response when failures happen.
    @GetMapping("/users")
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackPayment")
    @Retry(name = SERVICE_NAME, fallbackMethod = "fallbackPayment")
    @RateLimiter(name = SERVICE_NAME, fallbackMethod = "fallbackPayment")
    @Bulkhead(name = SERVICE_NAME, fallbackMethod = "fallbackPayment")
   // @TimeLimiter(name = SERVICE_NAME, fallbackMethod = "fallbackPaymentAsync")
    public Mono<List<Object>> getUsers() {
        // https://jsonplaceholder.typicode.com/users
        WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();

        return webClient.get().uri("/users").retrieve().bodyToFlux(Object.class).collectList();
    }

    @GetMapping(value = "/dummyData", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getDummyData() {
        // https://jsonplaceholder.typicode.com/users
        String url = "http://localhost:9090/api/v1";
        WebClient webClient = WebClient.builder().baseUrl(url).build();

        return webClient.get().uri("/dummyList").accept(MediaType.TEXT_EVENT_STREAM).retrieve().bodyToFlux(String.class);


    }


    public Mono<List<Object>> fallbackPayment(Throwable ex) {
        System.out.println("Fallback executed due to: " + ex.getMessage());

        List<Object> defaultUsers = new ArrayList<>();
        Map<String, String> user = new HashMap<>();
        user.put("name", "Fallback User");
        user.put("email", "fallback@example.com");
        defaultUsers.add(user);

        return Mono.just(defaultUsers);
    }

    // Asynchronous fallback (for TimeLimiter)
    public CompletableFuture<Mono<List<Object>>> fallbackPaymentAsync(Exception ex) {
        return CompletableFuture.completedFuture(
                Mono.just(List.of("Payment Service is currently unavailable. Please try again later."))
        );
    }
}
