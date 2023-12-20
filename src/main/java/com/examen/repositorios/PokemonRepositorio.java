package com.examen.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.entidades.Pokemon;

public interface PokemonRepositorio extends JpaRepository<Pokemon, Integer> {
	List<Pokemon> findByTipo_Pokemon(int tipoPokemonId);
}
