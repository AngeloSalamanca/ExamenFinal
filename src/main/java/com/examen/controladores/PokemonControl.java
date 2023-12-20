package com.examen.controladores;

import com.examen.entidades.Pokemon;
import com.examen.repositorios.PokemonRepositorio;
import com.examen.servicios.PokemonServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemones")
public class PokemonControl {

	@Autowired
    PokemonRepositorio pokemonRepositorio;
	PokemonServicio pokemonServicio;

    @GetMapping
    public ResponseEntity<List<Pokemon>> obtenerTodosLosPokemones() {
        List<Pokemon> pokemones = pokemonRepositorio.findAll();
        return new ResponseEntity<>(pokemones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> obtenerPokemonPorId(@PathVariable("id") int id) {
        Optional<Pokemon> pokemon = pokemonRepositorio.findById(id);
        return pokemon.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/tipo/{id}")
    public ResponseEntity<List<Pokemon>> listarPokemonesPorTipo(@PathVariable("id") int tipoPokemonId) {
        List<Pokemon> pokemonesPorTipo = pokemonServicio.listarPokemonesPorTipo(tipoPokemonId);
        if (!pokemonesPorTipo.isEmpty()) {
            return new ResponseEntity<>(pokemonesPorTipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pokemon> crearPokemon(@RequestBody Pokemon pokemon) {
        Pokemon nuevoPokemon = pokemonRepositorio.save(pokemon);
        return new ResponseEntity<>(nuevoPokemon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarPokemon(
            @PathVariable("id") int id,
            @RequestBody Pokemon nuevoPokemon
    ) {
        Optional<Pokemon> pokemonExistente = pokemonRepositorio.findById(id);
        if (pokemonExistente.isPresent()) {
            nuevoPokemon.setId(id);
            Pokemon pokemonActualizado = pokemonRepositorio.save(nuevoPokemon);
            return new ResponseEntity<>(pokemonActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarPokemon(@PathVariable("id") int id) {
        try {
        	pokemonRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}