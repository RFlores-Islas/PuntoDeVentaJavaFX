package io.diamons.modelo;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
	
	//PROPIEDADES
	private ObjectProperty<Integer> sysPK;
	private StringProperty codigo;
    private StringProperty nombre;
    private StringProperty telefono;
    private StringProperty direccion;
    
    //CONSTRUCTORES
    public Cliente() {
    	this(0, "", "", "", "");
    }//FIN CONSTRUCTOR
    
    public Cliente(Integer sysPK, String codigo, String nombre, String telefono, String direccion) {
    	this.sysPK = new SimpleObjectProperty<Integer>(sysPK);
    	this.codigo = new SimpleStringProperty(codigo);
    	this.nombre = new SimpleStringProperty(nombre);
    	this.telefono = new SimpleStringProperty(telefono);
    	this.direccion = new SimpleStringProperty(direccion);
    }//FIN CONSTRUCTOR
    
    //METODOS
	public void setSysPK(Integer sysPK) {
		this.sysPK.set(sysPK);	
	}//FIN METODO
	
	public Integer getSysPK() {
		return sysPK.get();
	}//FIN METODO
	
	public ObjectProperty<Integer> sysPKProperty() {
		return sysPK;
	}//FIN METODO 

	public String getCodigo() {
		return codigo.get();
	}//FIN METODO

	public void setCodigo(String codigo) {
		this.codigo.set(codigo);
	}//FIN METODO
	
	public StringProperty codigoProperty() {
		return codigo;
	}//FIN METODO

	public String getNombre() {
		return nombre.get();
	}//FIN METODO

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}//FIN METODO
	
	public StringProperty nombreProperty() {
		return nombre;
	}//FIN METODO

	public String getTelefono() {
		return telefono.get();
	}//FIN METODO

	public void setTelefono(String telefono) {
		this.telefono.set(telefono);
	}//FIN METODO
	
	public StringProperty telefonoProperty() {
		return telefono;
	}//FIN METODO

	public String getDireccion() {
		return direccion.get();
	}//FIN METODO

	public void setDireccion(String direccion) {
		this.direccion.set(direccion);
	}//FIN METODO
	
	public StringProperty direccionProperty() {
		return direccion;
	}//FIN METODO

	@Override
	public String toString() {
		return this.getNombre();
	}//FIN METODO
	
//	@Override
//	public String toString() {
//		return "Cliente [sysPK=" + sysPK + ", codigo=" + codigo + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion="
//				+ direccion + "]";
//	}//FIN METODO
}//FIN CLASE
