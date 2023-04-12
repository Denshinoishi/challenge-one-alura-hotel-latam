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



public class HuespedesDao {
	
	final private Connection con;
	
	public HuespedesDao(Connection con) {
		this.con = con;
		
	}
	
	public List<Huesped> listar(){
		List<Huesped> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID,"
					+ "IDRESERVA,"
					+ "DNI,"
					+ "NOMBRE,"
					+ "APELLIDO,"
					+ "FECHA_NACIMIENTO,"
					+ "NACIONALIDAD,"
					+ "TELEFONO FROM HUESPEDES");
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					while(resultSet.next()) {
						Date fechaNacimiento = resultSet.getDate("FECHA_NACIMIENTO");
						Huesped huesped = new Huesped(resultSet.getInt("ID"),
								resultSet.getInt("IDRESERVA"),
								resultSet.getString("DNI"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								fechaNacimiento,
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO"));
						resultado.add(huesped);
						
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
		
	}
	
	public int modificar(Integer idreserva, String nombre, String apellido, String dni, Date fecha_nacimiento, String nacionalidad, String telefono, Integer id) {
		 try {
		        final PreparedStatement statement = con.prepareStatement(
		                "UPDATE HUESPEDES SET "
		                + " NOMBRE = ?,"
		                + " APELLIDO = ?,"
		                + " DNI = ?,"
		                + "	FECHA_NACIMIENTO = ?,"
		                + " NACIONALIDAD = ?,"
		                + " TELEFONO = ?"
		                + " WHERE ID = ?");

		        try (statement) {
		        	
		        	
		            
		            statement.setString(1, nombre);
		            statement.setString(2, apellido);
		            statement.setString(3, dni);
		            statement.setDate(4, fecha_nacimiento);
		            statement.setString(5, nacionalidad);
		            statement.setString(6, telefono);
		            statement.setInt(7, id);
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
	        final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");

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


	public void guardar(Reservas reserva, Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO XXXXXXXX "
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
	
	private void ejecutaRegistro(Reservas reserva, PreparedStatement statement) throws SQLException {
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

}//FIN HUESPEDESDAO
