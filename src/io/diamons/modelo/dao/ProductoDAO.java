package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Producto;

public class ProductoDAO {
	public static boolean create(Connection connection, Producto producto) {
		String consulta = "INSERT INTO productos " +
				"(Sys_TimeStamp," +
				"Sys_GUID," +
				"Sys_DTCreated," +
				"Codigo," +
				"Descripcion," + 
				"ProveedorFK," +
				"MarcaFK," +
				"Stock," +
				"Precio) " +
				"VALUES " +
				"({Sys_TimeStamp NOW()}," +
				"{Sys_GUID UPPER(MD5(UUID()))}," +
				"{Sys_DTCreated NOW()}," +
				"{Codigo ?}," +
				"{Descripcion ?}," +
				"{ProveedorFK ?}," +
				"{MarcaFK ?}," +
				"{Stock ?}," +
				"{Precio ?})";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, producto.getCodigo());
			preparedStatement.setString(2, producto.getDescripcion());
			preparedStatement.setInt(3, producto.getProveedor().getSysPK());
			preparedStatement.setInt(4, producto.getMarca().getSysPK());
			preparedStatement.setInt(5, producto.getStock());
			preparedStatement.setDouble(6, producto.getPrecio());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public static ArrayList<Producto> read(Connection connection) {
		ArrayList<Producto> arrayListProductos = new ArrayList<Producto>();
		String consulta = "SELECT * FROM infoproductos ORDER BY ProductoDescripcion ASC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Producto producto = new Producto();
				producto.setSysPK(resultSet.getInt("ProductoSysPK"));
				producto.setCodigo(resultSet.getString("ProductoCodigo"));
				producto.setDescripcion(resultSet.getString("ProductoDescripcion"));

				producto.getProveedor().setSysPK(resultSet.getInt("ProductoProveedorSysPK"));
				producto.getProveedor().setCodigo(resultSet.getString("ProductoProveedorCodigo"));
				producto.getProveedor().setNombre(resultSet.getString("ProductoProveedorNombre"));
				
				producto.getMarca().setSysPK(resultSet.getInt("ProductoMarcaSysPK"));
				producto.getMarca().setCodigo(resultSet.getString("ProductoMarcaCodigo"));
				producto.getMarca().setMarca(resultSet.getString("ProductoMarcaMarca"));
				
				producto.setStock(resultSet.getInt("ProductoStock"));
				producto.setPrecio(resultSet.getDouble("ProductoPrecio"));
				arrayListProductos.add(producto);
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return arrayListProductos;
	}//FIN DE METODO
	
//	public static ArrayList<Producto> read(Connection connection) {
//		ArrayList<Producto> arrayListProductos = new ArrayList<Producto>();
//		String consulta = "SELECT * FROM productos";
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				Producto producto = new Producto();
//				producto.setSysPK(resultSet.getInt("Sys_PK"));
//				producto.setCodigo(resultSet.getString("Codigo"));
//				producto.setDescripcion(resultSet.getString("Descripcion"));
//				producto.getProveedor().setSysPK(resultSet.getInt("Proveedor"));
//				producto.getMarca().setSysPK(resultSet.getInt("Marca"));
//				producto.setStock(resultSet.getInt("Stock"));
//				producto.setPrecio(resultSet.getDouble("Precio"));
//				arrayListProductos.add(producto);
//			}//FIN WHILE
//		} catch (SQLException e) {
//			Notificacion.alertException(e);
//		}//FIN TRY/CATCH
//		return arrayListProductos;
//	}//FIN DE METODO
	
	public static int readUltimoSysPK(Connection connection) {
		int ultimoSysPK = 0;
		String consulta = "SELECT Sys_PK FROM productos ORDER BY Sys_PK DESC LIMIT 1";
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
	
	public static Producto readPorCodigo(Connection connection, String codigo) {
		Producto producto = new Producto();
		String consulta = "SELECT * FROM productos WHERE Codigo = '" + codigo + "'";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(consulta);
			while(resultSet.next()) {
				producto.setSysPK(resultSet.getInt("Sys_PK"));
				producto.setCodigo(resultSet.getString("Codigo"));
				producto.setDescripcion(resultSet.getString("Descripcion"));
				producto.getProveedor().setSysPK(resultSet.getInt("ProveedorFK"));
				producto.getMarca().setSysPK(resultSet.getInt("MarcaFK"));
				producto.setStock(resultSet.getInt("Stock"));
				producto.setPrecio(resultSet.getDouble("Precio"));
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return producto;
	}//FIN METODO
	
	public static Producto readPorSysPK(Connection connection, int productoSys_PK) {
		Producto producto = new Producto();
		String consulta ="SELECT * FROM productos WHERE Sys_PK = " + productoSys_PK;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(consulta);
			while(resultSet.next()) {
				producto.setSysPK(resultSet.getInt("Sys_PK"));
				producto.setCodigo(resultSet.getString("Codigo"));
				producto.setDescripcion(resultSet.getString("Descripcion"));
				producto.getProveedor().setSysPK(resultSet.getInt("ProveedorFK"));
				producto.getMarca().setSysPK(resultSet.getInt("MarcaFK"));
				producto.setStock(resultSet.getInt("Stock"));
				producto.setPrecio(resultSet.getDouble("Precio"));
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return producto;
	}//FIN METODO
	
	public static boolean update(Connection connection, Producto producto) {
		String consulta = "UPDATE productos " + 
				"SET " +
				"Sys_TimeStamp = NOW()," +
				"Codigo = ?," +
				"Descripcion = ?," +
				"ProveedorFK = ?," +
				"MarcaFK = ?," +
				"Stock = ?," +
				"Precio = ?" +
				"WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, producto.getCodigo());
			preparedStatement.setString(2, producto.getDescripcion());
			preparedStatement.setInt(3, producto.getProveedor().getSysPK());
			preparedStatement.setInt(4, producto.getMarca().getSysPK());
			preparedStatement.setInt(5, producto.getStock());
			preparedStatement.setDouble(6, producto.getPrecio());
			preparedStatement.setInt(7, producto.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO

	public static boolean delete(Connection connection, Producto producto) {
		String consulta = "DELETE FROM productos WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setInt(1, producto.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO
}//FIN CLASE
