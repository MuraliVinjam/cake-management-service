package com.waracle.cakemanager.service;

import java.util.List;
import com.waracle.cakemanager.jpa.Cake;

interface CakeService {

    List<Cake> getAllCakes();

    Cake addCake(Cake cake);

}
