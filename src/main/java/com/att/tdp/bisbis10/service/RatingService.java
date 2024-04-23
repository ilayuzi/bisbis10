package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.model.requests.AddRatingRequest;
import com.att.tdp.bisbis10.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RestaurantService restaurantService;

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public void addRating(AddRatingRequest request) {
        Double ratingValue = request.getRating();
        if (ratingValue < 0.0 || ratingValue > 5.0) {
            throw new IllegalArgumentException("Rating must be between 0.0 to 5.0");
        }
        Rating rating = new Rating();
        rating.setRating(ratingValue.floatValue());
        Integer restaurantId = request.getRestaurantId();
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new NullPointerException("Restaurant not found with id: " + restaurantId);
        }

        rating.setRestaurant(restaurant);
        ratingRepository.save(rating);
    }

    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }

    public boolean existById(Integer id) {
        return ratingRepository.existsById(id);
    }
}
