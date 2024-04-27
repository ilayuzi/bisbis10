package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddDishRequest;
import com.att.tdp.bisbis10.model.requests.UpdateDishRequest;
import com.att.tdp.bisbis10.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantService restaurantService;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish addDish(AddDishRequest request, Integer resId) {
        // Checking whether there is a restaurant with the id in order to add the dish to it
        Restaurant restaurant = restaurantService.getRestaurantById(resId);
        if (restaurant == null) {
            throw new NullPointerException("Restaurant not found with id: " + resId);
        }
        String name = request.getName();
        for (Dish dish : restaurant.getDishes()) {
            if (dish.getName().equals(name)) {
                throw new RuntimeException("There is already a dish with name " + name + " at restaurant " + resId);
            }
        }
        String description = request.getDescription();
        double price = request.getPrice();
        Dish dish = Dish.builder().name(name).description(description).price(price).restaurant(restaurant).build();
        dishRepository.save(dish);
        return dish;
    }

    public void deleteDish(Integer id) {
        dishRepository.deleteById(id);
    }

    public Dish getDishById(Integer id) {
        return dishRepository.findById(id).orElse(null);
    }

    public void updateDish(Dish existingDish, UpdateDishRequest updates) {
        if (updates.getName() != null) {
            existingDish.setName(updates.getName());
        }
        if (updates.getDescription() != null) {
            existingDish.setDescription(updates.getDescription());
        }
        if (updates.getPrice() != null) {
            existingDish.setPrice(updates.getPrice());
        }
        dishRepository.save(existingDish);
    }

    public Dish getDishByIdAndRestaurantId(Integer dishId, Integer restaurantId) {
        return dishRepository.findByIdAndRestaurantId(dishId, restaurantId);
    }

    public boolean existById(Integer id) {
        return dishRepository.existsById(id);
    }


}
