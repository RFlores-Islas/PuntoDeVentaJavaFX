package io.diamons.modelo;

public class Usuario {
	
	//PROPIEDADES
	private int id;
    private String nombre;
    private String correo;
    private String password;
    private StatusUsuario status;
    private String rol;
    
    //CONSTRUCTORES
    public Usuario() {
    }//FIN CONSTRUCTOR

	public Usuario(int id, String nombre, String correo, String password, StatusUsuario status, String rol) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.status = status;
		this.rol = rol;
	}//FIN CONSTRUCTOR

	//METODOS
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

	public String getCorreo() {
		return correo;
	}//FIN METODO

	public void setCorreo(String correo) {
		this.correo = correo;
	}//FIN METODO

	public String getPassword() {
		return password;
	}//FIN METODO

	public void setPassword(String password) {
		this.password = password;
	}//FIN METODO
	
	public void setStatus(StatusUsuario status) {
		this.status = status;
	}//FIN METODO
	
	public StatusUsuario getStatus() {
		return status;
	}//FIN METODO

	public String getRol() {
		return rol;
	}//FIN METODO

	public void setRol(String rol) {
		this.rol = rol;
	}//FIN METODO

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", password=" + password
				+ ", status=" + status + ", rol=" + rol + "]";
	}//FIN METODO
}//FIN CLASE
