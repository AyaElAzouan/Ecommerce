package com.springsecurity.springsecurity.services;

import com.springsecurity.springsecurity.entities.Panier;
import com.springsecurity.springsecurity.entities.Produit;
import com.springsecurity.springsecurity.entities.User;
import com.springsecurity.springsecurity.repositories.PanierRepository;
import com.springsecurity.springsecurity.repositories.ProduitRepository;
import com.springsecurity.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierService {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private UserRepository userRepository;

    public Panier createPanier(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Panier panier = new Panier();
        panier.setUser(user);
        return panierRepository.save(panier);
    }

    public Panier getPanierById(Integer id) {
        return panierRepository.findBYIdUser(id).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
    }

    public Panier addProduitToPanier(Long panierId, Long produitId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Produit produit = produitRepository.findById(produitId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        panier.getProduits().add(produit);
        panier.setTotal(panier.getTotal() + produit.getPrice());
        return panierRepository.save(panier);
    }

    public Panier removeProduitFromPanier(Long panierId, Long produitId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Produit produit = produitRepository.findById(produitId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        if (panier.getProduits().remove(produit)) {
            panier.setTotal(panier.getTotal() - produit.getPrice());
            return panierRepository.save(panier);
        } else {
            throw new RuntimeException("Produit non trouvé dans le panier");
        }
    }

    public void deletePanier(Long id) {
        panierRepository.deleteById(id);
    }
}
