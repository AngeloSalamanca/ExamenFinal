package com.examen.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.entidades.Tipo_pokemon;

public interface Tipo_pokemonRepositorio extends JpaRepository<Tipo_pokemon, Integer> {
}
