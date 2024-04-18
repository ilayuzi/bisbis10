package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CuisineRepository extends JpaRepository<Cuisine,Integer> {
    List<Cuisine> findAllByCuisineNameIn(List<String> cuisineNames);
}
