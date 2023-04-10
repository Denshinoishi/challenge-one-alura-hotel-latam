package com.alura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.model.Huesped;



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
	
	

}//FIN HUESPEDESDAO
