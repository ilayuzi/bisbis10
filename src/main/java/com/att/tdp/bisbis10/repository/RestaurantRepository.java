package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
//    @Query("SELECT restaurant_id FROM restaurant_cuisines WHERE :cuisineName MEMBER OF restaurant_cuisines.cuisines")
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN r.cuisines c WHERE c = :cuisineName")
    List<Restaurant> getRestaurantsByCuisine(@Param("cuisineName") String cuisineName);
}
