package com.co.ufps.previo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.co.ufps.previo.model.DTO.MangaDTO;
import com.co.ufps.previo.model.DTO.PaisDTO;
import com.co.ufps.previo.model.entity.Rol;
import com.co.ufps.previo.model.entity.Tipo;
import com.co.ufps.previo.model.entity.Pais;
import com.co.ufps.previo.model.entity.Manga;
import com.co.ufps.previo.repository.RolRepository;
import com.co.ufps.previo.repository.MangaRepository;
import com.co.ufps.previo.service.MangaService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MangaServiceImpl implements MangaService {

    private final MangaRepository mangaRepository;
    private final RolRepository rolRepository;

    @Override
    public List<Manga> getAllMangas() {
        List<Manga> mangas = mangaRepository.findAll();
        return mangas;
    }

    @Override
    public Manga getMangaById(Integer id) {
        Manga manga = mangaRepository.getReferenceById(id);
        return manga;
    }

    @Override
    public Manga saveManga(MangaDTO mangaDTO) {
        Manga manga = new Manga();
        manga.setNombre(mangaDTO.getNombre());
        manga.setFechaLanz(mangaDTO.getFechaLanz());
        manga.setTemporadas(mangaDTO.getTemporadas());

        Pais pais = new Pais();
        pais.setId(mangaDTO.getPais().getId());
        pais.setNombre(mangaDTO.getPais().getNombre());
        manga.setPais(pais);

        manga.setAnime(mangaDTO.getAnime());
        manga.setJuego(mangaDTO.getJuego());
        manga.setPelicula(mangaDTO.getPelicula());

        Tipo tipo = new Tipo();
        tipo.setId(mangaDTO.getTipo().getId());
        tipo.setNombre(mangaDTO.getTipo().getNombre());
        manga.setTipo(tipo);

        // Aquí podrías realizar validaciones adicionales antes de guardar el manga
        return mangaRepository.save(manga);
    }

    @Override
    public Manga updateManga(Integer id, MangaDTO mangaDTO) {
        // Buscar manga existente por su ID
        Optional<Manga> optionalManga = mangaRepository.findById(id);

        if (optionalManga.isPresent()) {
            Manga manga = optionalManga.get();
            // Actualizar los campos del manga con los valores del DTO
            manga.setNombre(mangaDTO.getNombre());
            manga.setFechaLanz(mangaDTO.getFechaLanz());
            manga.setTemporadas(mangaDTO.getTemporadas());

            Pais pais = new Pais();
            pais.setId(mangaDTO.getPais().getId());
            pais.setNombre(mangaDTO.getPais().getNombre());
            manga.setPais(pais);

            manga.setAnime(mangaDTO.getAnime());
            manga.setJuego(mangaDTO.getJuego());
            manga.setPelicula(mangaDTO.getPelicula());

            Tipo tipo = new Tipo();
            tipo.setId(mangaDTO.getTipo().getId());
            tipo.setNombre(mangaDTO.getTipo().getNombre());
            manga.setTipo(tipo);

            // Guardar el manga actualizado
            return mangaRepository.save(manga);
        } else {
            // Manejar el caso en que no se encuentre el manga con el ID dado
            throw new EntityNotFoundException("No se encontró un manga con el ID proporcionado: " + id);
        }
    }

    @Override
    public void deleteManga(Integer id) {
        Manga manga = mangaRepository.getReferenceById(id);
        mangaRepository.delete(manga);

    }

}
