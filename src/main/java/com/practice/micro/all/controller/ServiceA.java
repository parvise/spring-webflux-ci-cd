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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ServiceA {

    @Autowired
    public RestTemplate restTemplate;

    private String url="http://localhost:8080/api/v1/serviceB/{request}";

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
}
