package com.waracle.cakemanager.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.service.CakeServiceImpl;

@Controller
class CakeWebController {

    @Autowired
    private CakeServiceImpl cakeService;

    @RequestMapping("/")
    public String viewAllCakes(Map<String, Object> model) {
        List<Cake> cakes = cakeService.getAllCakes();
        model.put("cakes", cakes);
        return "index";
    }
}