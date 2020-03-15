package com.waracle.cakemanager.rest;


        import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.service.CakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
class CakeController {

    @Autowired private CakeServiceImpl cakeService;

    private static final String CAKES_FILENAME = "cakes.json";

    @GetMapping("/cakes")
    @Secured({"ROLE_CM_GET_ALL"})
    public Collection<Cake> getAllCakes() {
        return cakeService.getAllCakes();
    }

    @GetMapping("/cakes/{id}")
    @Secured({"ROLE_CM_GET_ONE"})
    public Cake getCake(@PathVariable("id") Long id) {
        return cakeService.getCake(id);
    }

    @PutMapping("/cakes/{id}")
    @Secured({"ROLE_CM_UPDATE_ONE"})
    public void updateCake(@PathVariable("id") Long id, @RequestBody Cake cake) {
        cake.setId(id);
        cakeService.updateCake(cake);
    }

    @DeleteMapping("/cakes/{id}")
    @Secured({"ROLE_CM_DELETE_ONE"})
    public void deleteCake(@PathVariable("id") Long id) {
        cakeService.deleteCake(id);
    }

    @PostMapping("/cakes")
    @Secured({"ROLE_CM_ADD_ONE"})
    public ResponseEntity<?> addCake(@RequestBody Cake cake) {
        cakeService.save(cake);
        return new ResponseEntity<Cake>(cake, HttpStatus.CREATED);
    }
}