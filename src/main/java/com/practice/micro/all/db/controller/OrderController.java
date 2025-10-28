package com.practice.micro.all.db.controller;

import com.practice.micro.all.db.dto.OrderDTO;
import com.practice.micro.all.db.service.OrderSevice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderSevice service;

    public OrderController(OrderSevice service) {
        this.service = service;
    }

    @PostMapping(value = "/orderPlaced")
    public OrderDTO bookOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        return service.bookOrder(orderDTO);
    }
}
