package io.diamons.modelo;

import java.util.HashMap;
import java.util.Map;

public enum Dialogo {
	CREAR(1),
	EDITAR(2),
	VER(3);

	private int constante;
	private static Map<Object, Object> map = new HashMap<>();
	
	private Dialogo(int constante) {
		this.constante = constante;
	}//FIN CONSTRUCTOR
	
	public int getConstante() {
		return constante;
	}//FIN METODO
	
	public String toString() {
		switch(this) {
		case CREAR:
			return "Crear";
		case EDITAR:
			return "Editar";
		case VER:
			return "Ver";
		default:
			throw new AssertionError("Opción para dialogo desconocida: " + this);
		}//FIN SWITCH
	}//FIN METODO
	
	static {
		for (Dialogo dialogo : Dialogo.values())
			map.put(dialogo.constante, dialogo);
	}//FIN METODO
	
	public static Dialogo valueOf(int constante) {
		return (Dialogo) map.get(constante);
	}//FIN METODO
}//FIN ENUM
