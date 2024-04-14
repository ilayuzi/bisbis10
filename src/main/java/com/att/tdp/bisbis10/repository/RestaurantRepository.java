package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
//    @Query("SELECT r FROM Restaurant r WHERE :cuisineName IN elements(r.cuisines)")
//    List<Restaurant> findByCuisinesContaining(String cuisineName);
}
