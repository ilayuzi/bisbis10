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
    private CuisineRepository cusineRepository;

    public List<Cuisine> getAllCuisines() {
        return cusineRepository.findAll();
    }

    public void addCusine(Cuisine cusine) {
        cusineRepository.save(cusine);
    }

    public void deleteCusine(Integer id) {
        cusineRepository.deleteById(id);
    }

    public boolean existById(Integer id) {
        return cusineRepository.existsById(id);
    }
}
