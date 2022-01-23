package io.diamons.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import io.diamons.modelo.Usuario;

public class UsuarioDAO {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static Conexion conexion = new Conexion();
	
	public final Usuario read(String nombre, String password) {
		Usuario login = new Usuario();
		String sql = "SELECT * FROM usuarios WHERE nombre = ? AND pass = ?";
		try {
			connection = conexion.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				login.setId(resultSet.getInt("id"));
				login.setNombre(resultSet.getString("nombre"));
				login.setCorreo(resultSet.getString("correo"));
				login.setPassword(resultSet.getString("pass"));
				login.setRol(resultSet.getString("rol"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return null;
		}//FIN TRY/CATCH
		return login;
	}//FIN METODO
	
	public boolean create(Usuario registro) {
		String sql = "INSERT INTO usuarios (nombre, correo, password, rol) VALUES (?, ?, ?, ?)";

		try {
			connection = conexion.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, registro.getNombre());
			preparedStatement.setString(2, registro.getCorreo());
			preparedStatement.setString(3, registro.getPassword());
			preparedStatement.setString(4, registro.getRol());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}//FIN TRY/CATCH
	}//FIN METODO

	public List<Usuario> list() {
		List<Usuario> listLogin = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuarios";
		try {
			connection = conexion.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Usuario login = new Usuario();
				login.setId(resultSet.getInt("id"));
				login.setNombre(resultSet.getString("nombre"));
				login.setCorreo(resultSet.getString("correo"));
				login.setRol(resultSet.getString("rol"));
				listLogin.add(login);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}//FIN TRY/cATCH
		return listLogin;
	}//FIN METODO
	
}//FIN CLASE
