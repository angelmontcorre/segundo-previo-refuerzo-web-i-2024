package com.co.ufps.previo.controller;

import com.co.ufps.previo.model.DTO.PaisDTO;
import com.co.ufps.previo.model.entity.Pais;
import com.co.ufps.previo.service.PaisService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pais")
@RequiredArgsConstructor
public class PaisController {

    private final PaisService paisService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping()
    public ResponseEntity<List<Pais>> getAllPais() {
        try {
            List<Pais> paisResponse = paisService.getAllPaises();
            return ResponseEntity.ok(paisResponse);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Pais> getPaisById(@PathVariable Integer id) {
        try {
            Pais pais = paisService.getPaisById(id);
            return ResponseEntity.ok(pais);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping()
    public ResponseEntity<Pais> savePais(@RequestBody PaisDTO paisDTO) {
        try {
            Pais pais = paisService.savePais(paisDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(pais);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Pais> updatePais(@PathVariable Integer id, @RequestBody PaisDTO paisDTO) {
        try {
            Pais pais = paisService.updatePais(id, paisDTO);
            return ResponseEntity.ok(pais);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePais(@PathVariable Integer id) {
        this.paisService.deletePais(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", true);
        return ResponseEntity.ok(respuesta);
    }

}
