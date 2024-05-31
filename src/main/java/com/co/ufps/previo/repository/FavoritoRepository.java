package com.co.ufps.previo.repository;

import com.co.ufps.previo.model.entity.Favorito;
import com.co.ufps.previo.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito,Integer> {

    Optional<List<Favorito>> getAllByUsuario(Optional<Usuario> usuario);
}
