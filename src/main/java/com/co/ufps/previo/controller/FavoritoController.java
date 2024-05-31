package com.co.ufps.previo.controller;

import com.co.ufps.previo.model.DTO.FavoritoDTO;
import com.co.ufps.previo.model.DTO.MangaDTO;
import com.co.ufps.previo.model.entity.Favorito;
import com.co.ufps.previo.service.FavoritoService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{username}/favoritos")
    public ResponseEntity<List<Favorito>> getAllFavoritos(@PathVariable String username) {
        try {
            List<Favorito> favoritosResponse = favoritoService.getAllFavoritos(username);
            return ResponseEntity.ok(favoritosResponse);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Favorito> getFavoritoById(@PathVariable Integer id) {
        try {
            Favorito favorito = favoritoService.getFavoritoById(id);
            return ResponseEntity.ok(favorito);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/{username}/favoritos")
    public ResponseEntity<Favorito> saveFavorito(@PathVariable String username, 
    @RequestBody FavoritoDTO favoritoDTO) {
        try {
            Favorito favorito = favoritoService.saveFavorito(username, favoritoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(favorito);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Favorito> updateFavorito(@PathVariable Integer id, @RequestBody FavoritoDTO favoritoDTO) {
        try {
            Favorito favorito = favoritoService.updateFavorito(id, favoritoDTO);
            return ResponseEntity.ok(favorito);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFavorito(@PathVariable Integer id) {
        this.favoritoService.deleteFavorito(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", true);
        return ResponseEntity.ok(respuesta);
    }

}
