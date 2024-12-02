package com.springsecurity.springsecurity.controllers;

import com.springsecurity.springsecurity.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springsecurity.springsecurity.entities.Panier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/panier")
@CrossOrigin(origins = "http://localhost:4200")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @PostMapping("/{userId}")
    public Panier createPanier(@PathVariable Integer userId) {
        return panierService.createPanier(userId);
    }

    @GetMapping("/{id}")
    public Panier getPanierById(@PathVariable Integer id) {
        return panierService.getPanierById(id);
    }


    @PostMapping("/{panierId}/{produitId}")
    public Panier addProduitToPanier(@PathVariable Long panierId, @PathVariable Long produitId) {
        return panierService.addProduitToPanier(panierId, produitId);
    }

    @DeleteMapping("/{panierId}/{produitId}")
    public Panier removeProduitFromPanier(@PathVariable Long panierId, @PathVariable Long produitId) {
        return panierService.removeProduitFromPanier(panierId, produitId);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePanier(@PathVariable Long id) {
        panierService.deletePanier(id);
    }
}

