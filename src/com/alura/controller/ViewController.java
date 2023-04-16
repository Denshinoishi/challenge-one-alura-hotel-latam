package com.alura.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.alura.connectionfactory.ConnectionFactory;
import com.alura.dao.HuespedesDao;
import com.alura.dao.ReservasDao;
import com.alura.model.Huesped;
import com.alura.model.Reservas;

public class ViewController {
	
	
	ConnectionFactory con = new ConnectionFactory();
	ReservasDao reservasDao = new ReservasDao(con.recuperarConexion());
	HuespedesDao huespedesDao = new HuespedesDao(con.recuperarConexion());
	

	public Reservas crearReserva(Date fecha_ingreso, Date fecha_salida, String formaPago) {
		LocalDate in = fecha_ingreso.toLocalDate();
		LocalDate out = fecha_salida.toLocalDate();
		long dias;
		
		Integer id = recuperarIdReserva() + 1;
		
		
		if(in.equals(out)) {
			dias = 1;
		} else if(out.isAfter(in)) {
			dias = ChronoUnit.DAYS.between(in, out);
		} else {
			throw new IllegalArgumentException("");
		}
		
		return new Reservas(id, fecha_ingreso, fecha_salida, formaPago);
		
	}
	
	public BigDecimal calcularReserva(Date fecha_de_ingreso, Date fecha_de_salida) {
	    LocalDate in = fecha_de_ingreso.toLocalDate();
	    LocalDate out = fecha_de_salida.toLocalDate();
	    long dias;
	    BigDecimal valorTotal;
	   
	    if (in.isEqual(out)) {
	        dias = 1;
	    } else if (out.isAfter(in)) {
	        dias = ChronoUnit.DAYS.between(in, out);
	    } else {
	        throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de ingreso.");
	    }
	    
	    return  valorTotal = BigDecimal.valueOf(dias).multiply(Reservas.TASA);
	    
	}

	public Huesped crearHuesped(String nombre, String apellido, String dni, String nacionalidad, Date nacimiento, String telefono) {
		return new Huesped(nombre, apellido, dni, nacionalidad, nacimiento, telefono);
		
	}
	
	public void registrar(Reservas reserva, Huesped huesped) {
		huespedesDao.guardar(reserva, huesped);
	}
	
	public Integer recuperarIdReserva() {
		return reservasDao.recuperarUltimoIdReserva();
	}


}
