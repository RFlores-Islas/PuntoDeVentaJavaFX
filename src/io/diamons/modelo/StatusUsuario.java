package io.diamons.modelo;

import java.util.HashMap;
import java.util.Map;

public enum StatusUsuario {
	
	BAJA(1),
	BLOQUEADO(2),
	ACTIVO(3),
	NO_EXISTE(4),
	CONTRASENA_INCORRECTA(5);
	
	private int constante;
	private static Map<Object, Object> map = new HashMap<>();
	
	StatusUsuario(int constante) {
		this.constante = constante;
	}//FIN CONSTRUCTOR
	
	public int getConstante() {
		return constante;
	}//FIN METODO
	
	public String toString() {
		switch(this) {
		case BAJA:
			return "Baja";
		case BLOQUEADO:
			return "Bloqueado";
		case ACTIVO:
			return "Activo";
		case NO_EXISTE:
			return "No Existe";
		case CONTRASENA_INCORRECTA:
			return "Contraseña incorrecta";
		default:
			return "";	
		}//FIN SWITCH
	}//FIN METODO
	
	static {
		for (StatusUsuario statusUsuario : StatusUsuario.values())
			map.put(statusUsuario.constante, statusUsuario);
	}//FIN METODO
	
	public static StatusUsuario valueOf(int statusUsuario) {
		return (StatusUsuario) map.get(statusUsuario);
	}//FIN METOD

}//FIN CLASE
