package com.examen.entidades;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pokemon")
public class Pokemon {

	private int id;
	private String nombre;
	private String descripcion;
	private int tipo_Pokemon;
	private Date fecha_Descubrimineto;
	private int generacion;
	private String uuid;
	
	@ManyToMany(mappedBy = "pokemones")
    private Set<Entrenador> entrenadores = new HashSet<>();
}
