package io.diamons.modelo;

public class Combo {
	
	//PROPIEDADES
	private int id;
	private String nombre;
	
	//CONSTRUCTORES
	public Combo() {
	}//FIN CONSTRUCTOR

	public Combo(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}//FIN CONSTRUCTOR

	public int getId() {
		return id;
	}//FIN METODO

	public void setId(int id) {
		this.id = id;
	}//FIN METODO

	public String getNombre() {
		return nombre;
	}//FIN METODO

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}//FIN METODO

	@Override
	public String toString() {
		return "Combo [id=" + id + ", nombre=" + nombre + "]";
	}//FIN METODO
}//FIN CLASE
