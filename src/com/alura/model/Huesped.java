package com.alura.model;

import java.sql.Date;

public class Huesped {
	
	Integer id;
	String nombre;
	String apellido;
	String din;
	Date fecha_de_nacimiento;
	String nacionalidad;
	String telefono;
	Integer id_reserva;
	
	public Huesped() {}
	
	public Huesped(Integer id, String nombre,
			String apellido, Date fecha_nacimiento,
			String nacionalidad, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		
		
	}
	
	
	
	
	public Integer getId() {
		return id;
	}

	public Integer getId_reserva() {
		return id_reserva;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDin() {
		return din;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
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
