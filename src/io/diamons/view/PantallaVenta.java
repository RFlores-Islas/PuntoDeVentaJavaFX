package io.diamons.view;

import java.sql.Connection;
import java.util.ArrayList;

import io.diamons.main.MainApp;
import io.diamons.modelo.Cliente;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Producto;
import io.diamons.modelo.Venta;
import io.diamons.modelo.dao.ClienteDAO;
import io.diamons.modelo.dao.ProductoDAO;
import io.github.utilities.PercentageTableColumn;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class PantallaVenta {
	
	//PROPIEDADES
	private MainApp mainApp;
	private Stage stage;
	private Connection connection;
	private Producto producto;
	private ArrayList<Venta> arrayListVentas;
	
	//COMPONENTES
	@FXML private Label labelTicket;
	@FXML private TextField textFieldBuscarProducto;
	@FXML private TextField textFieldProducto;
    @FXML private TextField textFieldCantidad;
    @FXML private TextField textFiedlPrecio;
    @FXML private Button buttonImprimirRecibo;
    @FXML private Button buttonGraficaVenta;
    @FXML private ComboBox<Cliente> comboBoxCliente;
    @FXML private Button buttoAgregarCliente;
    @FXML private Button buttonEditarCliente;
	@FXML private Button buttonCobrar;
    @FXML private Button buttonCancelar;
    @FXML private Label labelSubTotal;
    @FXML private Label labelDescuento;
    @FXML private Label labelImpuesto;
    @FXML private Label labelTotal;
    @FXML private Label labelPago;
    @FXML private Label labelResta;
    //TABLE VIEW
    @FXML private TableView<Venta> tableViewVentas;
    @FXML private PercentageTableColumn<Venta, String> tableColumnCodigoProducto;
    @FXML private PercentageTableColumn<Venta, String> tableColumnProducto;
    @FXML private PercentageTableColumn<Venta, String> tableColumnCantidadProducto;
    @FXML private PercentageTableColumn<Venta, String> tableColumnPrecioUnidad;
    @FXML private PercentageTableColumn<Venta, String> tableColumnSubtotal;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.producto = new Producto();
		this.stage = (Stage) this.buttonCancelar.getScene().getWindow();
		this.connection = this.mainApp.getConnection();
		this.arrayListVentas = new ArrayList<Venta>();
		this.initComponentes();
	}//FIN METODO
	
	private void initComponentes() {
		this.handlerTextFieldBuscarProducto();
		this.handlerTextFieldCantidad();
		
		tableColumnCodigoProducto.setCellValueFactory(cellValue -> cellValue.getValue().getProducto().codigoProperty());
		tableColumnProducto.setCellValueFactory(cellValue -> cellValue.getValue().getProducto().descripcionProperty());
		tableColumnCantidadProducto.setCellValueFactory(cellValue -> cellValue.getValue().cantidadProductoProperty().asString());
		tableColumnPrecioUnidad.setCellValueFactory(cellValue -> cellValue.getValue().getProducto().precioProperty().asString());
		tableColumnSubtotal.setCellValueFactory(cellValue -> cellValue.getValue().subTotalProperty().asString());
		
		this.initComboBoxCliente();
		this.buttonEditarCliente.setDisable(true);
	}//FIN METODO
	
	private void initComboBoxCliente() {
		this.comboBoxCliente.setItems(FXCollections.observableArrayList(ClienteDAO.read(this.connection)));
		this.comboBoxCliente.setConverter(new StringConverter<Cliente>() {

			@Override
			public String toString(Cliente object) {
				return object == null ? "" : object.toString();
			}//FIN METODO

			@Override
			public Cliente fromString(String string) {
				for (Cliente cliente : comboBoxCliente.getItems())
					if (cliente.toString().equals(string))
						return cliente;
				return null;
			}//FIN METODO
		});//FIN SENTENCIA
		
		this.comboBoxCliente.valueProperty().addListener((value, oldValue, newValue) -> {
			if (newValue == null)
				this.buttonEditarCliente.setDisable(true);
			else 
				this.buttonEditarCliente.setDisable(false);
		});
		
	}//FIN METODO
	
	private void handlerTextFieldCantidad() {
		this.textFieldBuscarProducto.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					if (!textFieldBuscarProducto.getText().isEmpty()) {
						producto = ProductoDAO.readPorCodigo(connection, textFieldBuscarProducto.getText());
						if (producto.getSysPK() != 0) {
							textFieldProducto.setText(producto.getDescripcion());
							textFiedlPrecio.setText(String.valueOf(producto.getPrecio()));
						} else {
							Notificacion.alertaInfo(AlertType.INFORMATION, "", "El registro al que intentas acceder no existe, ¡revisa que la información sea correcta!");
						}//FIN ELSE
					} else {
						Notificacion.alertaInfo(AlertType.WARNING, "", "¡Por Favor verificarla que la Información ingresada sea correcta!");
					}
				}//FIN IF
			}//FIN METODO
		});
	}//FIN METODO
	
	private void handlerTextFieldBuscarProducto() {		
		this.textFieldCantidad.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					updateTableView();
				}
			}//FIN METODO
			
		});
	}//FIN METODO
	
	private void updateTableView() {

		Venta venta = new Venta();
		venta.getProducto().setCodigo(this.producto.getCodigo());
		venta.getProducto().setDescripcion(producto.getDescripcion());
		venta.setCantidadProducto(Integer.valueOf(textFieldCantidad.getText()));
		venta.getProducto().setPrecio(producto.getPrecio());
		venta.setSubTotal(10.0);
		
		this.arrayListVentas.add(venta);
		
		this.tableViewVentas.setItems(FXCollections.observableArrayList(this.arrayListVentas));;
	}//FIN METODO
	
	private void resetVenta() {
		
	}//FIN METODO


}//FIN CLASE
