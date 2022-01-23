package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.diamons.modelo.Marca;
import io.diamons.modelo.Notificacion;

public class MarcaDAO {

	public static boolean create(Connection connection, Marca marca) {
		String consulta = "INSERT INTO marcas " +
				"(Sys_TimeStamp," +
				"Sys_GUID," +
				"Sys_DTCreated," +
				"Codigo," +
				"Marca)" + 
				"VALUES " +
				"({Sys_TimeStamp NOW()}," +
				"{Sys_GUID UPPER(MD5(UUID()))}," +
				"{Sys_DTCreated NOW()}," +
				"{Codigo ?}," +
				"{Marca ?})";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, marca.getCodigo());
			preparedStatement.setString(2, marca.getMarca());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public static ArrayList<Marca> read(Connection connection) {
		ArrayList<Marca> arrayListMarcas = new ArrayList<Marca>();
		String consulta = "SELECT * FROM marcas";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Marca marca = new Marca();
				marca.setSysPK(resultSet.getInt("Sys_PK"));
				marca.setCodigo(resultSet.getString("Codigo"));
				marca.setMarca(resultSet.getString("Marca"));
				arrayListMarcas.add(marca);
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return arrayListMarcas;
	}//FIN DE METODO
	
	public static int readUltimoSysPK(Connection connection) {
		int ultimoSysPK = 0;
		String consulta = "SELECT Sys_PK FROM marcas ORDER BY Sys_PK DESC LIMIT 1";
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
	
	public static Marca readPorSysPK(Connection connection, int marcaSys_PK) {
		Marca marca = new Marca();
		String consulta ="SELECT * FROM marcas WHERE Sys_PK = " + marcaSys_PK;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(consulta);
			while(resultSet.next()) {
				marca.setSysPK(resultSet.getInt("Sys_PK"));
				marca.setCodigo(resultSet.getString("Codigo"));
				marca.setMarca(resultSet.getString("Marca"));
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return marca;
	}//FIN METODO
	
	public static boolean update(Connection connection, Marca marca) {
		String consulta = "UPDATE marcas " + 
				"SET " +
				"Sys_TimeStamp = NOW()," +
				"Codigo = ?," +
				"Marca = ?" +
				"WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, marca.getCodigo());
			preparedStatement.setString(2, marca.getMarca());
			preparedStatement.setInt(3, marca.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO

	public static boolean delete(Connection connection, Marca marca) {
		String consulta = "DELETE FROM marcas WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setInt(1, marca.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO	
}//FIN CLASE
