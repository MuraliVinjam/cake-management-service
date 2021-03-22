package com.waracle.cakemanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.service.CakeServiceImpl;

@RestController
class CakeRestController {

    @Autowired
    private CakeServiceImpl cakeService;

    @GetMapping("/cakes")
    @Secured({"ROLE_CM_GET"})
    public List<Cake> getAllCakes() {
        return cakeService.getAllCakes();
    }

    @PostMapping("/cakes")
    @Secured({"ROLE_CM_ADD"})
    public Cake addCake(@RequestBody Cake cake) {
        return cakeService.addCake(cake);
    }
}