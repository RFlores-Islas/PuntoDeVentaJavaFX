package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.diamons.modelo.Configuracion;

public class ConfiguracionDAO {
	
	Connection connection;
	Conexion conexion = new Conexion();
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public Configuracion shearchDatos() {
		Configuracion configuracion = new Configuracion();
		String sql = "SELECT * FROM configuracion";
		try {
			connection = conexion.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				configuracion.setId(resultSet.getInt("id"));
				configuracion.setRfc(resultSet.getString("rfc"));
				configuracion.setNombre(resultSet.getString("nombre"));
				configuracion.setTelefono(resultSet.getString("telefono"));
				configuracion.setDireccion(resultSet.getString("direccion"));
				configuracion.setMensaje(resultSet.getString("mensaje"));
		} catch (SQLException e) {
			System.out.println(e.toString());
		}//FIN TRY/CATCH
		return configuracion;
	}//FIN METODO
	
	public boolean updateDatos(Configuracion configuracion) {
		String sql = "UPDATE config SET rfc = ?, nombre = ?, telefono = ?, direccion = ?, mensaje = ? WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, configuracion.getRfc());
			preparedStatement.setString(2, configuracion.getNombre());
			preparedStatement.setString(3, configuracion.getTelefono());
			preparedStatement.setString(4, configuracion.getDireccion());
			preparedStatement.setString(4, configuracion.getMensaje());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}//FIN TRY/cATCH
		}//FIN TRY/CATCH
	}//FIN METODO
	
}//FIN CLASE
