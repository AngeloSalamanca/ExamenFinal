package com.examen.controladores;

import com.examen.entidades.Tipo_pokemon;
import com.examen.repositorios.Tipo_pokemonRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class Tipo_pokemonControl {

	@Autowired
    Tipo_pokemonRepositorio tipo_pokemonRepositorio;

    @GetMapping
    public ResponseEntity<List<Tipo_pokemon>> obtenerTodosLosTiposPokemon() {
        List<Tipo_pokemon> tiposPokemon = tipo_pokemonRepositorio.findAll();
        return new ResponseEntity<>(tiposPokemon, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo_pokemon> obtenerTipoPokemonPorId(@PathVariable("id") int id) {
        Optional<Tipo_pokemon> tipoPokemon = tipo_pokemonRepositorio.findById(id);
        return tipoPokemon.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Tipo_pokemon> crearTipoPokemon(@RequestBody Tipo_pokemon tipoPokemon) {
        Tipo_pokemon nuevoTipoPokemon = tipo_pokemonRepositorio.save(tipoPokemon);
        return new ResponseEntity<>(nuevoTipoPokemon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo_pokemon> actualizarTipoPokemon(
            @PathVariable("id") int id,
            @RequestBody Tipo_pokemon nuevoTipoPokemon
    ) {
        Optional<Tipo_pokemon> tipoPokemonExistente = tipo_pokemonRepositorio.findById(id);
        if (tipoPokemonExistente.isPresent()) {
            nuevoTipoPokemon.setId(id);
            Tipo_pokemon tipoPokemonActualizado = tipo_pokemonRepositorio.save(nuevoTipoPokemon);
            return new ResponseEntity<>(tipoPokemonActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarTipoPokemon(@PathVariable("id") int id) {
        try {
        	tipo_pokemonRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
