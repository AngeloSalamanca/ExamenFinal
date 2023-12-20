package com.examen.entidades;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_pokemon")
public class Tipo_pokemon {
	
	@Id
	@SequenceGenerator(name="pokemon_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_id_seq")
	
	private int id;
	private String descripcion;
	private String uuid;
}
