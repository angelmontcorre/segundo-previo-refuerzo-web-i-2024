package com.co.ufps.previo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.co.ufps.previo.model.DTO.PaisDTO;
import com.co.ufps.previo.model.entity.Pais;
import com.co.ufps.previo.repository.PaisRepository;
import com.co.ufps.previo.service.PaisService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaisServiceImpl implements PaisService {

    private final PaisRepository paisRepository;

    @Override
    public List<Pais> getAllPaises() {
        List<Pais> paises = paisRepository.findAll();
        return paises;
    }

    @Override
    public Pais getPaisById(Integer id) {
        Pais pais = paisRepository.getReferenceById(id);
        return pais;
    }

    @Override
    public Pais savePais(PaisDTO paisDTO) {
        Pais pais = new Pais();
        pais.setNombre(paisDTO.getNombre());
        return paisRepository.save(pais);
    }

    @Override
    public Pais updatePais(Integer id, PaisDTO paisDTO) {
        // Buscar pais existente por su ID
        Optional<Pais> optionalPais = paisRepository.findById(id);

        if (optionalPais.isPresent()) {
            Pais pais = optionalPais.get();
            // Actualizar los campos del pais con los valores del DTO
            pais.setNombre(paisDTO.getNombre());
            // Guardar el pais actualizado
            return paisRepository.save(pais);
        } else {
            // Manejar el caso en que no se encuentre el pais con el ID dado
            throw new EntityNotFoundException("No se encontró un pais con el ID proporcionado: " + id);
        }
    }

    @Override
    public void deletePais(Integer id) {
        Pais pais = paisRepository.getReferenceById(id);
        paisRepository.delete(pais);

    }

}
