package com.major.kafka.Controller;

import com.major.kafka.Entity.Order;
import com.major.kafka.Service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private ConsumerService service;

    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(
            topics = "orders",
            id = "order",
            groupId = "order-1"
    )
    public void listen(Order order){
        log.info("Recieved : {}",order);
        service.process(order);
    }


}
