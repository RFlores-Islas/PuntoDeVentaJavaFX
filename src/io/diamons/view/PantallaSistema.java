package io.diamons.view;

import java.sql.Connection;
import java.util.function.Predicate;

import io.diamons.main.MainApp;
import io.diamons.modelo.Cliente;
import io.diamons.modelo.Dialogo;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Producto;
import io.diamons.modelo.Proveedor;
import io.diamons.modelo.dao.ClienteDAO;
import io.diamons.modelo.dao.ProductoDAO;
import io.diamons.modelo.dao.ProveedorDAO;
import io.github.utilities.PercentageTableColumn;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PantallaSistema {
	
	//PROPIEDADES
	private MainApp mainApp;
	private Stage stage;
	private Connection connection;
	
	//COMPONENTES
	@FXML private Button buttonClose;
	
	//TABS
	@FXML private TabPane tabPaneSistema;
	@FXML private Tab tabClientes;
	@FXML private Tab tabProveedores;
	@FXML private Tab tabProdutos;
	
	//TAB CLIENTEs
	@FXML private TextField textFieldBuscarCliente;
	@FXML private Button buttonAgregarCliente;
	@FXML private Button buttonActualizarClientes;
	
	@FXML private TableView<Cliente> tableViewClientes;
	@FXML private PercentageTableColumn<Cliente, String> tableColumnCodigoCliente;
	@FXML private PercentageTableColumn<Cliente, String> tableColumnNombreCliente;
	@FXML private PercentageTableColumn<Cliente, String> tableColumnTelefonoCliente;
	@FXML private PercentageTableColumn<Cliente, String> tableColumnDireccionCliente;
	@FXML private PercentageTableColumn<Cliente, String> tableColumnAccionesCliente;
	
	//TAB PROVEEDORES
	@FXML private TextField textFieldBuscarProveedor;
	@FXML private Button buttonAgregarProveedor;
	@FXML private Button buttonActualizarProveedores;

	@FXML private TableView<Proveedor> tableViewProveedores;
	@FXML private PercentageTableColumn<Proveedor, String> tableColumnCodigoProveedor;
	@FXML private PercentageTableColumn<Proveedor, String> tableColumnNombreProveedor;
    @FXML private PercentageTableColumn<Proveedor, String> tableColumnRfcProveedor;
    @FXML private PercentageTableColumn<Proveedor, String> tableColumnTelefonoProveedor;
    @FXML private PercentageTableColumn<Proveedor, String> tableColumnDireccionProveedor;
	@FXML private PercentageTableColumn<Proveedor, String> tableColumnAccionesProveedor;
	
	//TAB PRODUCTOS
    @FXML private TextField textFieldBuscarProducto;
    @FXML private Button buttonAgregarProducto;
    @FXML private Button buttonActualizarProductos;
    
    @FXML private TableView<Producto> tableViewProductos;
    @FXML private PercentageTableColumn<Producto, String> tableColumnCodigoProducto;
    @FXML private PercentageTableColumn<Producto, String> tableColumnDescripcionProducto;
    @FXML private PercentageTableColumn<Producto, String> tableColumnProductoProveedor;
    @FXML private PercentageTableColumn<Producto, String> tableColumnMarcaProducto;
    @FXML private PercentageTableColumn<Producto, String> tableColumnStockProducto;
    @FXML private PercentageTableColumn<Producto, String> tableColumnPrecioProducto;
    @FXML private PercentageTableColumn<Producto, String> tableColumnAccionesProducto;
	

	public void setMainApp(MainApp mainAppp) {
		this.mainApp = mainAppp;
		this.stage = (Stage) this.buttonClose.getScene().getWindow();
		this.connection = this.mainApp.getConnection();
		this.initComponentes();
		this.updateTableViewClientes();
	}//FIN METODO
	
	private void initComponentes() {
		this.buttonClose.setOnAction(action -> this.handlerButtonClose());
		this.buttonActualizarClientes.setOnAction(action -> this.handlerButtonActualizar());
		this.buttonActualizarProveedores.setOnAction(action -> this.handlerButtonActualizar());
		this.buttonActualizarProductos.setOnAction(action -> this.handlerButtonActualizar());
		
		this.buttonAgregarCliente.setOnAction(action -> this.handlerButtonAgregarCliente());		
		this.buttonAgregarProveedor.setOnAction(action -> this.handlerButtonAgregarProveedor());
		this.buttonAgregarProducto.setOnAction(action -> this.handlerButtonAgregarProducto());
		
		 this.tabPaneSistema.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
			 this.handlerButtonActualizar();
		 });//FIN SENTENCIA
		
		//TAB CLIENTES
		this.tableColumnCodigoCliente.setCellValueFactory(cellValue -> cellValue.getValue().codigoProperty());
		this.tableColumnNombreCliente.setCellValueFactory(cellValue -> cellValue.getValue().nombreProperty());
		this.tableColumnTelefonoCliente.setCellValueFactory(cellValue -> cellValue.getValue().telefonoProperty());
		this.tableColumnDireccionCliente.setCellValueFactory(cellValue -> cellValue.getValue().direccionProperty());
		this.initTableColumnAccionesCliente();
		
		//PROVEEDORES
		this.tableColumnCodigoProveedor.setCellValueFactory(cellValue -> cellValue.getValue().codigoProperty());
		this.tableColumnNombreProveedor.setCellValueFactory(cellValue -> cellValue.getValue().nombreProperty());
		this.tableColumnRfcProveedor.setCellValueFactory(cellValue -> cellValue.getValue().rfcProperty());
		this.tableColumnTelefonoProveedor.setCellValueFactory(cellValue -> cellValue.getValue().telefonoProperty());
		this.tableColumnDireccionProveedor.setCellValueFactory(cellValue -> cellValue.getValue().direccionProperty());
		this.initTableColumnAccionesProveedor();
		
		//PRODUCTOS
		this.tableColumnCodigoProducto.setCellValueFactory(cellValue -> cellValue.getValue().codigoProperty());
		this.tableColumnDescripcionProducto.setCellValueFactory(cellValue -> cellValue.getValue().descripcionProperty());
		this.tableColumnProductoProveedor.setCellValueFactory(cellValue -> cellValue.getValue().proveedorProperty().asString());
		this.tableColumnMarcaProducto.setCellValueFactory(cellValue -> cellValue.getValue().marcaProperty().asString());
		this.tableColumnStockProducto.setCellValueFactory(cellValue -> cellValue.getValue().stockProperty().asString());
		this.tableColumnPrecioProducto.setCellValueFactory(cellValue -> cellValue.getValue().precioProperty().asString());
		this.initTableColumnAccionesProductos();
		
		
	}//FIN METODO
	
	private void initTableColumnAccionesCliente() {
		 this.tableColumnAccionesCliente.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		 Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>> cellFactory = params -> {
			 final TableCell<Cliente, String> tableCell = new TableCell<Cliente, String>() {
				 final Button buttonEditar = new Button("Editar");
				 final Button buttonEliminar = new Button("Eliminar");
				 final HBox hBoxAcciones = new HBox(buttonEditar, buttonEliminar);
				 
				 @Override
				 public void updateItem(String item, boolean empty) {
					 hBoxAcciones.setAlignment(Pos.CENTER_LEFT);
					 hBoxAcciones.setSpacing(3);
					 
					 buttonEditar.setGraphic(new ImageView(new Image(MainApp.class.getResourceAsStream("../view/imagenes/icons/actualizar.png"))));
					 buttonEditar.setPrefSize(20.0, 20.0);
					 buttonEditar.setPadding(Insets.EMPTY);
					 buttonEditar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					 buttonEditar.setStyle("-fx-background-color: transparent");
					 buttonEditar.setCursor(Cursor.HAND);
					 buttonEditar.setTooltip(new Tooltip("Editar registro"));
					 buttonEditar.setOnAction(action -> handlerButtonEditarCliente(getTableView().getItems().get(getIndex())));
					 
					 buttonEliminar.setGraphic(new ImageView(new Image(MainApp.class.getResourceAsStream("../view/imagenes/icons/eliminar.png"))));
					 buttonEliminar.setPrefSize(20.0, 20.0);
					 buttonEliminar.setPadding(Insets.EMPTY);
					 buttonEliminar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					 buttonEliminar.setStyle("-fx-background-color: transparent");
					 buttonEliminar.setCursor(Cursor.HAND);
					 buttonEliminar.setTooltip(new Tooltip("Eliminar registro"));
					 buttonEliminar.setOnAction(action -> handlerButtonEliminarCliente(getTableView().getItems().get(getIndex())));
					 
					 super.updateItem(item, empty);
					 if (empty) {
						 super.setGraphic(null);
						 super.setText(null);
					 } else {						 
						 super.setGraphic(hBoxAcciones);
						 super.setText(null);
					 }//FIN IF/ELSE					 
				 }//FIN METODO
			 };//FIN SENTENCIA
			 return tableCell;
		 };//FIN SENTENCIA
		 this.tableColumnAccionesCliente.setCellFactory(cellFactory);
	}//FIN METODO
	
	private void initTableColumnAccionesProveedor() {
		 this.tableColumnAccionesProveedor.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		 Callback<TableColumn<Proveedor, String>, TableCell<Proveedor, String>> cellFactory = params -> {
			 final TableCell<Proveedor, String> tableCell = new TableCell<Proveedor, String>() {
				 final Button buttonEditar = new Button("Editar");
				 final Button buttonEliminar = new Button("Eliminar");
				 final HBox hBoxAcciones = new HBox(buttonEditar, buttonEliminar);
				 
				 @Override
				 public void updateItem(String item, boolean empty) {
					 hBoxAcciones.setAlignment(Pos.CENTER_LEFT);
					 hBoxAcciones.setSpacing(3);
					 
					 buttonEditar.setGraphic(new ImageView(new Image(MainApp.class.getResourceAsStream("../view/imagenes/icons/actualizar.png"))));
					 buttonEditar.setPrefSize(20.0, 20.0);
					 buttonEditar.setPadding(Insets.EMPTY);
					 buttonEditar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					 buttonEditar.setStyle("-fx-background-color: transparent");
					 buttonEditar.setCursor(Cursor.HAND);
					 buttonEditar.setTooltip(new Tooltip("Editar registro"));
					 buttonEditar.setOnAction(action -> handlerButtonEditarProveedor(getTableView().getItems().get(getIndex())));
					 
					 buttonEliminar.setGraphic(new ImageView(new Image(MainApp.class.getResourceAsStream("../view/imagenes/icons/eliminar.png"))));
					 buttonEliminar.setPrefSize(20.0, 20.0);
					 buttonEliminar.setPadding(Insets.EMPTY);
					 buttonEliminar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					 buttonEliminar.setStyle("-fx-background-color: transparent");
					 buttonEliminar.setCursor(Cursor.HAND);
					 buttonEliminar.setTooltip(new Tooltip("Eliminar registro"));
					 buttonEliminar.setOnAction(action -> handlerButtonEliminarProveedor(getTableView().getItems().get(getIndex())));
					 
					 super.updateItem(item, empty);
					 if (empty) {
						 super.setGraphic(null);
						 super.setText(null);
					 } else {						 
						 super.setGraphic(hBoxAcciones);
						 super.setText(null);
					 }//FIN IF/ELSE					 
				 }//FIN METODO
			 };//FIN SENTENCIA
			 return tableCell;
		 };//FIN SENTENCIA
		 this.tableColumnAccionesProveedor.setCellFactory(cellFactory);
	}//FIN METODO
	
	private void initTableColumnAccionesProductos() {
		 this.tableColumnAccionesProducto.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		 Callback<TableColumn<Producto, String>, TableCell<Producto, String>> cellFactory = params -> {
			 final TableCell<Producto, String> tableCell = new TableCell<Producto, String>() {
				 final Button buttonEditar = new Button("Editar");
				 final Button buttonEliminar = new Button("Eliminar");
				 final HBox hBoxAcciones = new HBox(buttonEditar, buttonEliminar);
				 
				 @Override
				 public void updateItem(String item, boolean empty) {
					 hBoxAcciones.setAlignment(Pos.CENTER_LEFT);
					 hBoxAcciones.setSpacing(3);
					 
					 buttonEditar.setGraphic(new ImageView(new Image(MainApp.class.getResourceAsStream("../view/imagenes/icons/actualizar.png"))));
					 buttonEditar.setPrefSize(20.0, 20.0);
					 buttonEditar.setPadding(Insets.EMPTY);
					 buttonEditar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					 buttonEditar.setStyle("-fx-background-color: transparent");
					 buttonEditar.setCursor(Cursor.HAND);
					 buttonEditar.setTooltip(new Tooltip("Editar registro"));
					 buttonEditar.setOnAction(action -> handlerButtonEditarProducto(getTableView().getItems().get(getIndex())));
					 
					 buttonEliminar.setGraphic(new ImageView(new Image(MainApp.class.getResourceAsStream("../view/imagenes/icons/eliminar.png"))));
					 buttonEliminar.setPrefSize(20.0, 20.0);
					 buttonEliminar.setPadding(Insets.EMPTY);
					 buttonEliminar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					 buttonEliminar.setStyle("-fx-background-color: transparent");
					 buttonEliminar.setCursor(Cursor.HAND);
					 buttonEliminar.setTooltip(new Tooltip("Eliminar registro"));
	//				 buttonEliminar.setOnAction(action -> handlerButtonEliminarCliente(getTableView().getItems().get(getIndex())));
					 
					 super.updateItem(item, empty);
					 if (empty) {
						 super.setGraphic(null);
						 super.setText(null);
					 } else {						 
						 super.setGraphic(hBoxAcciones);
						 super.setText(null);
					 }//FIN IF/ELSE					 
				 }//FIN METODO
			 };//FIN SENTENCIA
			 return tableCell;
		 };//FIN SENTENCIA
		 this.tableColumnAccionesProducto.setCellFactory(cellFactory);	
	}//FIN METODO
	
	private void updateTableViewClientes() {
		ObjectProperty<Predicate<Cliente>> filtroBuscar = new SimpleObjectProperty<>();
		filtroBuscar.bind(Bindings.createObjectBinding(() -> cliente -> textFieldBuscarCliente.getText().isEmpty() ||
				cliente.getCodigo().toLowerCase().contains(textFieldBuscarCliente.getText().toLowerCase()) ||
				cliente.getNombre().toLowerCase().contains(textFieldBuscarCliente.getText().toLowerCase()),
				textFieldBuscarCliente.textProperty()));
		 
		FilteredList<Cliente> filteredListClientes = new FilteredList<Cliente>(FXCollections.observableArrayList(ClienteDAO.read(this.connection)));
		SortedList<Cliente> sortedListClientes = new SortedList<Cliente>(filteredListClientes);
		sortedListClientes.comparatorProperty().bind(tableViewClientes.comparatorProperty());
		this.tableViewClientes.setItems(sortedListClientes);
		this.tableViewClientes.getSortOrder().add(this.tableColumnNombreCliente);
		filteredListClientes.predicateProperty().bind(Bindings.createObjectBinding(() -> filtroBuscar.get(), filtroBuscar));
	}//FIN METODO
	
	private void updateTableViewProveedores() {
		ObjectProperty<Predicate<Proveedor>> filtroBuscar = new SimpleObjectProperty<>();
		filtroBuscar.bind(Bindings.createObjectBinding(() -> proveedor -> textFieldBuscarProveedor.getText().isEmpty() ||
				proveedor.getCodigo().toLowerCase().contains(textFieldBuscarProveedor.getText().toLowerCase()) ||
				proveedor.getNombre().toLowerCase().contains(textFieldBuscarProveedor.getText().toLowerCase()),
				textFieldBuscarProveedor.textProperty()));
		 
		FilteredList<Proveedor> filteredListProveedores = new FilteredList<Proveedor>(FXCollections.observableArrayList(ProveedorDAO.read(this.connection)));
		SortedList<Proveedor> sortedListProvedores = new SortedList<Proveedor>(filteredListProveedores);
		sortedListProvedores.comparatorProperty().bind(tableViewProveedores.comparatorProperty());
		this.tableViewProveedores.setItems(sortedListProvedores);
		this.tableViewProveedores.getSortOrder().add(this.tableColumnNombreProveedor);
		filteredListProveedores.predicateProperty().bind(Bindings.createObjectBinding(() -> filtroBuscar.get(), filtroBuscar));
	}//FIN METODO
	
	private void updateTableViewProductos() {
		ObjectProperty<Predicate<Producto>> filtroBuscar = new SimpleObjectProperty<>();
		filtroBuscar.bind(Bindings.createObjectBinding(() -> producto -> textFieldBuscarProducto.getText().isEmpty() ||
				producto.getCodigo().toLowerCase().contains(textFieldBuscarProducto.getText().toLowerCase()) ||
				producto.getDescripcion().toLowerCase().contains(textFieldBuscarProducto.getText().toLowerCase()),
				textFieldBuscarProducto.textProperty()));
		
		FilteredList<Producto> filteredListProducto = new FilteredList<Producto>(FXCollections.observableArrayList(ProductoDAO.read(this.connection)));
		SortedList<Producto> sortedListProducto = new SortedList<Producto>(filteredListProducto);
		sortedListProducto.comparatorProperty().bind(tableViewProductos.comparatorProperty());
		this.tableViewProductos.setItems(sortedListProducto);
		this.tableViewProductos.getSortOrder().add(this.tableColumnDescripcionProducto);
		filteredListProducto.predicateProperty().bind(Bindings.createObjectBinding(() -> filtroBuscar.get(), filtroBuscar));
	}//FIN METODO
	
	private void handlerButtonAgregarCliente() {
		this.mainApp.openDialogoCliente(this.stage, new Cliente(), Dialogo.CREAR);
		this.updateTableViewClientes();
	}//FIN MANEJADOR
	
	private void handlerButtonEditarCliente(Cliente cliente) {
		this.mainApp.openDialogoCliente(this.stage, cliente, Dialogo.EDITAR);
		this.updateTableViewClientes();
	}//FIN MANEJADOR
	
	private void handlerButtonEliminarCliente(Cliente cliente) {
		if (Notificacion.aletarConfirmation("", "¿Realmente deseas borrar el registro?")) {
			ClienteDAO.delete(this.connection, cliente);
			this.updateTableViewClientes();
		}//FIN IF
	}//FIN MANEJADOR
	
	private void handlerButtonAgregarProveedor() {
		this.mainApp.openDialogoProveedor(this.stage, new Proveedor(), Dialogo.CREAR);
		this.updateTableViewProveedores();
	}//FIN MANEJADOR
	
	private void handlerButtonEditarProveedor(Proveedor proveedor) {
		this.mainApp.openDialogoProveedor(this.stage, proveedor, Dialogo.EDITAR);
		this.updateTableViewProveedores();
	}//FIN MANEJADOR
	
	private void handlerButtonEliminarProveedor(Proveedor proveedor) {
		if (Notificacion.aletarConfirmation("", "¿Realmente deseas borrar el registro?")) {
			ProveedorDAO.delete(this.connection, proveedor);
			this.updateTableViewProveedores();
		}//FIN IF
	}//FIN MANEJADOR
	
	private void handlerButtonAgregarProducto() {
		this.mainApp.openDialogoProducto(this.stage, new Producto(), Dialogo.CREAR);
		this.updateTableViewProductos();
	}//FIN MANEJADOR
	
	private void handlerButtonEditarProducto(Producto producto) {
		this.mainApp.openDialogoProducto(this.stage, producto, Dialogo.EDITAR);
		this.updateTableViewProductos();
	}//FIN METODO
	
	private void handlerButtonActualizar() {
		if (this.tabPaneSistema.getSelectionModel().getSelectedItem() == this.tabClientes)
			this.updateTableViewClientes();
		else if (this.tabPaneSistema.getSelectionModel().getSelectedItem() == this.tabProveedores)
			this.updateTableViewProveedores();
		else if (this.tabPaneSistema.getSelectionModel().getSelectedItem() == this.tabProdutos)
			this.updateTableViewProductos();
	}//FIN MANEJADOR
	
	private void handlerButtonClose() {
		this.mainApp.openDialogoInicio();
	}//FIN MANEJADOR
	
}//FIN CLASE
