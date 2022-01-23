package io.diamons.view;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import io.diamons.main.MainApp;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Usuario;
import io.diamons.modelo.dao.UsuarioDAO;

import javafx.scene.control.PasswordField;

public class DialogoSesion {
	
	//PROPIEDADES
	private MainApp mainApp;
	private Stage stage;
	private Usuario usuario;
	
	//COMPONENTES
	@FXML private TextField textFieldUsuario;
	@FXML private PasswordField textFieldPassword;
	@FXML private Button buttonIngresar;
	@FXML private Button buttonClose;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.stage = (Stage) this.buttonClose.getScene().getWindow();
		this.usuario = new Usuario();
		this.initComponentes();
	}//FIN METODO
	
	public void initComponentes() {
		this.buttonIngresar.setOnAction(action -> this.handlerButtonIngresar());
		this.buttonClose.setOnAction(action -> this.handlerButtonClose());
		
	}//FIN METODO
	
	public boolean checkComponentes() {		
		if (this.textFieldUsuario.getText().isEmpty()) {
			Notificacion.alertaInfo(AlertType.WARNING, "", "¡Favor de ingresar un usuario valido!");
			return false;
		} else if (this.textFieldPassword.getText().isEmpty()) {
			Notificacion.alertaInfo(AlertType.WARNING, "", "¡Favor de ingresar una contraseña valida!");
			return false;
		}//FIN ELSE/IF
		return true;
	}//FIN METODO
	
	public boolean validateUsuario() {
		if (!checkComponentes()) return false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		this.usuario = usuarioDAO.read(this.textFieldUsuario.getText(), this.textFieldPassword.getText());
		
		if (this.usuario.getNombre() != null && this.usuario.getPassword() != null) {
			return true;
		} else {
			Notificacion.alertaInfo(AlertType.WARNING, "", "¡Registro invalido, Favor de verificar!");
			return false;
		}//FIN ELSE/IF
		
	}//FIN METODO
	
	private void handlerButtonIngresar() {
		if (validateUsuario()) {
			this.mainApp.setUsuario(this.usuario);
			this.mainApp.openDialogoInicio();
		}//FIN IF
	}//FIN MANEJADOR
	
	private void handlerButtonClose() {
		this.stage.close();
	}//FIN MANEJADOR
}//FIN CLASE
