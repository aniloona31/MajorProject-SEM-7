package com.major.kafka.Controller;

import com.major.kafka.Entity.Order;
import com.major.kafka.Service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class KafkaController {
    @Autowired
    private ConsumerService service;

    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);
    ExecutorService executorService = Executors.newFixedThreadPool(30);
    @KafkaListener(
            topics = "orders-2",
            id = "order2",
            groupId = "orderr",
            concurrency = "6"
    )
    public void listen(Order order){
        log.info("Recieved : {}",order);
        executorService.submit(() -> service.process(order));
    }

}
