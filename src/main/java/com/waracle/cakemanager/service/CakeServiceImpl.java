package com.waracle.cakemanager.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.jpa.CakeRepository;

@Service
public class CakeServiceImpl implements CakeService {

    @Autowired
    private CakeRepository repository;

    @Override
    public List<Cake> getAllCakes() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Cake addCake(Cake cake) {
        return repository.save(cake);
    }


}
