package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.CuisineService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cuisines")
public class CuisineController {

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public ResponseEntity<List<Cuisine>> getAllCuisines() {
        List<Cuisine> cuisines = cuisineService.getAllCuisines();
        return ResponseEntity.ok(cuisines);
    }

    @PostMapping("/")
    public ResponseEntity<?> addCuisine(@RequestBody Cuisine cuisine) {
        try {
            cuisineService.addCusine(cuisine);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding cuisine: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuisine(@PathVariable Integer id) {
        try {
            cuisineService.deleteCusine(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting restaurant: " + e.getMessage());
        }
    }

//    @PostMapping("/createCuisine")
//    public String createEmployee(@RequestBody Cuisine entity) {
//        System.out.println("\nCreate a new Employee." + "\n");
//
//        // create a new Employee
//        Cuisine cuisine = new Cuisine();
//        cuisine.setCuisineName(entity.getCuisineName());
//
//        // save Employee
//        cuisineService.addCusine(cuisine);
//        return "Cuisine saved!!!";
//    }
//
//    @PostMapping("/createCuisineForRestaurant/{resId}")
//    public String createCuisineForRestaurant(@RequestBody Cuisine entity,
//                                           @PathVariable(name = "resId") Integer resId) {
//
//        // create a new Employee
//        Cuisine cuisine = new Cuisine();
//        cuisine.setCuisineName(entity.getCuisineName());
//
//
//        // save Employee
//        cuisineService.addCusine(cuisine);
//
//        // get a Project
//        Restaurant restaurant = restaurantService.getRestaurantById(resId);
//
//        // create Employee set
//        List<Cuisine> cuisines = new ArrayList<>();
//        cuisines.add(cuisine);
//
//        // assign Employee Set to Project
//        restaurant.setCuisines(cuisines);
//
//        // save Project
//        restaurantService.addRestaurant(restaurant);
//
//        return "Cuisine saved!!!";
//    }


}

