package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService raitingService;

    @PostMapping("/")
    public ResponseEntity<?> addRestaurant(@RequestBody Rating raiting) {
        try {
            raitingService.addRating(raiting);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding rating: " + e.getMessage());
        }
    }
}
