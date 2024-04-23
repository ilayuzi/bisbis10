package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.DTO.RestaurantByIdResponseDto;
import com.att.tdp.bisbis10.model.DTO.RestaurantsResponseDto;
import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddRestaurantRequest;
import com.att.tdp.bisbis10.model.requests.UpdateRestaurantRequest;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineService cuisineService;

    // when i want to get the restaurants with all the details
//    public List<Restaurant> getAllRestaurants() {
//        return restaurantRepository.findAll();
//    }

    public List<RestaurantsResponseDto> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantsResponseDto> restaurantsDto = restaurants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return restaurantsDto;
    }

    public List<RestaurantsResponseDto> getRestaurantsByCuisine(String cusineName) {
        List<Restaurant> restaurants = restaurantRepository.getRestaurantsByCuisine(cusineName);
        List<RestaurantsResponseDto> restaurantsDto = restaurants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return restaurantsDto;
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
                cuisineService.addCuisine(cuisine);
            }
        }
        restaurantRepository.save(restaurant);
    }

    public RestaurantByIdResponseDto getRestaurantByIdRequest(Integer id) {
       Restaurant restaurant =  restaurantRepository.findById(id).orElse(null);
       if(restaurant == null){
           return null;
       }
        RestaurantByIdResponseDto restaurantByIdResponseDto = convertRestaurantToDTO(restaurant);
        return restaurantByIdResponseDto;
    }

    public Restaurant getRestaurantById(Integer id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public void deleteRestaurant(Integer id) {
        restaurantRepository.deleteById(id);
    }

    public void updateRestaurant(Restaurant existingRestaurant, UpdateRestaurantRequest updates) {

        if(updates.getName() != null){
            existingRestaurant.setName(updates.getName());
        }

        if(updates.getIsKosher() != null){
            existingRestaurant.setKosher(updates.getIsKosher());
        }

        if(updates.getCuisines() != null){
            List<String> cuisineNames = updates.getCuisines();
            List<Cuisine> cuisines = getCuisinesFromNames(cuisineNames);

            // Add restaurant to the restaurants list of each cuisine
            for (Cuisine cuisine : cuisines) {
                cuisine.getRestaurants().add(existingRestaurant);
                cuisineService.addCuisine(cuisine);
            }

//                List<Cuisine> cuisines = cuisineService.getCuisinesByNames(cuisineNames);
            existingRestaurant.setCuisines(cuisines);
        }

        restaurantRepository.save(existingRestaurant);
    }

    public boolean existById(Integer id) {
        return restaurantRepository.existsById(id);
    }

//    public Restaurant findById(Integer id) {
//        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
//        return optionalRestaurant.orElseThrow(() -> new FileSystemNotFoundException("Restaurant not found with id: " + id));
//    }

    public RestaurantsResponseDto convertToDTO(Restaurant restaurant) {
        RestaurantsResponseDto dto = new RestaurantsResponseDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAverageRating(restaurant.getAverageRating());
        dto.setKosher(restaurant.isKosher());
        dto.setCuisines(restaurant.getCuisines().stream()
                .map(Cuisine::getCuisineName)
                .collect(Collectors.toList()));
        return dto;
    }

    public RestaurantByIdResponseDto convertRestaurantToDTO(Restaurant restaurant) {
        RestaurantByIdResponseDto dto = new RestaurantByIdResponseDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAverageRating(restaurant.getAverageRating());
        dto.setKosher(restaurant.isKosher());
        dto.setCuisines(restaurant.getCuisines().stream()
                .map(Cuisine::getCuisineName)
                .collect(Collectors.toList()));
        dto.setDishes(restaurant.getDishes());
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
