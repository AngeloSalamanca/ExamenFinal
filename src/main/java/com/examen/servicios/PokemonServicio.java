package com.examen.servicios;

import com.examen.entidades.Pokemon;
import com.examen.repositorios.PokemonRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServicio {

	@Autowired
    PokemonRepositorio pokemonRepositorio;

    public List<Pokemon> listarPokemonesPorTipo(int tipoPokemon) {
        return pokemonRepositorio.findByTipo_Pokemon(tipoPokemon);
    }
}
