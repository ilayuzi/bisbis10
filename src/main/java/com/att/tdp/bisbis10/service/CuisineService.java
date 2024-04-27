package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.model.requests.AddCuisineRequest;
import com.att.tdp.bisbis10.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuisineService {

    @Autowired
    private CuisineRepository cuisineRepository;

    public List<Cuisine> getAllCuisines() {
        return cuisineRepository.findAll();
    }

    public void addCuisineByRequest(AddCuisineRequest request) {
        Cuisine cuisine = new Cuisine();
        cuisine.setCuisineName(request.getCuisineName());
        cuisineRepository.save(cuisine);
    }

    public void addCuisine(Cuisine cuisine) {
        cuisineRepository.save(cuisine);
    }

    public void deleteCuisine(Integer id) {
        cuisineRepository.deleteById(id);
    }

    public boolean existById(Integer id) {
        return cuisineRepository.existsById(id);
    }

    public List<Cuisine> getCuisinesByNames(List<String> cuisineNames) {
        return cuisineRepository.findAllByCuisineNameIn(cuisineNames);
    }

    public Optional<Cuisine> getCuisineByName(String cuisineName) {
        return cuisineRepository.findByCuisineName(cuisineName);
    }
}
