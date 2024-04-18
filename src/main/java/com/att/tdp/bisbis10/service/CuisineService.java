package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuisineService {

    @Autowired
    private CuisineRepository cuisineRepository;

    public List<Cuisine> getAllCuisines() {
        return cuisineRepository.findAll();
    }

    public void addCusine(Cuisine cusine) {
        cuisineRepository.save(cusine);
    }

    public void deleteCusine(Integer id) {
        cuisineRepository.deleteById(id);
    }

    public boolean existById(Integer id) {
        return cuisineRepository.existsById(id);
    }

    public List<Cuisine> getCuisinesByNames(List<String> cuisineNames){
        return cuisineRepository.findAllByCuisineNameIn(cuisineNames);
    }
}
