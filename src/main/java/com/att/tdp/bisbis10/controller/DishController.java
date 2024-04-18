package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return ResponseEntity.ok(dishes);
    }

    @PostMapping("/restaurants/{id}/dishes")
    public ResponseEntity<?> addDish(@RequestBody Dish dish, @PathVariable Integer id) {
        try {
            Optional<Restaurant> optionalRestaurant = Optional.ofNullable(restaurantService.findById(id));
            if (optionalRestaurant.isPresent()) {
                Restaurant restaurant = optionalRestaurant.get();
                dish.setRestaurant(restaurant);
                dishService.addDish(dish);
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.status(404).body("Restaurant not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding dish: " + e.getMessage());
        }

    }

    @DeleteMapping("/restaurants/{id}/dishes/{dishId}")
    public ResponseEntity<?> deleteDish(@PathVariable Integer dishId) {
        try {
            dishService.deleteDish(dishId);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting dish: " + e.getMessage());
        }
    }
}
