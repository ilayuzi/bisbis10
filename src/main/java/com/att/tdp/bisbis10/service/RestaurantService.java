package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurant(){
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurantsByCuisine(String cusineName){
        return restaurantRepository.getRestaurantsByCuisine(cusineName);
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Integer id){
        restaurantRepository.deleteById(id);
    }

    public boolean existById (Integer id){
        return restaurantRepository.existsById(id);
    }

    public Restaurant findById(Integer id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.orElseThrow(() -> new FileSystemNotFoundException("Restaurant not found with id: " + id));
    }
}
