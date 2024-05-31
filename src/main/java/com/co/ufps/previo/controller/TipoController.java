package com.co.ufps.previo.controller;

import com.co.ufps.previo.model.DTO.TipoDTO;
import com.co.ufps.previo.model.entity.Tipo;
import com.co.ufps.previo.service.TipoService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo")
@RequiredArgsConstructor
public class TipoController {

    private final TipoService tipoService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping()
    public ResponseEntity<List<Tipo>> getAllTipos() {
        try {
            List<Tipo> tiposResponse = tipoService.getAllTipoes();
            return ResponseEntity.ok(tiposResponse);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Tipo> getTipoById(@PathVariable Integer id) {
        try {
            Tipo tipo = tipoService.getTipoById(id);
            return ResponseEntity.ok(tipo);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping()
    public ResponseEntity<Tipo> saveTipo(@RequestBody TipoDTO tipoDTO) {
        try {
            Tipo tipo = tipoService.saveTipo(tipoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(tipo);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Tipo> updateTipo(@PathVariable Integer id, @RequestBody TipoDTO tipoDTO) {
        try {
            Tipo tipo = tipoService.updateTipo(id, tipoDTO);
            return ResponseEntity.ok(tipo);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTipo(@PathVariable Integer id) {
        this.tipoService.deleteTipo(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", true);
        return ResponseEntity.ok(respuesta);
    }

}
