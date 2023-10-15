package com.major.kafka.Controller;

import com.major.kafka.Entity.Order;
import com.major.kafka.Service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private Producer producer;

    @PostMapping("/create-order")
    public void createOrderRequest(@RequestBody Order order){
        producer.publish(order);
    }

}
