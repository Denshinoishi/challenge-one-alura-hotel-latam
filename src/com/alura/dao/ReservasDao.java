package com.alura.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.model.Huesped;
import com.alura.model.Reservas;



public class ReservasDao {
	
	final private Connection con;
	
	public ReservasDao(Connection con) {
		this.con = con;
		
	}
	
	public List<Reservas> listar(){
		List<Reservas> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID,"
					+ "FECHA_INGRESO,"
					+ "FECHA_SALIDA,"
					+ "VALOR,"
					+ "FORMA_PAGO FROM RESERVAS");
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					while(resultSet.next()) {
						Date fechaIngreso = resultSet.getDate("FECHA_INGRESO");
						Date fechaSalida = resultSet.getDate("FECHA_SALIDA");
						Reservas reserva = new Reservas(
								resultSet.getInt("ID"),
								fechaIngreso, 
								fechaSalida, 
								resultSet.getBigDecimal("VALOR"), 
								resultSet.getString("FORMA_PAGO"));
						
						
						
						resultado.add(reserva);
						
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
		
	}
	
	public int consultarProximoId() {
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT AUTO_INCREMENT "
					+ "FROM information_schema.TABLES "
					+ "WHERE TABLE_SCHEMA = 'hotel_alura' "
					+ "AND TABLE_NAME = 'reservas'");
			try (statement){
				final ResultSet resultSet  = statement.executeQuery();
				
				try(resultSet){
					
					if(resultSet.next()) {
						return resultSet.getInt(1);						
					}else {
						throw new RuntimeException("No se encontro el valor de AUTO_INCREMENT");
					}
					
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public int modificar(Date fecha_ingreso, Date fecha_salida, BigDecimal cuota, String forma_pago, Integer id) {
		 try {
		        final PreparedStatement statement = con.prepareStatement(
		                "UPDATE RESERVAS SET "
		                + " FECHA_INGRESO = ?, "
		                + " FECHA_SALIDA = ?,"
		                + " VALOR = ?,"
		                + " FORMA_PAGO = ?"
		                + " WHERE ID = ?");

		        try (statement) {
		        	
		        	
		            statement.setDate(1, fecha_ingreso);
		            statement.setDate(2, fecha_salida);
		            statement.setBigDecimal(3, cuota);
		            statement.setString(4, forma_pago);
		            statement.setInt(5, id);
		            statement.execute();

		            int updateCount = statement.getUpdateCount();

		            return updateCount;
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    }
	}

	public int eliminar(Integer id) {
		try {
	        final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");

	        try (statement) {
	            statement.setInt(1, id);
	            statement.execute();

	            int updateCount = statement.getUpdateCount();

	            return updateCount;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	
	public void guardar(Reservas reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVAS "
					+ "(fecha_ingreso, fecha_salida, valor, forma_pago)"
					+ " VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutaRegistro(reserva, statement);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}
	
	public void ejecutaRegistro(Reservas reserva, PreparedStatement statement) throws SQLException {
		statement.setDate(1, reserva.getFecha_de_ingreso());
		statement.setDate(2, reserva.getFecha_de_salida());
		statement.setBigDecimal(3, reserva.getValorTotal());
		statement.setString(4, reserva.getForma_de_pago());
		statement.execute()	;
		final ResultSet resultSet = statement.getGeneratedKeys();
		try(resultSet){
			while(resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(String.format("Fues insertado el producto %s", reserva));
			}
		}
	}
	
	
	public Integer recuperarUltimoIdReserva() {
		Integer lastid = null;
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT MAX(ID) FROM RESERVAS");
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet){
					if(resultSet.next()) {
						lastid = resultSet.getInt(1);
					}
					
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return lastid;
	}



}//FIN HUESPEDESDAO
