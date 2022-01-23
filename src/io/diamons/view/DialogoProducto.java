package io.diamons.view;

import java.sql.Connection;
import java.text.DecimalFormat;

import io.diamons.main.MainApp;
import io.diamons.modelo.Dialogo;
import io.diamons.modelo.Marca;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Producto;
import io.diamons.modelo.Proveedor;
import io.diamons.modelo.dao.MarcaDAO;
import io.diamons.modelo.dao.ProductoDAO;
import io.diamons.modelo.dao.ProveedorDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DialogoProducto {
	
	//PROPIEDADES
	private MainApp mainApp;
	private Stage stage;
	private Dialogo opcionDialogo;
	private Connection connection;
	private Producto producto;
	
	//COMPONENTES
	@FXML private TextField textFieldCodigo;
	@FXML private TextField textFieldDescripcion;
	@FXML private ComboBox<Proveedor> comboBoxProveedor;
	@FXML private Button buttonAgregarProveedor;
	@FXML private Button buttonEditarProveedor;
	@FXML private ComboBox<Marca> comboBoxMarca;
	@FXML private Button buttonAgregarMarca;
	@FXML private Button buttonEditarMarca;
	@FXML private TextField textFieldStock;
	@FXML private TextField textFieldPrecio;
	@FXML private Button buttonAceptar;
	@FXML private Button buttonCancelar;
	
	public void setMainApp(MainApp mainApp, Producto producto, Dialogo opcionDialogo) {
		this.mainApp = mainApp;
		this.connection = this.mainApp.getConnection();
		this.stage = (Stage) this.buttonCancelar.getScene().getWindow();
		this.producto = producto;
		this.opcionDialogo = opcionDialogo;
		
		this.initComponentes();
		this.loadComponentes();
	}//FIN METODO
	
	private void initComponentes() {
		this.textFieldCodigo.setDisable(true);
		
		this.buttonEditarProveedor.setDisable(true);
		this.initComboBoxProveedor();
		this.buttonAgregarProveedor.setOnAction(action -> this.handlerButtonAgregarProveedor());
		this.buttonEditarProveedor.setOnAction(action -> this.handlerButtonEditarProveedor());
		
		this.buttonEditarMarca.setDisable(true);
		this.initComboBoxMarca();
		this.buttonAgregarMarca.setOnAction(action -> this.handlerButtonAgregarMarca());
		this.buttonEditarMarca.setOnAction(action -> this.handlerButtonEditarMarca());
		
		this.buttonAceptar.setOnAction(action -> this.handlerButtonAceptar());
		this.buttonCancelar.setOnAction(action -> this.handlerButtonCancelar());
	}//FIN METODO
	
	private void initComboBoxProveedor() {
		this.comboBoxProveedor.setItems(FXCollections.observableArrayList(ProveedorDAO.read(this.connection)));
		this.comboBoxProveedor.setConverter(new StringConverter<Proveedor>() {

			@Override
			public String toString(Proveedor object) {
				return object == null ? "" : object.toString();
			}//FIN METODO

			@Override
			public Proveedor fromString(String string) {
				for (Proveedor proveedor : comboBoxProveedor.getItems())
					if (proveedor.toString().equals(string))
						return proveedor;
				return null;
			}//FIN METODO
		});//FIN SENTENCIA
		
		this.comboBoxProveedor.valueProperty().addListener((value, oldValue, newValue) -> {
			if (newValue == null || this.producto.getProveedor() == newValue) 
				this.buttonEditarProveedor.setDisable(true);
			else 
				this.buttonEditarProveedor.setDisable(false);
		});//FIN SENTENCIA
		//new AutoCompleteComboBoxListener<Proveedor>(this.comboBoxProveedor);
	}//FIN METODO
	
	private void initComboBoxMarca() {
		this.comboBoxMarca.setItems(FXCollections.observableArrayList(MarcaDAO.read(this.connection)));
		this.comboBoxMarca.setConverter(new StringConverter<Marca>() {

			@Override
			public String toString(Marca object) {
				return object == null ? "" : object.toString();
			}//FIN METODO

			@Override
			public Marca fromString(String string) {
				for (Marca marca : comboBoxMarca.getItems())
					if (marca.toString().equals(string))
						return marca;
				return null;
			}//FIN METODO
		});//FIN SENTENCIA
		
		this.comboBoxMarca.valueProperty().addListener((value, oldValue, newValue) -> {
			if (newValue == null || this.producto.getMarca() == newValue)
				this.buttonEditarMarca.setDisable(true);
			else
				this.buttonEditarMarca.setDisable(false);
		});//FIN SENTENCIA
		//new AutoCompleteComboBoxListener<Proveedor>(this.comboBoxProveedor);
	}//FIN METODO
	
	private void loadComponentes() {
		this.textFieldCodigo.setText(this.producto.getCodigo());
		this.textFieldDescripcion.setText(this.producto.getDescripcion());
		this.comboBoxProveedor.setValue(this.producto.getProveedor());
		this.comboBoxMarca.setValue(this.producto.getMarca());
		this.textFieldStock.setText(String.valueOf(this.producto.getStock()));
		this.textFieldPrecio.setText(String.valueOf(this.producto.getPrecio()));
		System.out.println("Producto: " + this.producto.toString());
	}//FIN METODO
	
	private boolean checkComponentes() {
		if (this.textFieldDescripcion.getText().isEmpty()) {
			Notificacion.alertaInfo(AlertType.WARNING, "", "El campo Descripción no puede estar vacío, ¡revisa que la información sea correcta!");
			return false;
		} else if (this.comboBoxProveedor.getSelectionModel().getSelectedItem().toString().isEmpty()) {
			Notificacion.alertaInfo(AlertType.WARNING, "", "El campo Proveedor no puede estar vacío, ¡revisa que la información sea correcta!");
			return false;
		} else if (this.comboBoxMarca.getSelectionModel().getSelectedItem().toString().isEmpty()) {
			Notificacion.alertaInfo(AlertType.WARNING, "", "El campo Marca no puede estar vacío, ¡revisa que la información sea correcta!");
			return false;
		} else if (this.textFieldStock.getText().isEmpty()) {
			Notificacion.alertaInfo(AlertType.WARNING, "", "El campo Stock no puede estar vacío, ¡revisa que la información sea correcta!");
			return false;
		} else if (this.textFieldPrecio.getText().isEmpty()) {
			Notificacion.alertaInfo(AlertType.WARNING, "", "El campo Precio no puede estar vacío, ¡revisa que la información sea correcta!");
			return false;
		}//FIN IF
		return true;
	}//FIN METODO
	
	private boolean saveProducto() {
		if (!checkComponentes()) return false;
		
		if (this.producto.getSysPK() == 0) {
			DecimalFormat decimalFormat = new DecimalFormat("00000");
			this.producto.setCodigo("PRO-" + decimalFormat.format(Double.valueOf(ProductoDAO.readUltimoSysPK(this.connection) + 1)));
		} else {
			this.producto.setCodigo(this.textFieldCodigo.getText());
		} 
		
		this.producto.setDescripcion(this.textFieldDescripcion.getText());
		this.producto.setProveedor(this.comboBoxProveedor.getValue());
		this.producto.setMarca(this.comboBoxMarca.getValue());
		this.producto.setStock(Integer.valueOf(this.textFieldStock.getText()));
		this.producto.setPrecio(Double.valueOf(this.textFieldPrecio.getText()));
		
		if (this.opcionDialogo == Dialogo.CREAR) {
			if (ProductoDAO.create(this.connection, this.producto)) {
				return true;
			} else {
				return false;
			}//FIN ELSE/IF
		} else if (this.opcionDialogo == Dialogo.EDITAR) {
			if (ProductoDAO.update(this.connection, this.producto)) {
				return true;
			} else {
				return false;
			}//FIN ELSE/IF
		}//FIN ELSE /IF
		return false;
	}//FIN METODO
	
	private void handlerButtonAceptar() {
		if (this.saveProducto()) {
			Notificacion.alertaInfo(AlertType.INFORMATION, "", "El registro se guardo de forma correcta");
			this.stage.close();
		}//FIN IF
	}//FIN MANEJADOR
	
	private void handlerButtonCancelar() {
		this.stage.close();
	}//FIN MANEJADOR
	
	private void handlerButtonAgregarProveedor() {
		this.mainApp.openDialogoProveedor(this.stage, new Proveedor(), Dialogo.CREAR);
		this.initComboBoxProveedor();
	}//FIN MANEJADOR
	
	private void handlerButtonEditarProveedor() {
		this.mainApp.openDialogoProveedor(this.stage, this.comboBoxProveedor.getValue(), Dialogo.EDITAR);
		this.initComboBoxProveedor();
	}//FIN MANEJADOR
	
	private void handlerButtonAgregarMarca() {
		this.mainApp.openDialogoMarca(this.stage, new Marca(), Dialogo.CREAR);
		this.initComboBoxMarca();
	}//FIN METODO
	
	private void handlerButtonEditarMarca() {
		this.mainApp.openDialogoMarca(this.stage, this.comboBoxMarca.getValue(), Dialogo.EDITAR);
		this.initComboBoxMarca();
	}//FIN MANEJADOR

}//FIN CLASE
