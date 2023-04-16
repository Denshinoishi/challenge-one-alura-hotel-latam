package com.alura.pruebas;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.alura.connectionfactory.ConnectionFactory;
import com.alura.dao.HuespedesDao;
import com.alura.dao.ReservasDao;
import com.alura.model.Huesped;
import com.alura.model.Reservas;

public class PruebasDeConexion {
	
	
		public static void main(String[] args) {
			Date fecha1 = Date.valueOf("2023-1-20");
			Date fecha2 = Date.valueOf("2023-11-20");
			Reservas reserva = new Reservas( fecha1, fecha2, 100000, "con tapas");
			
			System.out.println(reserva);
			ConnectionFactory con = new ConnectionFactory();
			ReservasDao reservasDao = new ReservasDao(con.recuperarConexion());
			HuespedesDao huespedesDao = new HuespedesDao(con.recuperarConexion());
			
			
			
			Huesped pn = new Huesped("Nombre", "Apellido", "DNI", fecha2, "Pelotense", "1234567890");
			huespedesDao.guardar(reserva, pn);
			
			
			System.out.println(pn);

			
	}
	

}
