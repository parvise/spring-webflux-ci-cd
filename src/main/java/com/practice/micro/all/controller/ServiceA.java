package com.practice.micro.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;
import java.util.*;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;


@RestController
@RequestMapping("/api/v1")
public class ServiceA {

    @Autowired
    public RestTemplate restTemplate;

    private final Random random = new Random();

    private String url="http://localhost:9090/api/v1/serviceB/{request}";

    @GetMapping("/serviceA/callB")
    public String getServiceA() {
        //URI uri= URI.create("http://localhost:8081/api/v1/serviceB");
        URI uri= UriComponentsBuilder.fromUriString(url).
                buildAndExpand("Request from A").toUri();
        String response = restTemplate.getForObject(uri, String.class);


        return "Service A is running - "+response;
    }

    @GetMapping("/dummyList")
    public Flux<String> getDummyList() throws InterruptedException {
        List<String> dummyList = new ArrayList<>(List.of("Dummy1", "Dummy2", "Dummy3"));
        Flux<String> delayedFlux = Flux.range(4, 10)
                .delayElements(Duration.ofSeconds(2))
                .map(i -> "Dummy" + i);
        return Flux.fromIterable(dummyList).concatWith(delayedFlux);

    }

    @GetMapping("/payment")
    public String makePayment() throws InterruptedException {

        int val = random.nextInt(10);
        if (val < 4) {
            throw new RuntimeException("Payment Service Failed");
        } else if (val < 7) {
            Thread.sleep(3000); // simulate slow response
        }
        List<String> listOfStrings = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");

        List<Map.Entry<String, Long>> collect = listOfStrings.stream().collect(Collectors.groupingBy(str1 -> str1, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        return "Payment Success!";
    }

    private static final String SERVICE_NAME = "paymentService";

    @GetMapping("/order")
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackPayment")
    @Retry(name = SERVICE_NAME, fallbackMethod = "fallbackPayment")
    @RateLimiter(name = SERVICE_NAME, fallbackMethod = "fallbackPayment")
    @Bulkhead(name = SERVICE_NAME, fallbackMethod = "fallbackPayment")
    @TimeLimiter(name = SERVICE_NAME, fallbackMethod = "fallbackPaymentAsync")
    public CompletableFuture<String> placeOrder() {
        return CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:9091/api/v1/payment", String.class)
        );
    }

    // Synchronous fallback
    public String fallbackPayment(Exception ex) {
        return "Payment Service is unavailable. Please try again later.";
    }

    // Asynchronous fallback (for TimeLimiter)
    public CompletableFuture<String> fallbackPaymentAsync(Exception ex) {
        return CompletableFuture.completedFuture("Payment Service timeout. Please try later.");
    }
}
