package com.co.ufps.previo.service;

import java.util.List;

import com.co.ufps.previo.model.DTO.FavoritoDTO;
import com.co.ufps.previo.model.DTO.MangaDTO;
import com.co.ufps.previo.model.entity.Favorito;

public interface FavoritoService {

    public List<Favorito> getAllFavoritos(String username);
    public Favorito getFavoritoById(Integer id);
    public Favorito saveFavorito(String username, FavoritoDTO favoritoDTO);
    public Favorito updateFavorito(Integer id, FavoritoDTO favoritoDTO);
    public void deleteFavorito(Integer id);
    

}
