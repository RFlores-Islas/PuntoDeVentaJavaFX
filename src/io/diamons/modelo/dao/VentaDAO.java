package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Venta;

public class VentaDAO {

	public static boolean create(Connection connection, Venta venta) {
		String consulta = "INSERT INTO ventas " +
				"(Sys_TimeStamp," +
				"Sys_GUID," +
				"Sys_DTCreated," +
				"Folio," +
				"Fecha," + 
				"ClienteFK," +
				"ProductoFK," +
				"CantidadProducto," +
				"SubTotal," +
				"Descuento," +
				"Impuesto," +
				"Total) " +
				"VALUES " +
				"({Sys_TimeStamp NOW()}," +
				"{Sys_GUID UPPER(MD5(UUID()))}," +
				"{Sys_DTCreated NOW()}," +
				"{Folio ?}," +
				"{Fecha ?}," +
				"{ClienteFK ?}," +
				"{ProductoFK ?}," +
				"{CantidadProducto ?}," +
				"{SubTotal ?}," +
				"{Descuento ?}," +
				"{Impuesto ?}," +
				"{Total ?})";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setString(1, venta.getFolio());
			preparedStatement.setDate(2, venta.getFecha());
			preparedStatement.setInt(3, venta.getCliente().getSysPK());
			preparedStatement.setInt(4, venta.getProducto().getSysPK());
			preparedStatement.setInt(5, venta.getCantidadProducto());
			preparedStatement.setDouble(6, venta.getSubTotal());
			preparedStatement.setDouble(7, venta.getDescuento());
			preparedStatement.setDouble(8, venta.getImpuesto());
			preparedStatement.setDouble(9, venta.getTotal());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public static ArrayList<Venta> read(Connection connection) {
		ArrayList<Venta> arrayListVentas = new ArrayList<Venta>();
		String consulta = "SELECT * FROM infoventas ORDER BY VentaFolio ASC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Venta venta = new Venta();
				venta.setSysPK(resultSet.getInt("VentaSysPK"));
				venta.setFolio(resultSet.getString("VentaFolio"));
				venta.setFecha(resultSet.getDate("VentaFecha"));
				
				venta.getCliente().setSysPK(resultSet.getInt("VentaClienteSys_PK"));
				venta.getCliente().setCodigo(resultSet.getString("VentaClienteCodigo"));
				venta.getCliente().setNombre(resultSet.getString("VentaClienteNombre"));
				
				venta.getProducto().setSysPK(resultSet.getInt("VentaProductoSysPK"));
				venta.getProducto().setCodigo(resultSet.getString("VentaProductoCodigo"));
				venta.getProducto().setDescripcion(resultSet.getString("VentaProductoDescripcion"));
				
				venta.setCantidadProducto(resultSet.getInt("VentaCantidadProducto"));
				venta.setSubTotal(resultSet.getDouble("VentaSubTotal"));
				venta.setDescuento(resultSet.getDouble("VentaDescuento"));
				venta.setImpuesto(resultSet.getDouble("VentaImpuesto"));
				venta.setTotal(resultSet.getDouble("VentaTotal"));
				arrayListVentas.add(venta);
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return arrayListVentas;
	}//FIN DE METODO
	
	public static int readUltimoSysPK(Connection connection) {
		int ultimoSysPK = 0;
		String consulta = "SELECT Sys_PK FROM ventas ORDER BY Sys_PK DESC LIMIT 1";
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
	
	public static Venta readPorSysPK(Connection connection, int productoSys_PK) {
		Venta venta = new Venta();
		String consulta ="SELECT * FROM ventas WHERE Sys_PK = " + productoSys_PK;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(consulta);
			while(resultSet.next()) {
				venta.setSysPK(resultSet.getInt("Sys_PK"));
				venta.setFolio(resultSet.getString("Folio"));
				venta.setFecha(resultSet.getDate("Fecha"));
				venta.getCliente().setSysPK(resultSet.getInt("ClienteFK"));
				venta.getProducto().setSysPK(resultSet.getInt("ProductoFK"));
				venta.setCantidadProducto(resultSet.getInt("CantidadProducto"));
				venta.setSubTotal(resultSet.getDouble("SubTotal"));
				venta.setDescuento(resultSet.getDouble("Descuento"));
				venta.setImpuesto(resultSet.getDouble("Impuesto"));
				venta.setTotal(resultSet.getDouble("Total"));
			}//FIN WHILE
		} catch (SQLException e) {
			Notificacion.alertException(e);
		}//FIN TRY/CATCH
		return venta;
	}//FIN METODO
	
//	public static boolean update(Connection connection, Venta venta) {
//		String consulta = "UPDATE ventas " + 
//				"SET " +
//				"Sys_TimeStamp = NOW()," +
//				"Codigo = ?," +
//				"Descripcion = ?," +
//				"ProveedorFK = ?," +
//				"MarcaFK = ?," +
//				"Stock = ?" +
//				"Precio = ?" +
//				"WHERE Sys_PK = ?";
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
//			preparedStatement.setString(1, venta.getCodigo());
//			preparedStatement.setString(2, venta.getDescripcion());
//			preparedStatement.setInt(3, venta.getProveedor().getSysPK());
//			preparedStatement.setInt(4, venta.getMarca().getSysPK());
//			preparedStatement.setInt(5, venta.getStock());
//			preparedStatement.setDouble(6, venta.getPrecio());
//			preparedStatement.setInt(7, venta.getSysPK());
//			preparedStatement.execute();
//			return true;
//		} catch (SQLException e) {
//			Notificacion.alertException(e);
//			return false;
//		}//FIN FINALLY
//	}//FIN METODO

	public static boolean delete(Connection connection, Venta venta) {
		String consulta = "DELETE FROM ventas WHERE Sys_PK = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setInt(1, venta.getSysPK());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			Notificacion.alertException(e);
			return false;
		}//FIN FINALLY
	}//FIN METODO

}//FIN CLASE
