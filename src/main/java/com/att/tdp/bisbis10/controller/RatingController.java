package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RatingService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService raitingService;

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = raitingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }

    @PostMapping("/")
    public ResponseEntity<?> addRating(@RequestBody Rating raiting) {
        try {
            raitingService.addRating(raiting);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding rating: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Long id) {
        try {
            if (!raitingService.existById(id)) {
                return ResponseEntity.status(404).body("The rating with ID " + id + " does not exist.");
            }
            raitingService.deleteRating(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting raiting: " + e.getMessage());
        }
    }
}
