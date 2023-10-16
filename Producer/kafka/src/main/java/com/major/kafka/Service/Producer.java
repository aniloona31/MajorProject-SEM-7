package com.major.kafka.Service;

import com.major.kafka.Entity.Order;
import com.major.kafka.Repository.OrderRepostory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class Producer {

    @Autowired
    private KafkaTemplate<Integer, Order> kafkaTemplate;

    @Autowired
    private OrderRepostory orderRepostory;

    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);
    public void publish(Order order){
        Order addedOrder = orderRepostory.save(order);
        LOG.info("Processed: order->{}", addedOrder);

        CompletableFuture<SendResult<Integer, Order>> result = kafkaTemplate
                .send("orders", order);
        result.whenComplete((sr, ex) ->
                LOG.debug("Sent(key={},partition={}): {}",
                        sr.getProducerRecord().partition(),
                        sr.getProducerRecord().key(),
                        sr.getProducerRecord().value()));
    }
}
