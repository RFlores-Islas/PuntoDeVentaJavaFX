package io.diamons.modelo;

public class Configuracion {
	
	//PROPIEDADES
	private int id;
    private String rfc;
    private String nombre;
    private String telefono;
    private String direccion;
    private String mensaje;
    
    //CONSTRUCTORES
    public Configuracion() {
    }//FIN CONSTRUCTOR

	public Configuracion(int id, String rfc, String nombre, String telefono, String direccion, String mensaje) {
		this.id = id;
		this.rfc = rfc;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.mensaje = mensaje;
	}//FIN CONSTRUCTOR
    
	public void setId(int id) {
		this.id = id;
	}//FIN METODO
	
	public int getId() {
		return id;
	}//FIN METODO

	public String getRfc() {
		return rfc;
	}//FIN METODO

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}//FIN METODO

	public String getNombre() {
		return nombre;
	}//FIN METODO

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}//FIN METODO

	public String getTelefono() {
		return telefono;
	}//FIN METODO

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}//FIN METODO

	public String getDireccion() {
		return direccion;
	}//FIN METODO

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}//FIN METODO

	public String getMensaje() {
		return mensaje;
	}//FIN METODO

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}//FIN METODO

	@Override
	public String toString() {
		return "Configuracion [id=" + id + ", rfc=" + rfc + ", nombre=" + nombre + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", mensaje=" + mensaje + "]";
	}//FIN METODO
}//FIN CLASE
