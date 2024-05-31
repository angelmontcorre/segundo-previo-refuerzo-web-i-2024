package com.co.ufps.previo.repository;

import com.co.ufps.previo.model.entity.Usuario;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);

}
