package io.diamons.utilities;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class Validaciones {
	
	public void textKeyPress(KeyEvent event) {
        char caracter = event.getKeyChar();
	        if ((caracter < 'a' || caracter > 'z') && (caracter < 'A' || caracter > 'Z')
	                && (caracter != (char) KeyEvent.VK_BACK_SPACE) && (caracter != (char) KeyEvent.VK_SPACE)) {
	            event.consume();
	        }//FIN IF
	}//FIN METODO

    public void numberKeyPress(KeyEvent event) {
        char caracter = event.getKeyChar();
	        if ((caracter < '0' || caracter > '9') && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
	            event.consume();
	        }//FIN IF
    }//FIN METODO

    public void numberDecimalKeyPress(KeyEvent event, JTextField textField) {
        char caracter = event.getKeyChar();
	        if ((caracter < '0' || caracter > '9') && textField.getText().contains(".") && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
	            event.consume();
	        } else if ((caracter < '0' || caracter > '9') && (caracter != '.') && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
	            event.consume();
	        }//FIN ELSE/IF
    }//FIN METODO
}//FIN CLASE
