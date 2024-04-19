package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.CuisineService;
import com.att.tdp.bisbis10.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CuisineService cuisineService;

    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> list = restaurantService.getAllRestaurant();
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cuisine")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCuisine(@RequestParam(value = "cuisine") String cuisine){
        try{
            List<Restaurant> restaurants = restaurantService.getRestaurantsByCuisine(cuisine);
            return ResponseEntity.ok().body(restaurants);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @PostMapping("/")
//    public ResponseEntity<?> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
//        try {
//            restaurantService.addRestaurant(restaurant);
//            return ResponseEntity.status(201).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error adding restaurant: " + e.getMessage());
//        }
//    }

    @PostMapping("/")
    public ResponseEntity<?> addRestaurant(@Valid @RequestBody Map<String,Object> restaurantDetails) {
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setName((String) restaurantDetails.get("name"));
            restaurant.setKosher((boolean) restaurantDetails.get("isKosher"));
            List<String> cuisineNames = (List<String>) restaurantDetails.get("cuisines");
            if (cuisineNames != null && !cuisineNames.isEmpty()) {
                List<Cuisine> newCuisines = new ArrayList<>();
                for (String name : cuisineNames){
                    Optional<Cuisine> optionalCuisine = cuisineService.getCuisineByName(name);
                    if (optionalCuisine.isEmpty()) {
                        Cuisine cuisine = new Cuisine();
                        cuisine.setCuisineName(name);
                        newCuisines.add(cuisine);
                } else {
                        Cuisine cuisine = optionalCuisine.get();
                        newCuisines.add(cuisine);
                    }
                }

                restaurant.setCuisines(newCuisines);

//              Add restaurant to the restaurants list of each cuisine
                for (Cuisine cuisine : newCuisines) {
                    cuisine.getRestaurants().add(restaurant);
                    cuisineService.addCusine(cuisine);
                }

//                List<Cuisine> cuisines = cuisineService.getCuisinesByNames(cuisineNames);
//                cuisines.removeIf(Objects::isNull);
//                restaurant.setCuisines(cuisines);

//                 Add restaurant to the restaurants list of each cuisine
//                for (Cuisine cuisine : cuisines) {
//                    cuisine.getRestaurants().add(restaurant);
//                    cuisineService.addCusine(cuisine);
//                }
            }
            restaurantService.addRestaurant(restaurant);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding restaurant: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable Integer id) {
        try {
            if (!restaurantService.existById(id)) {
                return ResponseEntity.status(404).body("The restaurant with ID " + id + " does not exist.");
            }
            Restaurant restaurant = restaurantService.getRestaurantById(id);
            return ResponseEntity.status(200).body(restaurant);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting restaurant: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Integer id, @RequestBody Map<String, Object> updates){
        try{
            Restaurant existingRestaurant = restaurantService.getRestaurantById(id);

            if(existingRestaurant == null){
                return ResponseEntity.status(404).body("Restaurant not found with id " + id);
            }
            if(updates.containsKey("cuisines")){
                List<String> cuisineNames = (List<String>) updates.get("cuisines");
                List<Cuisine> cuisines = cuisineService.getCuisinesByNames(cuisineNames);
                existingRestaurant.setCuisines(cuisines);
            }

            restaurantService.updateRestaurant(existingRestaurant);
            return ResponseEntity.status(200).body("Restaurant " + id + " updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error updating restaurant: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Integer id) {
        try {
            if (!restaurantService.existById(id)) {
                return ResponseEntity.status(404).body("The restaurant with ID " + id + " does not exist.");
            }
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting restaurant: " + e.getMessage());
        }
    }

    @PostMapping("/createRestaurant")
    public String createRestaurant(@RequestBody Restaurant entity) {

        // new Project
        Restaurant restaurant = new Restaurant();
        restaurant.setName(entity.getName());
        restaurant.setKosher(entity.isKosher());


        // save Project
        restaurantService.addRestaurant(restaurant);

        return "Restaurant saved!!!";
    }


}
