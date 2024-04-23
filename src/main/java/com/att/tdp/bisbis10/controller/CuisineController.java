package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddCuisineRequest;
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

    @GetMapping("")
    public ResponseEntity<List<Cuisine>> getAllCuisines() {
        List<Cuisine> cuisines = cuisineService.getAllCuisines();
        return ResponseEntity.ok(cuisines);
    }

    @PostMapping("")
    public ResponseEntity<?> addCuisine(@RequestBody AddCuisineRequest request) {
        try {
            cuisineService.addCuisineByRequest(request);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding cuisine: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuisine(@PathVariable Integer id) {
        try {
            if (!cuisineService.existById(id)) {
                return ResponseEntity.status(404).body("The cuisine with ID " + id + " does not exist.");
            }
            cuisineService.deleteCuisine(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting restaurant: " + e.getMessage());
        }
    }


}

