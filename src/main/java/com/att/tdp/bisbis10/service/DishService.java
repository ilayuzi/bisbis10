package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
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

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public void addDish (String name, String description, double price, Restaurant restaurant){
        Dish dish = Dish.builder().name(name).description(description).price(price).restaurant(restaurant).build();
        dishRepository.save(dish);
    }

    public void deleteDish(Integer id){
        dishRepository.deleteById(id);
    }

    public Dish getDishById(Integer id){
        return dishRepository.findById(id).orElse(null);
    }

    public void updateDish(Dish existingDish,Map<String, Object> updates ){
        if (updates.containsKey("name")) {
            existingDish.setName((String) updates.get("description"));
        }
        if (updates.containsKey("description")) {
            existingDish.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("price")) {
            existingDish.setPrice(((Integer) updates.get("price")).doubleValue());
        }
        dishRepository.save(existingDish);
    }

    public Dish getDishByIdAndRestaurantId(Integer dishId, Integer restaurantId) {
        return dishRepository.findByIdAndRestaurantId(dishId, restaurantId);
    }

    public boolean existById (Integer id){
        return dishRepository.existsById(id);
    }



}
