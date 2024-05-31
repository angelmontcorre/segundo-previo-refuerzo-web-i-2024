package com.co.ufps.previo.model.DTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MangaDTO {

    private Integer id;
    private String nombre;
    private LocalDate fechaLanz;
    private Integer temporadas;
    private PaisDTO pais;
    private Integer anime;
    private Integer juego;
    private Integer pelicula;
    private TipoDTO tipo;
    
}
