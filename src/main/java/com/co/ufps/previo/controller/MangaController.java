package com.co.ufps.previo.controller;

import com.co.ufps.previo.model.DTO.MangaDTO;
import com.co.ufps.previo.model.entity.Manga;
import com.co.ufps.previo.service.MangaService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mangas")
@RequiredArgsConstructor
public class MangaController {

    private final MangaService mangaService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping()
    public ResponseEntity<List<Manga>> getAllMangas() {
        try {
            List<Manga> mangasResponse = mangaService.getAllMangas();
            return ResponseEntity.ok(mangasResponse);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Integer id) {
        try {
            Manga manga = mangaService.getMangaById(id);
            return ResponseEntity.ok(manga);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping()
    public ResponseEntity<Manga> saveManga(@RequestBody MangaDTO mangaDTO) {
        try {
            Manga manga = mangaService.saveManga(mangaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(manga);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Integer id, @RequestBody MangaDTO mangaDTO) {
        try {
            Manga manga = mangaService.updateManga(id, mangaDTO);
            return ResponseEntity.ok(manga);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteManga(@PathVariable Integer id) {
        this.mangaService.deleteManga(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", true);
        return ResponseEntity.ok(respuesta);
    }

}
