package com.co.ufps.previo.service;

import java.util.List;

import com.co.ufps.previo.model.DTO.MangaDTO;
import com.co.ufps.previo.model.entity.Manga;

public interface MangaService {

    public List<Manga> getAllMangas();
    public Manga getMangaById(Integer id);
    public Manga saveManga(MangaDTO mangaDTO);
    public Manga updateManga(Integer id, MangaDTO mangaDTO);
    public void deleteManga(Integer id);
    

}
