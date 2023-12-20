package com.examen.servicios;

import com.examen.entidades.Entrenador;
import com.examen.repositorios.EntrenadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntrenadorServicio {

	@Autowired
	EntrenadorRepositorio entrenadorRepositorio;

	public String obtenerUuidEntrenadorPorId(int id) {
        Entrenador entrenador = entrenadorRepositorio.findById(id).orElse(null);
        if (entrenador != null) {
            return entrenador.getUuid();
        }
        return null;
    }
}
