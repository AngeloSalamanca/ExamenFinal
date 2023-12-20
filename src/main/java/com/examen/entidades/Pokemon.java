package com.examen.entidades;

import java.sql.Date;

import lombok.Data;

@Data
public class Pokemon {

	private int id;
	private String nombre;
	private String descripcion;
	private int tipo_Pokemon;
	private Date fecha_Descubrimineto;
	private int generacion;
	private String uuid;
}
