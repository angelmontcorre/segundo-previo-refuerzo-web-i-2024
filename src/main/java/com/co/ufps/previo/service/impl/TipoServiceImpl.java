package com.co.ufps.previo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.co.ufps.previo.model.DTO.TipoDTO;
import com.co.ufps.previo.model.entity.Tipo;
import com.co.ufps.previo.repository.TipoRepository;
import com.co.ufps.previo.service.TipoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoServiceImpl implements TipoService {

    private final TipoRepository tipoRepository;

    @Override
    public List<Tipo> getAllTipoes() {
        List<Tipo> tipoes = tipoRepository.findAll();
        return tipoes;
    }

    @Override
    public Tipo getTipoById(Integer id) {
        Tipo tipo = tipoRepository.getReferenceById(id);
        return tipo;
    }

    @Override
    public Tipo saveTipo(TipoDTO tipoDTO) {
        Tipo tipo = new Tipo();
        tipo.setNombre(tipoDTO.getNombre());
        return tipoRepository.save(tipo);
    }

    @Override
    public Tipo updateTipo(Integer id, TipoDTO tipoDTO) {
        // Buscar tipo existente por su ID
        Optional<Tipo> optionalTipo = tipoRepository.findById(id);

        if (optionalTipo.isPresent()) {
            Tipo tipo = optionalTipo.get();
            // Actualizar los campos del tipo con los valores del DTO
            tipo.setNombre(tipoDTO.getNombre());
            // Guardar el tipo actualizado
            return tipoRepository.save(tipo);
        } else {
            // Manejar el caso en que no se encuentre el tipo con el ID dado
            throw new EntityNotFoundException("No se encontró un tipo con el ID proporcionado: " + id);
        }
    }

    @Override
    public void deleteTipo(Integer id) {
        Tipo tipo = tipoRepository.getReferenceById(id);
        tipoRepository.delete(tipo);

    }

}
