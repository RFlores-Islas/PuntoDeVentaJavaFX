package io.diamons.modelo;

import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

public class Notificacion {
	
	public static void alertException(Exception ex) {
		Toolkit.getDefaultToolkit().beep();
        Alert alert = new Alert(AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Alerta del Sistema");
        alert.setHeaderText("");
        alert.setContentText("Ocurrio un error inesperado durante la ejecución del sistema: \n" + ex.getMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("El error fue ocacionado por:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
	}//FIN METODO
	
	public static boolean aletarConfirmation(String titulo, String mensaje) {
		Toolkit.getDefaultToolkit().beep();
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.initStyle(StageStyle.UTILITY);
	    alert.setTitle("Alerta del Sistema");
	    alert.setHeaderText(titulo);
	    alert.setContentText(mensaje);
	    Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
	}//FIN METODO
	
	public static void alertaInfo(AlertType tipo, String titulo, String mensaje) {
		Toolkit.getDefaultToolkit().beep();
	    Alert alert = new Alert(tipo);
	    alert.initStyle(StageStyle.UTILITY);
	    alert.setHeaderText(titulo);
	    alert.setTitle("Alerta del Sistema");
	    alert.setContentText(mensaje);
	    alert.showAndWait();
	}//FIN METODO

}//FIN CLASE
