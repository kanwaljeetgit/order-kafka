package com.sapient.orderkafka.controller;

import com.sapient.orderkafka.model.Order;
import com.sapient.orderkafka.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("KafkaOrder")
public class OrderController {

    @Autowired
    private OrderService orderService;

    OrderController(){
        System.out.println("test");
    }

    @PostMapping
    public ResponseEntity<Order> postOrder(@RequestBody Order order) throws ExecutionException, InterruptedException {
      return new ResponseEntity(orderService.sendToKafka(order), HttpStatus.OK);
    }

}
