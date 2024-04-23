package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddDishRequest;
import com.att.tdp.bisbis10.model.requests.UpdateDishRequest;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> addDish(@RequestBody AddDishRequest request, @PathVariable Integer id) {
        try {
            Dish dish  = dishService.addDish(request,id);
            return ResponseEntity.status(201).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(404).body("Error adding dish: " + e.getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.status(500).body("Error adding dish: " + e.getMessage());
        }

    }

    @DeleteMapping("/restaurants/{id}/dishes/{dishId}")
    public ResponseEntity<?> deleteDish(@PathVariable Integer dishId) {
        try {
            Dish dish = dishService.getDishById(dishId);
            if (dish == null) {
                return ResponseEntity.status(404).body("Dish not found with id " + dishId);
            }
            dishService.deleteDish(dishId);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting dish: " + e.getMessage());
        }
    }

    @GetMapping("/restaurants/{id}/dishes")
    public ResponseEntity<?> getDishesByRestaurantId(@PathVariable Integer id) {
        try {
            if (!restaurantService.existById(id)) {
                return ResponseEntity.status(404).body("The restaurant with ID " + id + " does not exist.");
            }
            Restaurant restaurant = restaurantService.getRestaurantById(id);
            return ResponseEntity.status(200).body(restaurant.getDishes());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting restaurant's dishes: " + e.getMessage());
        }
    }

    @PutMapping("/restaurants/{id}/dishes/{dishId}")
    public ResponseEntity<?> updateDish(@PathVariable Integer id, @PathVariable Integer dishId, @RequestBody UpdateDishRequest updates) {
        try {
            Dish existingDish = dishService.getDishByIdAndRestaurantId(dishId,id);
            if(existingDish == null){
                return ResponseEntity.status(404).body("Dish " + dishId + " not found in restaurant " + id);
            }
            dishService.updateDish(existingDish, updates);
            return ResponseEntity.status(200).body("Dish " + dishId + " updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating dish: " + e.getMessage());
        }
    }
}
