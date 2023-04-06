package com.alura.model;

import java.sql.Date;

public class Huesped {
	
	Integer id;
	String nombre;
	String apellido;
	Date fecha_de_nacimiento;
	String nacionalidad;
	String telefono;
	Integer id_reserva;
	
	public Huesped(Integer id, String nombre,
			String apellido, Date fecha_nacimiento,
			String nacionalidad, String telefono) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		
		
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, nombre: %s, apellido: %s, fecha de nacimiento: %s,"
				+ "nacionalidad: %s, telefono: %s ",
				this.id,
				this.nombre,
				this.apellido,
				this.fecha_de_nacimiento,
				this.nacionalidad,
				this.telefono
				);
	}
}//FIN HUESPED
