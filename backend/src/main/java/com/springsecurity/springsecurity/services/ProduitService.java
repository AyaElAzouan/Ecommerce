package com.springsecurity.springsecurity.services;

import com.springsecurity.springsecurity.entities.Produit;
import com.springsecurity.springsecurity.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }


    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produitDetails) {
        Produit produit = produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        produit.setName(produitDetails.getName());
        produit.setDescription(produitDetails.getDescription());
        produit.setPrice(produitDetails.getPrice());
        produit.setImageUrl(produitDetails.getImageUrl());
        produit.setInStock(produitDetails.isInStock());

        return produitRepository.save(produit);
    }

    public void deleteProduit(Long id) {
        Produit produit = produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        produitRepository.delete(produit);
    }
}
