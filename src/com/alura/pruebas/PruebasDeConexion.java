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
		
			ConnectionFactory con = new ConnectionFactory();
			ReservasDao reservasDao = new ReservasDao(con.recuperarConexion());
			HuespedesDao huespedesDao = new HuespedesDao(con.recuperarConexion());
			
			System.out.println(huespedesDao.recuperarIdReserva(277));
			
			
			

			
	}
	

}
