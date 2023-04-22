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

import javax.management.RuntimeErrorException;

import com.alura.model.Huesped;
import com.alura.model.Reservas;

public class HuespedesDao {

	final private Connection con;

	public HuespedesDao(Connection con) {
		this.con = con;

	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID," + "IDRESERVA," + "DNI," + "NOMBRE,"
					+ "APELLIDO," + "FECHA_NACIMIENTO," + "NACIONALIDAD," + "TELEFONO FROM HUESPEDES");
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
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
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;

	}


	
	public int modificar(Huesped huesped, Integer id) {
		
		
		try {
			final PreparedStatement statement = con
					.prepareStatement("UPDATE HUESPEDES SET "
							+ " NOMBRE = ?,"
							+ " APELLIDO = ?,"
							+ " DNI = ?,"
							+ "	FECHA_NACIMIENTO = ?,"
							+ " NACIONALIDAD = ?,"
							+ " TELEFONO = ?"
							+ " WHERE ID = ?");

			try (statement) {

				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getDni());
				statement.setDate(4, huesped.getFecha_de_nacimiento());
				statement.setString(5, huesped.getNacionalidad());
				statement.setString(6, huesped.getTelefono());
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

	public int eliminarIdReserva(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE IDRESERVA = ?");

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
	
	public int recuperarIdReserva(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT IDRESERVA FROM HUESPEDES WHERE ID = ?");
			try(statement){
				statement.setInt(1, id);
				ResultSet resultSet = statement.executeQuery();
				try(resultSet){
					
					
					if(resultSet.next()) {
						return resultSet.getInt(1);						
					}else {
						throw new RuntimeException("No se encontro el valor de IDRESERVA");
					}
					
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public void guardar(Reservas reserva, Huesped huesped) {
		
		
		try {
			con.setAutoCommit(false);
			
			ReservasDao reservaDao = new ReservasDao(con);
			reservaDao.guardar(reserva);
			
		
			
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPEDES "
					+ "(IDRESERVA, " 		
					+ "NOMBRE, "
					+ "APELLIDO, "
					+ "DNI, "
					+ "FECHA_NACIMIENTO, "
					+ "NACIONALIDAD, "
					+ "TELEFONO) " 
					+ "VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(reserva, huesped, reserva.getId(), statement );
			}
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			throw new RuntimeException(e);
		}

	}

	private void ejecutaRegistro(Reservas reserva, Huesped huesped, Integer reservaId, PreparedStatement statement) throws SQLException {
		
		
		try(statement){
		statement.setInt(1, reserva.getId());
		statement.setString(2, huesped.getNombre());
		statement.setString(3, huesped.getApellido() );
		statement.setString(4, huesped.getDni());
		statement.setDate(5, huesped.getFecha_de_nacimiento());
		statement.setString(6, huesped.getNacionalidad());
		statement.setString(7, huesped.getTelefono());
		
		statement.execute();
		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(String.format("Fues insertado el huesped %s", huesped));
			}
		}
	}
		con.commit();
		con.setAutoCommit(true);
		
	}

}// FIN HUESPEDESDAO
