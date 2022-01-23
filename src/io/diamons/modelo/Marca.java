package io.diamons.modelo;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marca {

	//PROPIEDADES
	private ObjectProperty<Integer> sysPK;
	private StringProperty codigo;
	private StringProperty marca;
	
	//CONSTRUCTORES
	public Marca() {
		this(0, "", "");
	}//FIN CONSTRUCTOR
	
	public Marca(Integer sysPK, String codigo, String marca) {
		this.sysPK = new SimpleObjectProperty<Integer>(sysPK);
		this.codigo = new SimpleStringProperty(codigo);
		this.marca = new SimpleStringProperty(marca);
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
	
	public String getMarca() {
		return marca.get();
	}//FIN METODO
	
	public void setMarca(String marca) {
		this.marca.set(marca);
	}//FIN METODO
	
	public StringProperty marcaProperty() {
		return marca;
	}//FIN METODO

	@Override
	public String toString() {
		return this.getMarca();
	}//FIN METODO
	
//	@Override
//	public String toString() {
//		return "Marca [sysPK=" + sysPK + ", codigo=" + codigo + ", marca=" + marca + "]";
//	}//FIN METODO
}//FIN METODO
