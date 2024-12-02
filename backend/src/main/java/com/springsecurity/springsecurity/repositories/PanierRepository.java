package com.springsecurity.springsecurity.repositories;

import com.springsecurity.springsecurity.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PanierRepository extends JpaRepository<Panier,Long> {
    @Query("SELECT a FROM Panier a WHERE a.user.id = :idUser")
    Optional<Panier> findBYIdUser(@Param("idUser") Integer id);
}
