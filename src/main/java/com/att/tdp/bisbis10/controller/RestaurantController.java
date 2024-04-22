package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.RestaurantResponseDto;
import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddRestaurantRequest;
import com.att.tdp.bisbis10.service.CuisineService;
import com.att.tdp.bisbis10.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CuisineService cuisineService;

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

//    @GetMapping("")
//    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
//        List<RestaurantResponseDto> restaurantsDto = restaurantService.getAllRestaurants();
//        return ResponseEntity.ok(restaurantsDto);
//    }



//    @GetMapping(params = "cuisine")
    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCuisine(@RequestParam(value = "cuisine") String cuisine){
        try{
            List<Restaurant> restaurants = restaurantService.getRestaurantsByCuisine(cuisine);
            return ResponseEntity.ok().body(restaurants);
        } catch(Exception e){
            System.out.println(e.getMessage());
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

    @PostMapping("")
    public ResponseEntity<?> addRestaurant(@RequestBody AddRestaurantRequest request) {
//        try {
            restaurantService.addRestaurant(request);
            return ResponseEntity.status(201).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error adding restaurant: " + e.getMessage());
//        }
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

            restaurantService.updateRestaurant(existingRestaurant, updates);
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


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException exp
//    ) {
//        var errors = new HashMap<String,String>();
//        exp.getBindingResult().getAllErrors()
//                .forEach(error -> {
//                    var fieldName = ((FieldError) error).getField();
//                    var errorMessage = error.getDefaultMessage();
//                    errors.put(fieldName,errorMessage);
//                });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
}
