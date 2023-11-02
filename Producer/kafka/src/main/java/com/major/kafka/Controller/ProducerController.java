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

    @PostMapping("/create-order-1")
    public void createOrderRequest(){
        for(int i=0;i<100000;i++){
            Order order = new Order(i,"mobile","iphone",52000,1);
            producer.publish(order);
        }
    }

    @PostMapping("/create-order-2")
    public void createOrderRequest2(){
        for(int i=0;i<25000;i++){
            Order order = new Order(i,"mobile","iphone",52000,1);
            producer.publishNew(order);
        }
    }

}
