package com.major.kafka.Repository;

import com.major.kafka.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepostory extends JpaRepository<Order,Integer> {

}
