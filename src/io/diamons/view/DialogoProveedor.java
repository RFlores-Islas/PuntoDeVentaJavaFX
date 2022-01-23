package io.diamons.view;

import java.sql.Connection;
import java.text.DecimalFormat;

import io.diamons.main.MainApp;
import io.diamons.modelo.Dialogo;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Proveedor;
import io.diamons.modelo.dao.ProveedorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoProveedor {
	
	//PROPIEDADES
	private MainApp mainApp;
	private Stage stage;
	private Connection connection;
	private Proveedor proveedor;
	private Dialogo opcionDialogo;
	//COMPONENTES
	@FXML private TextField textFieldCodigo;
	@FXML private TextField textFieldNombre;
	@FXML private TextField textFieldRfc;
	@FXML private TextField textFieldTelefono;
	@FXML private TextField textFieldDireccion;
	@FXML private Button buttonAceptar;
	@FXML private Button buttonCancelar;
	
	public void setMainApp(MainApp mainApp, Proveedor proveedor, Dialogo opcionDialogo) {
		this.mainApp = mainApp;
		this.stage = (Stage) this.buttonCancelar.getScene().getWindow();
		this.connection = this.mainApp.getConnection();
		this.proveedor = proveedor;
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
		this.textFieldCodigo.setText(this.proveedor.getCodigo());
		this.textFieldNombre.setText(this.proveedor.getNombre());
		this.textFieldRfc.setText(this.proveedor.getRfc());
		this.textFieldTelefono.setText(this.proveedor.getTelefono());
		this.textFieldDireccion.setText(this.proveedor.getDireccion());
	}//FIN METODO
	
	private boolean checkComponentes() {
		if (this.textFieldNombre.getText().isEmpty()) {	
			Notificacion.alertaInfo(AlertType.WARNING, "", "El campo Nombre no puede estar vacío, ¡revisa que la información sea correcta!");
			return false;
		}//FIN IF
		return true;
	}//FIN METODO
	
	private boolean saveProveedor() {
		if (!checkComponentes())
			return false;
		
		if (this.proveedor.getSysPK() == 0) {
			DecimalFormat decimalFormat = new DecimalFormat("00000");
			this.proveedor.setCodigo("PR-" + decimalFormat.format(Double.valueOf(ProveedorDAO.readUltimoSysPK(this.connection) + 1)));
		} else {
			this.proveedor.setCodigo(this.textFieldCodigo.getText());
		}//FIN ELSE
		
		this.proveedor.setNombre(this.textFieldNombre.getText());
		this.proveedor.setRfc(this.textFieldRfc.getText());
		this.proveedor.setTelefono(this.textFieldTelefono.getText());
		this.proveedor.setDireccion(this.textFieldDireccion.getText());
		
		if (this.opcionDialogo == Dialogo.CREAR) {
			if (ProveedorDAO.create(this.connection, this.proveedor)) {
				return true;
			} else {
				return false;
			}//FIN ELSE
		} else if (this.opcionDialogo == Dialogo.EDITAR) {
			if (ProveedorDAO.update(this.connection, this.proveedor)) {
				return true;
			} else {
				return false;
			}//FIN ELSE
		}//FIN ELSE/IF
		return false;
	}//FIN METODO

	private void handlerButtonAceptar() {
		if (this.saveProveedor()) {
			Notificacion.alertaInfo(AlertType.INFORMATION, "", "El registro se guardo de forma correcta");
			this.stage.close();
		}//FIN IF
	}//FIN METODO
	
	private void handlerButtonCancelar() {
		this.stage.close();
	}//FIN METODO
}//FIN CLASE
