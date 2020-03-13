package com.waracle.cakemanager.service;

import com.waracle.cakemanager.jpa.Cake;

import java.util.List;

interface CakeService {

    List<Cake> getAllCakes();

    void save(Cake cake);

    Cake getCake(Long id);

    void updateCake(Cake cake);

    void deleteCake(Long id);
}
