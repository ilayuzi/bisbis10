package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.DTO.OrderItemDTO;
import com.att.tdp.bisbis10.model.requests.AddOrderRequest;
import com.att.tdp.bisbis10.service.OrderItemService;
import com.att.tdp.bisbis10.service.OrderService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping()
    public ResponseEntity<List<Order>> getAllDishes() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping()
    public ResponseEntity<?> addOrder(@RequestBody AddOrderRequest request) {
        try {
            Order order = orderService.addOrder(request);
            return ResponseEntity.status(200).body(Map.of("orderId", order.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding order: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        try {
            if (!orderService.existById(id)) {
                return ResponseEntity.status(404).body("The order with ID " + id + " does not exist.");
            }
            orderService.deleteOrder(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting raiting: " + e.getMessage());
        }
    }
}
