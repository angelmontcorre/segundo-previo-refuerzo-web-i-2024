package com.co.ufps.previo.repository;

import com.co.ufps.previo.model.entity.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RolRepository extends JpaRepository<Rol,Integer> {

    Optional<Rol> findByNombre(String nombre);
}
