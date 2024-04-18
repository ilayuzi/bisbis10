package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public void addDish (Dish dish){
        dishRepository.save(dish);
    }

    public void deleteDish(Integer id){
        dishRepository.deleteById(id);
    }

    public Dish getDishById(Integer id){
        return dishRepository.findById(id).orElse(null);
    }

    public void updateDish(Dish updatedDish){
        dishRepository.save(updatedDish);
    }

    public Dish getDishByIdAndRestaurantId(Integer dishId, Integer restaurantId) {
        return dishRepository.findByIdAndRestaurantId(dishId, restaurantId);
    }



}
