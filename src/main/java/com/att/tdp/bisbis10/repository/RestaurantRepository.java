package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN r.cuisines c WHERE c.cuisineName = :cuisineName")
    List<Restaurant> getRestaurantsByCuisine(@Param("cuisineName") String cuisineName);

}
