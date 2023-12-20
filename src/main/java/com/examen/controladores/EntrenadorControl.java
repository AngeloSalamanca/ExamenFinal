package com.examen.controladores;

import com.examen.dto.EntrenadorDTO;
import com.examen.entidades.Entrenador;
import com.examen.repositorios.EntrenadorRepositorio;
import com.examen.servicios.EntrenadorServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class EntrenadorControl {

	@Autowired
    EntrenadorRepositorio entrenadorRepositorio;
	EntrenadorServicio entrenadorServicio;

    @GetMapping
    public ResponseEntity<List<Entrenador>> obtenerTodosLosEntrenadores() {
        List<Entrenador> entrenadores = entrenadorRepositorio.findAll();
        return new ResponseEntity<>(entrenadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerEntrenadorPorId(@PathVariable("id") int id) {
        Optional<Entrenador> entrenador = entrenadorRepositorio.findById(id);
        return entrenador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Entrenador> crearEntrenador(@RequestBody Entrenador entrenador) {
        Entrenador nuevoEntrenador = entrenadorRepositorio.save(entrenador);
        return new ResponseEntity<>(nuevoEntrenador, HttpStatus.CREATED);
    }
    
    @PostMapping("/uuid")
    public ResponseEntity<String> obtenerUuidEntrenador(@RequestBody EntrenadorDTO request) {
        Optional<Entrenador> entrenadorOptional = entrenadorRepositorio
                .findByNombreAndApellido(request.getNombre(), request.getApellido());

        return entrenadorOptional.map(entrenador -> new ResponseEntity<>(entrenador.getUuid(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Entrenador no encontrado", HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(
            @PathVariable("id") int id,
            @RequestBody Entrenador nuevoEntrenador
    ) {
        Optional<Entrenador> entrenadorExistente = entrenadorRepositorio.findById(id);
        if (entrenadorExistente.isPresent()) {
            nuevoEntrenador.setId(id);
            Entrenador entrenadorActualizado = entrenadorRepositorio.save(nuevoEntrenador);
            return new ResponseEntity<>(entrenadorActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarEntrenador(@PathVariable("id") int id) {
        try {
        	entrenadorRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
