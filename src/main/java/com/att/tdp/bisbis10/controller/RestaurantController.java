package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.model.DTO.RestaurantByIdResponseDto;
import com.att.tdp.bisbis10.model.DTO.RestaurantsResponseDto;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddRestaurantRequest;
import com.att.tdp.bisbis10.model.requests.UpdateRestaurantRequest;
import com.att.tdp.bisbis10.service.CuisineService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
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

      // get the restaurats with all the details
//    @GetMapping("")
//    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
//        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
//        return ResponseEntity.ok(restaurants);
//    }


    @GetMapping("")
    public ResponseEntity<?> getAllRestaurants() {
        try{
            List<RestaurantsResponseDto> restaurantsDto = restaurantService.getAllRestaurants();
            return ResponseEntity.ok(restaurantsDto);
        } catch(Exception e){
            return ResponseEntity.status(500).body("Error getting restaurants: " + e.getMessage());
        }

    }



//    @GetMapping(params = "cuisine")
    @GetMapping("/")
    public ResponseEntity<?> getRestaurantsByCuisine(@RequestParam(value = "cuisine") String cuisine){
        try{
            List<RestaurantsResponseDto> restaurantsDto = restaurantService.getRestaurantsByCuisine(cuisine);
            return ResponseEntity.ok(restaurantsDto);
        } catch(Exception e){
            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            return ResponseEntity.status(500).body("Error getting restaurants by cuisine: " + e.getMessage());
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
        try {
            restaurantService.addRestaurant(request);
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
            RestaurantByIdResponseDto restaurantDto = restaurantService.getRestaurantByIdRequest(id);
            return ResponseEntity.status(200).body(restaurantDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting restaurant: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Integer id, @RequestBody UpdateRestaurantRequest updates){
        try{
            Restaurant existingRestaurant = restaurantService.getRestaurantById(id);

            if(existingRestaurant == null){
                return ResponseEntity.status(404).body("Restaurant not found with id " + id);
            }

            restaurantService.updateRestaurant(existingRestaurant, updates);
            System.out.println("Restaurant " + id + " updated successfully");
            return ResponseEntity.status(200).body(null);
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
