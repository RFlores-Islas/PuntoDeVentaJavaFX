package io.diamons.view;

import java.sql.Connection;
import java.text.DecimalFormat;

import io.diamons.main.MainApp;
import io.diamons.modelo.Cliente;
import io.diamons.modelo.Dialogo;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.dao.ClienteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoCliente {
	//PROPIEDADES
	private MainApp mainApp;
	private Stage stage;
	private Connection connection;
	private Cliente cliente;
	private Dialogo opcionDialogo;
	//COMPONENTES
    @FXML private TextField textFieldCodigo;
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldTelefono;
    @FXML private TextField textFieldDireccion;
    @FXML private Button buttonAceptar;
    @FXML private Button buttonCancelar;
	
	public void setMainApp(MainApp mainApp, Cliente cliente, Dialogo opcionDialogo) {
		this.mainApp = mainApp;
		this.stage = (Stage) this.buttonCancelar.getScene().getWindow();
		this.connection = this.mainApp.getConnection();
		this.cliente = cliente;
		this.opcionDialogo = opcionDialogo;
		this.initComponentes();
		this.loadComponentes();
	}//FIN METODO
	
	private void initComponentes() {
		this.buttonAceptar.setOnAction(action -> this.handlerButtonAceptar());
		this.buttonCancelar.setOnAction(action -> this.handlerButtonCancelar());
		this.textFieldCodigo.setDisable(true);
	}//FIN METODO
	
	private void loadComponentes() {
		this.textFieldCodigo.setText(this.cliente.getCodigo());
		this.textFieldNombre.setText(this.cliente.getNombre());
		this.textFieldTelefono.setText(this.cliente.getTelefono());
		this.textFieldDireccion.setText(this.cliente.getDireccion());
	}//FIN METODO
	
	private boolean checkComponentes() {
		if (this.textFieldNombre.getText().isEmpty()) {	
			Notificacion.alertaInfo(AlertType.WARNING, "", "El campo Nombre no puede estar vacío, ¡revisa que la información sea correcta!");
			return false;
		}//FIN IF
		return true;
	}//FIN METODO
	
	private boolean saveCliente() {
		if (!checkComponentes()) return false;
	
		if (this.cliente.getSysPK() == 0) {
			DecimalFormat decimalFormat = new DecimalFormat("00000");
			this.cliente.setCodigo("CL-" + decimalFormat.format(Double.valueOf(ClienteDAO.readUltimoSysPK(this.connection) + 1)));
		} else {
			this.cliente.setCodigo(this.textFieldCodigo.getText());
		}//FIN ELSE
		
		this.cliente.setNombre(this.textFieldNombre.getText());
		this.cliente.setTelefono(this.textFieldTelefono.getText());
		this.cliente.setDireccion(this.textFieldDireccion.getText());
		
		if (this.opcionDialogo == Dialogo.CREAR) {
			if (ClienteDAO.create(this.connection, this.cliente)) {
				return true;
			} else {
				return false;
			}//FIN ELSE/IF
		} else if (this.opcionDialogo == Dialogo.EDITAR) {
			if (ClienteDAO.update(this.connection, this.cliente)) {
				return true;
			} else {
				return false;
			}//FIN ELSE/IF
		}//FIN ELSE/IF
		return false;
	}//FIN METOD
	
	private void handlerButtonAceptar() {
		if (this.saveCliente()) {
			Notificacion.alertaInfo(AlertType.INFORMATION, "", "El registro se guardo de forma correcta");
			this.stage.close();
		}//FIN IF
	}//FIN MANEJADOR
	
	private void handlerButtonCancelar() {
		this.stage.close();
	}//FIN MANEJADOR

}//FIN CLASE
