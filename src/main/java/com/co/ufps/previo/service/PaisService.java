package com.co.ufps.previo.service;

import java.util.List;

import com.co.ufps.previo.model.DTO.PaisDTO;
import com.co.ufps.previo.model.entity.Pais;

public interface PaisService {

    public List<Pais> getAllPaises();
    public Pais getPaisById(Integer id);
    public Pais savePais(PaisDTO paisDTO);
    public Pais updatePais(Integer id, PaisDTO paisDTO);
    public void deletePais(Integer id);
    

}
