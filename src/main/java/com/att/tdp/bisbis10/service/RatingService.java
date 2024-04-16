package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public void addRating(Rating rating){
        ratingRepository.save(rating);
    }
}
