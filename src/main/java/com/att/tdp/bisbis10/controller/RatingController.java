package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddRatingRequest;
import com.att.tdp.bisbis10.service.RatingService;
import com.att.tdp.bisbis10.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService raitingService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = raitingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }


    @PostMapping("/")
    public ResponseEntity<?> addRating(@RequestBody AddRatingRequest request) {
        try {
            Rating rating = new Rating();
            Double ratingValue = request.getRating();
            if(ratingValue < 0.0 || ratingValue > 5.0){
                return ResponseEntity.status(400).body("Rating must be between 0.0 to 5.0");
            }
            rating.setRating(ratingValue.floatValue());
            Integer restaurantId = request.getRestaurantId();
            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
            if(restaurant == null){
                return ResponseEntity.status(404).body("Restaurant not found with id: " + restaurantId);
            } else {
                rating.setRestaurant(restaurant);
                raitingService.addRating(rating);
                return ResponseEntity.status(200).build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding rating: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Integer id) {
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
