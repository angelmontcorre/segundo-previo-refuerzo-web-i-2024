package com.co.ufps.previo.service;

import java.util.List;

import com.co.ufps.previo.model.DTO.TipoDTO;
import com.co.ufps.previo.model.entity.Tipo;

public interface TipoService {

    public List<Tipo> getAllTipoes();
    public Tipo getTipoById(Integer id);
    public Tipo saveTipo(TipoDTO tipoDTO);
    public Tipo updateTipo(Integer id, TipoDTO tipoDTO);
    public void deleteTipo(Integer id);
    

}
