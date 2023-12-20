package com.examen.controladores;

import com.examen.entidades.Pueblo;
import com.examen.repositorios.PuebloRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class PuebloControl {

	@Autowired
    PuebloRepositorio puebloRepositorio;

    @GetMapping
    public ResponseEntity<List<Pueblo>> obtenerTodosLosPueblos() {
        List<Pueblo> pueblos = puebloRepositorio.findAll();
        return new ResponseEntity<>(pueblos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pueblo> obtenerPuebloPorId(@PathVariable("id") int id) {
        Optional<Pueblo> pueblo = puebloRepositorio.findById(id);
        return pueblo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Pueblo> crearPueblo(@RequestBody Pueblo pueblo) {
        Pueblo nuevoPueblo = puebloRepositorio.save(pueblo);
        return new ResponseEntity<>(nuevoPueblo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pueblo> actualizarPueblo(
            @PathVariable("id") int id,
            @RequestBody Pueblo nuevoPueblo
    ) {
        Optional<Pueblo> puebloExistente = puebloRepositorio.findById(id);
        if (puebloExistente.isPresent()) {
            nuevoPueblo.setId(id);
            Pueblo puebloActualizado = puebloRepositorio.save(nuevoPueblo);
            return new ResponseEntity<>(puebloActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarPueblo(@PathVariable("id") int id) {
        try {
        	puebloRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
