package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
