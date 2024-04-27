package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.DTO.OrderItemDTO;
import com.att.tdp.bisbis10.model.requests.AddOrderRequest;
import com.att.tdp.bisbis10.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OrderItemService orderItemService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(AddOrderRequest request) {
        Integer resId = request.getRestaurantId();
        Restaurant restaurant = restaurantService.getRestaurantById(resId);
        if (restaurant == null) {
            throw new NullPointerException("Restaurant not found with id: " + resId);
        }
        if (restaurant.getDishes().isEmpty()) {
            throw new RuntimeException("There aren't dishes in restaurant " + restaurant.getId());
        }

        Order order = new Order();
        order.setRestaurant(restaurant);

        List<OrderItemDTO> items = request.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : items) {
            Integer dishId = itemDTO.getDishId();
            boolean dishExists = false;
            for (Dish dish : restaurant.getDishes()) { // loop to check if the dish exists in the restaurant
                if (dish.getId().equals(dishId)) {
                    dishExists = true;
                    OrderItem item = new OrderItem();
                    item.setAmount(itemDTO.getAmount());
                    item.setDishId(itemDTO.getDishId());
                    item.setOrder(order);
                    orderItems.add(item);
                    break; // Exit the loop once the dish is found
                }
                throw new RuntimeException("There is no dish with id " + dishId + " in restaurant " + restaurant.getId());
            }
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        orderItems.forEach(item -> orderItemService.addOrderItem(item));
        return order;
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public boolean existById(Integer id) {
        return orderRepository.existsById(id);
    }
}
