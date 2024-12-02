package com.springsecurity.springsecurity.repositories;

import com.springsecurity.springsecurity.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
