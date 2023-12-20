package com.examen.entidades;

import java.sql.Date;

import lombok.Data;

@Data
public class Entrenador {
	
	private int id;
	private String nombre;
	private String apellido;
	private Date fecha_Nacimineto;
	private Date fecha_Vinculacion;
	private int pueblo_id;
	private String uuid;
}
