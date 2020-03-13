package com.waracle.cakemanager.service;

import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.jpa.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CakeServiceImpl implements CakeService {

    @Autowired
    private CakeRepository repository;

    @Override
    public List<Cake> getAllCakes() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Cake getCake(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateCake(Cake cake) {
        repository.save(cake);
    }

    @Override
    public void deleteCake(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Cake cake) {
        repository.save(cake);
    }


}
