package com.alura.model;

import java.sql.Date;

public class Huesped {
	
	Integer id;
	String nombre;
	String apellido;
	String dni;
	Date fecha_de_nacimiento;
	String nacionalidad;
	String telefono;
	Integer id_reserva;
	
	public Huesped() {}
	
	
//	public Huesped(Integer id_reserva, Integer id, String dni, String nombre,
//			String apellido, Date fecha_nacimiento,
//			String nacionalidad, String telefono) {
//		this.id_reserva = id_reserva;
//		this.id = id;
//		this.dni = dni;
//		this.nombre = nombre;
//		this.apellido = apellido;
//		this.fecha_de_nacimiento = fecha_nacimiento;
//		this.nacionalidad = nacionalidad;
//		this.telefono = telefono;
//		
//		
//	}
	
	
	
	
	public Huesped(String nombre, String apellido, String dni,
			Date fecha_de_nacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId_reserva(Integer id_reserva) {
		this.id_reserva = id_reserva;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
				"reserva: %d,"
				+ " id: %d,"
				+ " nombre: %s,"
				+ " dni: %s,"
				+ " fecha de nacimiento: %s,"
				+ " nacionalidad: %s,"
				+ " telefono: %s",
				this.id_reserva,
				this.id,
				this.nombre,
				this.dni,
				this.fecha_de_nacimiento,
				this.nacionalidad,
				this.telefono
				);
	}
}//FIN HUESPED
