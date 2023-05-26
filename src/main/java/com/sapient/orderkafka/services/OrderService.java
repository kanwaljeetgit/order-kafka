package com.sapient.orderkafka.services;

import com.sapient.orderkafka.model.Order;
import com.sapient.orderkafka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String , Order> kafkaTemplate;

    @KafkaListener(topics = "order")
    public void listenOrderTopic(Order order){
        System.out.println("order consumed "+order);
        orderRepository.save(order);
    }

    public Order sendToKafka(Order order) throws ExecutionException, InterruptedException {
       return kafkaTemplate.send("order",order).get().getProducerRecord().value();
    }

}
