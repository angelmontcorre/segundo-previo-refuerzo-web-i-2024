package com.co.ufps.previo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.co.ufps.previo.model.DTO.FavoritoDTO;
import com.co.ufps.previo.model.DTO.MangaDTO;
import com.co.ufps.previo.model.DTO.UsuarioDTO;
import com.co.ufps.previo.model.entity.Rol;
import com.co.ufps.previo.model.entity.Manga;
import com.co.ufps.previo.model.entity.Usuario;
import com.co.ufps.previo.model.entity.Favorito;
import com.co.ufps.previo.repository.RolRepository;
import com.co.ufps.previo.repository.UserRepository;
import com.co.ufps.previo.repository.FavoritoRepository;
import com.co.ufps.previo.repository.MangaRepository;
import com.co.ufps.previo.service.FavoritoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoritoServiceImpl implements FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UserRepository userRepository;
    private final MangaRepository mangaRepository;

    @Override
    public List<Favorito> getAllFavoritos(String username) {
        Optional<Usuario> usuario = userRepository.findByUsername(username);
        Optional<List<Favorito>> favoritos = favoritoRepository.getAllByUsuario(usuario);
        return favoritos.get();
    }

    @Override
    public Favorito getFavoritoById(Integer id) {
        Favorito favorito = favoritoRepository.getReferenceById(id);
        return favorito;
    }

    @Override
    public Favorito saveFavorito(String username, FavoritoDTO favoritoDTO) {
        Favorito favorito = new Favorito();

        Optional<Usuario> usuario = userRepository.findByUsername(username);
        Manga manga = mangaRepository.getReferenceById(favoritoDTO.getManga().getId());

        favorito.setUsuario(usuario.get());
        favorito.setManga(manga);

        // Aquí podrías realizar validaciones adicionales antes de guardar el favorito
        return favoritoRepository.save(favorito);
    }

    @Override
    public Favorito updateFavorito(Integer id, FavoritoDTO favoritoDTO) {
        // Buscar favorito existente por su ID
        Optional<Favorito> optionalFavorito = favoritoRepository.findById(id);

        if (optionalFavorito.isPresent()) {
            Favorito favorito = optionalFavorito.get();

            Usuario usuario = new Usuario();
            usuario.setId(favoritoDTO.getUsuario().getId());
            favorito.setUsuario(usuario);

            Manga manga = new Manga();
            manga.setId(favoritoDTO.getManga().getId());
            favorito.setManga(manga);

            return favoritoRepository.save(favorito);
        } else {
            // Manejar el caso en que no se encuentre el favorito con el ID dado
            throw new EntityNotFoundException("No se encontró un favorito con el ID proporcionado: " + id);
        }
    }

    @Override
    public void deleteFavorito(Integer id) {
        Favorito favorito = favoritoRepository.getReferenceById(id);
        favoritoRepository.delete(favorito);
    }

}
