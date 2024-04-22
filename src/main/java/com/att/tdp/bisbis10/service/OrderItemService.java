package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItem(){
        return orderItemRepository.findAll();
    }

    public void addOrderItem (OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Integer id){
        orderItemRepository.deleteById(id);
    }

    public boolean existById (Integer id){
        return orderItemRepository.existsById(id);
    }
}
