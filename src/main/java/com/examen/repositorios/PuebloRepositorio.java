package com.examen.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.entidades.Pueblo;

public interface PuebloRepositorio extends JpaRepository<Pueblo, Integer> {
}