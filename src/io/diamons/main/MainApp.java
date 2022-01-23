package io.diamons.main;

import java.io.IOException;
import java.sql.Connection;

import io.diamons.modelo.Cliente;
import io.diamons.modelo.Dialogo;
import io.diamons.modelo.Marca;
import io.diamons.modelo.Notificacion;
import io.diamons.modelo.Producto;
import io.diamons.modelo.Proveedor;
import io.diamons.modelo.Usuario;
import io.diamons.modelo.dao.Conexion;
import io.diamons.view.DialogoCliente;
import io.diamons.view.DialogoInicio;
import io.diamons.view.DialogoMarca;
import io.diamons.view.DialogoProducto;
import io.diamons.view.DialogoProveedor;
import io.diamons.view.DialogoSesion;
import io.diamons.view.PantallaSistema;
import io.diamons.view.PantallaVenta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class MainApp extends Application{
	
	//PROPIEDADES
	private Connection connection;
	private Usuario usuario;
	
	//ESCENARIOS DEL SISTEMA
	private Stage escenarioUno;
	private Stage escenarioDos;
	private Stage escenarioTres;
	
	//PANTALLAS DEL SISTEMA
	private BorderPane pantallaUno;
	private BorderPane pantallaDos;
	
	//PANTALLAS DE REGISTRO
	private AnchorPane pantallaVenta;
	private AnchorPane pantallaSistema;
	
	//DIALOGOS DEL SISTEMA
	private AnchorPane dialogoSesion;
	private AnchorPane dialogoInicio;
	private AnchorPane dialogoCliente;
	private AnchorPane dialogoProveedor;
	private AnchorPane dialogoProducto;
	private AnchorPane dialogoMarca;

	@Override
	public void start(Stage primaryStage) {
		this.configuracionEscenarioUno(primaryStage);
	    this.initEscenarioUno();
	    this.configuracionEscenarioDos(new Stage());
	    this.openDialogoSesion();
	    this.configuracionConexion();
	}//FIN METODO
	
	public void configuracionConexion() {
		Conexion conexion = new Conexion();
		this.connection = conexion.getConnection();
	}//FIN METODO
	
	public Connection getConnection() {
		return this.connection;
	}//FIN METODO
		
	private void configuracionEscenarioUno(Stage escenarioUno) {		
		this.escenarioUno = escenarioUno;
		this.escenarioUno.initStyle(StageStyle.UNDECORATED);
		this.escenarioUno.centerOnScreen();
	}//FIN METODO
	
	public Stage getEscenarioUno() {
		return this.escenarioUno;
	}//FIN METODO
	
	public void initEscenarioUno() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/PantallaBase.fxml"));
			this.pantallaUno = (BorderPane) fxmlLoader.load();
			
			Scene escenaUno = new Scene(this.pantallaUno);
			escenaUno.setFill(Color.WHITE);
			this.escenarioUno.setScene(escenaUno);
			this.escenarioUno.show();
		
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
	
	private void configuracionEscenarioDos(Stage escenarioDos) {
		this.escenarioDos = escenarioDos;
		this.escenarioDos.initStyle(StageStyle.DECORATED);
		this.escenarioDos.setMaximized(true);
	}//FIN METODO
	
	public Stage getEscenarioDos() {
		return this.escenarioDos;
	}//FIN METODO
	
	public void initEscenarioDos() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/PantallaBaseSecundaria.fxml"));
			this.pantallaDos = (BorderPane) fxmlLoader.load();
			
			Scene escenaDos = new Scene(this.pantallaDos);
			escenaDos.setFill(Color.WHITE);
			this.escenarioDos.setScene(escenaDos);
			this.escenarioDos.show();
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);	
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public void configuracionEscenarioTres(Window window) {
		this.escenarioTres = new Stage();
		this.escenarioTres.setResizable(false);
		this.escenarioTres.setMaximized(false);
		this.escenarioTres.initModality(Modality.WINDOW_MODAL);
		this.escenarioTres.initStyle(StageStyle.TRANSPARENT);
		this.escenarioTres.initOwner(window);
	}//FIN METODO
	
	public Stage getEscenarioTres() {
		return this.escenarioTres;
	}//FIN METODO
	
	private Scene initEscenarioTres(Parent parent) {
		VBox vBoxVentana = new VBox();
		vBoxVentana.getChildren().add(parent);
		vBoxVentana.setPadding(new Insets(10.0d));
		vBoxVentana.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0), new CornerRadii(0), new Insets(0))));
		parent.setEffect(new DropShadow());
		((AnchorPane) parent).setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0,0), new CornerRadii(0), new Insets(0))));
		Scene escena = new Scene(vBoxVentana);
		escena.setFill(Color.TRANSPARENT);
		return escena;
	}//FIN METODO
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}//FIN METODO
	
	public Usuario getUsuario() {
		return this.usuario;
	}//FIN METODO;
	
	//PANTALLAS
	public void openPantallaVenta() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/PantallaVenta.fxml"));
			this.pantallaVenta = (AnchorPane) fxmlLoader.load();
			this.pantallaDos.setCenter(this.pantallaVenta);
			
			PantallaVenta pantallaVenta = fxmlLoader.getController();
			pantallaVenta.setMainApp(this);
			
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public void openPantallaSistema() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/PantallaSistema.fxml"));
			this.pantallaSistema = (AnchorPane) fxmlLoader.load();
			this.pantallaDos.setCenter(this.pantallaSistema);
			
			PantallaSistema pantallaSistema = fxmlLoader.getController();
			pantallaSistema.setMainApp(this);
			
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
	
	//DIALOGOS
	public void openDialogoSesion() {
	    try {
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(MainApp.class.getResource("../view/DialogoSesion.fxml"));
	        this.dialogoSesion = (AnchorPane) fxmlLoader.load();
	        this.pantallaUno.setCenter(this.dialogoSesion);
	        
	        DialogoSesion dialogoLogin = fxmlLoader.getController();
	        dialogoLogin.setMainApp(this);
	        
	        this.escenarioUno.sizeToScene();
			this.escenarioUno.centerOnScreen();
	    } catch (IllegalStateException | IOException ex) {
	    	Notificacion.alertException(ex);
	    }//FIN TRY/CATCH
	}//FIN METODO
	
	public void openDialogoInicio() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/DialogoInicio.fxml"));
			this.dialogoInicio = (AnchorPane) fxmlLoader.load();
			this.pantallaUno.setCenter(this.dialogoInicio);

			DialogoInicio dialogoInicio = fxmlLoader.getController();
			dialogoInicio.setMainApp(this);
			
			this.escenarioUno.sizeToScene();
			this.escenarioUno.centerOnScreen();
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public void openDialogoCliente(Window window, Cliente cliente, Dialogo opcionDialogo) {
		try {
			this.configuracionEscenarioTres(window);
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/DialogoCliente.fxml"));
			this.dialogoCliente = (AnchorPane) fxmlLoader.load();
			
			Scene escenaDialogoCliente = this.initEscenarioTres(this.dialogoCliente);
			this.escenarioTres.setScene(escenaDialogoCliente);
			
			DialogoCliente dialogoCliente = fxmlLoader.getController();
			dialogoCliente.setMainApp(this, cliente, opcionDialogo);
			
			this.escenarioTres.showAndWait();
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public void openDialogoProveedor(Window window, Proveedor proveedor, Dialogo opcionDialogo) {
		try {
			this.configuracionEscenarioTres(window);
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/DialogoProveedor.fxml"));
			this.dialogoProveedor = (AnchorPane) fxmlLoader.load();
			
			Scene escenaDialogoProveedor = this.initEscenarioTres(this.dialogoProveedor);
			this.escenarioTres.setScene(escenaDialogoProveedor);
			
			DialogoProveedor dialogoProveedor = fxmlLoader.getController();
			dialogoProveedor.setMainApp(this, proveedor, opcionDialogo);
			
			this.escenarioTres.showAndWait();
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public void openDialogoProducto(Window window, Producto producto, Dialogo opcionDialogo) {
		try {
			this.configuracionEscenarioTres(window);
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/DialogoProducto.fxml"));
			this.dialogoProducto = (AnchorPane) fxmlLoader.load();
			
			Scene escenaDialogoProducto = this.initEscenarioTres(this.dialogoProducto);
			this.escenarioTres.setScene(escenaDialogoProducto);
			
			DialogoProducto dialogoProducto = fxmlLoader.getController();
			dialogoProducto.setMainApp(this, producto, opcionDialogo);
			
			this.escenarioTres.showAndWait();
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
	
	public void openDialogoMarca(Window window, Marca marca, Dialogo opcionDialogo) {
		try {
			this.configuracionEscenarioTres(window);
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("../view/DialogoMarca.fxml"));
			this.dialogoMarca = (AnchorPane) fxmlLoader.load();
			
			Scene escenaDialogoMarca = this.initEscenarioTres(this.dialogoMarca);
			this.escenarioTres.setScene(escenaDialogoMarca);
			
			DialogoMarca dialogoMarca = fxmlLoader.getController();
			dialogoMarca.setMainApp(this, marca, opcionDialogo);
			this.escenarioTres.showAndWait();
		} catch (IllegalStateException | IOException ex) {
			Notificacion.alertException(ex);
		}//FIN TRY/CATCH
	}//FIN METODO
	
    public static void main(String[] args) {
        launch(args);
    }//FIN METODO
}//FIN CLASE
