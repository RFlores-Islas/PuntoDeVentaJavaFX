package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.diamons.modelo.Cliente;
import io.diamons.modelo.Notificacion;

public class ClienteDAO {
	
	public static boolean create(Connection connection, Cliente cliente) {
		String consulta = "INSERT INTO clientes " +
				"(Sys_TimeStamp," +
				"Sys_GUID," +
				"Sys_DTCreated," +
				"Codigo," +
				"Nombre," + 
				"Telefono," +
				"Direccion) " +
				"VALUES " +
				"({Sys_TimeStamp NOW()}," +
				"{Sys_GUID UPPER(MD5(UUID()))}," +
				"{Sys_DTCreated NOW()}," +
				"{Codigo ?}," +
				"{Nombre ?}," +
				"{Telefono ?}," +
				"{Direccion ?})";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, cliente.getCodigo());
			preparedStatement.setString(2, cliente.getNombre());
			preparedStatement.setString(3, cliente.getTelefono());
			preparedStatement.setString(4, cliente.getDireccion());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public static ArrayList<Cliente> read(Connection connection) {
		ArrayList<Cliente> arrayListClientes = new ArrayList<Cliente>();
		String consulta = "SELECT * FROM clientes";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setSysPK(resultSet.getInt("Sys_PK"));
				cliente.setCodigo(resultSet.getString("Codigo"));
				cliente.setNombre(resultSet.getString("Nombre"));
				cliente.setTelefono(resultSet.getString("Telefono"));
				cliente.setDireccion(resultSet.getString("Direccion"));
				arrayListClientes.add(cliente);
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return arrayListClientes;
	}//FIN DE METODO
	
	public static int readUltimoSysPK(Connection connection) {
		int ultimoSysPK = 0;
		String consulta = "SELECT Sys_PK FROM clientes ORDER BY Sys_PK DESC LIMIT 1";
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
	
	public static Cliente readPorSysPK(Connection connection, int clienteSys_PK) {
		Cliente cliente = new Cliente();
		String consulta ="SELECT * FROM clientes WHERE Sys_PK = " + clienteSys_PK;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(consulta);
			while(resultSet.next()) {
				cliente.setSysPK(resultSet.getInt("Sys_PK"));
				cliente.setCodigo(resultSet.getString("Codigo"));
				cliente.setNombre(resultSet.getString("Nombre"));
				cliente.setTelefono(resultSet.getString("Telefono"));
				cliente.setDireccion(resultSet.getString("Direccion"));
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return cliente;
	}//FIN METODO
	
	public static boolean update(Connection connection, Cliente cliente) {
		String consulta = "UPDATE clientes " + 
				"SET " +
				"Sys_TimeStamp = NOW()," +
				"Codigo = ?," +
				"Nombre = ?," +
				"Telefono = ?," +
				"Direccion = ?" +
				"WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, cliente.getCodigo());
			preparedStatement.setString(2, cliente.getNombre());
			preparedStatement.setString(3, cliente.getTelefono());
			preparedStatement.setString(4, cliente.getDireccion());
			preparedStatement.setInt(5, cliente.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO

	public static boolean delete(Connection connection, Cliente cliente) {
		String consulta = "DELETE FROM clientes WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setInt(1, cliente.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO	
}//FIN CLASE
