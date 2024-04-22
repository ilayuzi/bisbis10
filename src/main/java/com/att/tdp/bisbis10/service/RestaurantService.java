package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.RestaurantResponseDto;
import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddRestaurantRequest;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineService cuisineService;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

//    public List<RestaurantResponseDto> getAllRestaurants(){
//        List<Restaurant> restaurants = restaurantRepository.findAll();
//        List<RestaurantResponseDto> restaurantsDto = restaurants.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//        return restaurantsDto;
//    }

    public List<Restaurant> getRestaurantsByCuisine(String cusineName) {
        return restaurantRepository.getRestaurantsByCuisine(cusineName);
    }

    public void addRestaurant(AddRestaurantRequest restaurantDetails) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDetails.getName());
        restaurant.setKosher(restaurantDetails.isKosher());
        List<String> cuisineNames = (restaurantDetails.getCuisines());
        if (cuisineNames != null && !cuisineNames.isEmpty()) {
            List<Cuisine> cuisines = getCuisinesFromNames(cuisineNames);
            restaurant.setCuisines(cuisines);

//          Add restaurant to the restaurants list of each cuisine
            for (Cuisine cuisine : cuisines) {
                cuisine.getRestaurants().add(restaurant);
                cuisineService.addCusine(cuisine);
            }
        }
        restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Integer id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public void deleteRestaurant(Integer id) {
        restaurantRepository.deleteById(id);
    }

    public void updateRestaurant(Restaurant existingRestaurant, Map<String, Object> updates) {

        if(updates.containsKey("cuisines")){
            List<String> cuisineNames = (List<String>) updates.get("cuisines");
            List<Cuisine> cuisines = getCuisinesFromNames(cuisineNames);

            // Add restaurant to the restaurants list of each cuisine
            for (Cuisine cuisine : cuisines) {
                cuisine.getRestaurants().add(existingRestaurant);
                cuisineService.addCusine(cuisine);
            }

//                List<Cuisine> cuisines = cuisineService.getCuisinesByNames(cuisineNames);
            existingRestaurant.setCuisines(cuisines);
        }
        restaurantRepository.save(existingRestaurant);
    }

    public boolean existById(Integer id) {
        return restaurantRepository.existsById(id);
    }

    public Restaurant findById(Integer id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.orElseThrow(() -> new FileSystemNotFoundException("Restaurant not found with id: " + id));
    }

    public RestaurantResponseDto convertToDTO(Restaurant restaurant) {
        RestaurantResponseDto dto = new RestaurantResponseDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAverageRating(restaurant.getAverageRating());
        dto.setKosher(restaurant.isKosher());
        dto.setCuisines(restaurant.getCuisines().stream()
                .map(Cuisine::getCuisineName)
                .collect(Collectors.toList()));
        return dto;
    }

    public List<Cuisine> getCuisinesFromNames(List<String> cuisineNames) {
        List<Cuisine> cuisines = new ArrayList<>();

        for (String name : cuisineNames) {
            Optional<Cuisine> optionalCuisine = cuisineService.getCuisineByName(name);

            if (optionalCuisine.isEmpty()) {
                Cuisine cuisine = new Cuisine();
                cuisine.setCuisineName(name);
                cuisines.add(cuisine);
            } else {
                Cuisine cuisine = optionalCuisine.get();
                cuisines.add(cuisine);
            }
        }

        return cuisines;
    }
}
