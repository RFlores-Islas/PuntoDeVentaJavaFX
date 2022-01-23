package io.diamons.view;

import java.sql.Connection;
import java.text.DecimalFormat;

import io.diamons.main.MainApp;
import io.diamons.modelo.Dialogo;
import io.diamons.modelo.Marca;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.dao.MarcaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoMarca {
	
	//PROPIEDADES
	private MainApp mainApp;
	private Connection connection;
	private Stage stage;
	private Marca marca;
	private Dialogo opcionDialogo;
	
	//COMPONENTES
	@FXML private TextField textFieldMarca;
	@FXML private Button buttonAceptar;
	@FXML private Button buttonCancelar;
	
	public void setMainApp(MainApp mainApp, Marca marca, Dialogo opcionDialogo) {
		this.mainApp = mainApp;
		this.connection = this.mainApp.getConnection();
		this.stage = (Stage) this.buttonCancelar.getScene().getWindow();
		this.marca = marca;
		this.opcionDialogo = opcionDialogo;
		
		this.initComponentes();
		this.loadComponentes();
	}//FIN METODO
	
	private void initComponentes() {
		this.buttonAceptar.setOnAction(action -> this.handlerButtonAceptar());
		this.buttonCancelar.setOnAction(action -> this.handlerButtonCancelar());
	}//FIN METODO
	
	private void loadComponentes() {
		this.textFieldMarca.setText(this.marca.getMarca());
	}//FIN METODO
	
	private boolean checkComponentes() {
		if (this.textFieldMarca.getText().isEmpty()) {
			Notificacion.alertaInfo(AlertType.WARNING, "", "El campo Marca no puede estar vacío, ¡revisa que la información sea correcta!");
			return false;
		}//FIN IF
		return true;
	}//FIN METODO

	private boolean saveMarca() {
		if (!checkComponentes()) return false;
		
		if (this.marca.getSysPK() == 0) {
			DecimalFormat decimalFormat = new DecimalFormat("00000");
			this.marca.setCodigo("MRK-" + decimalFormat.format(Double.valueOf(MarcaDAO.readUltimoSysPK(this.connection) + 1)));
		} else {
			this.marca.setCodigo(this.marca.getCodigo());
		}
		
		this.marca.setMarca(this.textFieldMarca.getText());
		
		if (this.opcionDialogo == Dialogo.CREAR) {
			if (MarcaDAO.create(this.connection, this.marca)) {
				return true;
			} else {
				return false;
			}//FIN ELSE/IF
		} else if (this.opcionDialogo == Dialogo.EDITAR) {
			if (MarcaDAO.update(this.connection, this.marca)) {
				return true;
			} else {
				return false;
			}//FIN ELSE/IF
		}//FIN ELSE/IF
		return false;
	}//FIN METODO
	
	private void handlerButtonAceptar() {
		if (this.saveMarca()) {
			Notificacion.alertaInfo(AlertType.INFORMATION, "", "El registro se guardo de forma correcta");
			this.stage.close();
		}//FIN IF
	}//FIN MANEJADOR
	
	private void handlerButtonCancelar() {
		this.stage.close();
	}//FIN MANEJADOR
}//FIN CLASE
