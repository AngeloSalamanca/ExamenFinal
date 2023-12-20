package com.examen.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class PokemonDTO {
    private String nombre;
    private String descripcion;
    private int tipoPokemon;
    private Date fechaDescubrimiento;
    private int generacion;
}