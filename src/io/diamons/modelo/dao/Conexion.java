package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.diamons.modelo.Notificacion;

public class Conexion {
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/venta", "root", "");
			return  connection;
		} catch (ClassNotFoundException e) {
			Notificacion.alertException(e);
		} catch (SQLException e) {
			Notificacion.alertException(e);
		} catch (Exception e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return null;
	}//FIN METODO
}//FIN CLASE
