package com.co.ufps.previo.model.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "manga")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fechaLanz", nullable = false)
    private LocalDate fechaLanz;

    @Column(name = "temporadas", nullable = false)
    private Integer temporadas;

    @ManyToOne
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    private Pais pais;

    @Column(name = "anime")
    private Integer anime;

    @Column(name = "juego", nullable = false)
    private Integer juego;

    @Column(name = "pelicula", nullable = false)
    private Integer pelicula;

    @ManyToOne
    @JoinColumn(name = "tipo_id", referencedColumnName = "id")
    private Tipo tipo;

}
