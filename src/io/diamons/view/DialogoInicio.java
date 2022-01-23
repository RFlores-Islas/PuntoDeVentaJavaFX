package io.diamons.view;

import io.diamons.main.MainApp;
import javafx.fxml.FXML;

import javafx.scene.layout.VBox;

public class DialogoInicio {
	
	//PROPIEDADES
	private MainApp mainApp;
	
	//COMPONENTES
	@FXML private VBox vBoxSistema;
	@FXML private VBox vBoxPuntoVenta;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.initComponentes();
	}//FIN METODO
	
	private void initComponentes() {
		this.vBoxSistema.setOnMouseClicked(action -> this.handlerSistema());
		this.vBoxPuntoVenta.setOnMouseClicked(action -> this.handlerPuntoVenta());
	}//FIN METODO

	private void handlerPuntoVenta() {
		this.mainApp.getEscenarioUno().close();
		this.mainApp.initEscenarioDos();
		this.mainApp.openPantallaVenta();
	}//FIN METODO
	
	private void handlerSistema() {
		this.mainApp.getEscenarioUno().close();
		this.mainApp.initEscenarioDos();
		this.mainApp.openPantallaSistema();
	}//FIN METODO
}//FIN CLASE
