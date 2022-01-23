package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Proveedor;


public class ProveedorDAO {
	
	public static boolean create(Connection connection, Proveedor proveedor) {
		String consulta = "INSERT INTO proveedores " +
			"(Sys_TimeStamp," +
			"Sys_GUID," +
			"Sys_DTCreated," +
			"Codigo," +
			"Nombre," + 
			"Rfc," +
			"Telefono," +
			"Direccion) " +
			"VALUES " +
			"({Sys_TimeStamp NOW()}," +
			"{Sys_GUID UPPER(MD5(UUID()))}," +
			"{Sys_DTCreated NOW()}," +
			"{Codigo ?}," +
			"{Nombre ?}," +
			"{Rfc ?}," +
			"{Telefono ?}," +
			"{Direccion ?})";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, proveedor.getCodigo());
			preparedStatement.setString(2, proveedor.getNombre());
			preparedStatement.setString(3, proveedor.getRfc());
			preparedStatement.setString(4, proveedor.getTelefono());
			preparedStatement.setString(5, proveedor.getDireccion());
			preparedStatement.execute();
			return true;
		} catch (SQLException ex) {
			Notificacion.alertException(ex);
			return false;
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public static ArrayList<Proveedor> read(Connection connection) {
		ArrayList<Proveedor> arrayListProveedores = new ArrayList<Proveedor>();
		String consulta = "SELECT * FROM proveedores";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Proveedor proveedor = new Proveedor();
				proveedor.setSysPK(resultSet.getInt("Sys_PK"));
				proveedor.setCodigo(resultSet.getString("Codigo"));
				proveedor.setNombre(resultSet.getString("Nombre"));
				proveedor.setRfc(resultSet.getString("Rfc"));
				proveedor.setTelefono(resultSet.getString("Telefono"));
				proveedor.setDireccion(resultSet.getString("Direccion"));
				arrayListProveedores.add(proveedor);
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return arrayListProveedores;
	}//FIN DE METODO
	
	public static int readUltimoSysPK(Connection connection) {
		int ultimoSysPK = 0;
		String consulta = "SELECT Sys_PK FROM proveedores ORDER BY Sys_PK DESC LIMIT 1";
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(consulta);
			while (resultSet.next())
				ultimoSysPK = resultSet.getInt("Sys_PK");
			return ultimoSysPK;
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY /CATCH
		return ultimoSysPK;
	}//FIN METODO
	
	public static Proveedor readPorSysPK(Connection connection, int proveedorSys_PK) {
		Proveedor proveedor = new Proveedor();
		String consulta ="SELECT * FROM proveedores WHERE Sys_PK = " + proveedorSys_PK;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(consulta);
			while(resultSet.next()) {
				proveedor.setSysPK(resultSet.getInt("Sys_PK"));
				proveedor.setCodigo(resultSet.getString("Codigo"));
				proveedor.setNombre(resultSet.getString("Nombre"));
				proveedor.setRfc(resultSet.getString("Rfc"));
				proveedor.setTelefono(resultSet.getString("Telefono"));
				proveedor.setDireccion(resultSet.getString("Direccion"));
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return proveedor;
	}//FIN METODO
	
	public static boolean update(Connection connection, Proveedor proveedor) {
		String consulta = "UPDATE proveedores " + 
				"SET " +
				"Sys_TimeStamp = NOW()," +
				"Codigo = ?," +
				"Nombre = ?," +
				"Rfc = ?," +
				"Telefono = ?," +
				"Direccion = ?" +
				"WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, proveedor.getCodigo());
			preparedStatement.setString(2, proveedor.getNombre());
			preparedStatement.setString(3, proveedor.getRfc());
			preparedStatement.setString(4, proveedor.getTelefono());
			preparedStatement.setString(5, proveedor.getDireccion());
			preparedStatement.setInt(6, proveedor.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO
	
	public static boolean delete(Connection connection, Proveedor proveedor) {
		String consulta = "DELETE FROM proveedores WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setInt(1, proveedor.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO
}//FIN CLASE
