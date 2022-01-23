package io.diamons.modelo;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
	
	//PROPIEDADES
	private ObjectProperty<Integer> sysPK;
	private StringProperty codigo;
	private StringProperty descripcion;
	private ObjectProperty<Proveedor> proveedor;
	private ObjectProperty<Marca> marca;
	private ObjectProperty<Integer> stock;
	private ObjectProperty<Double> precio;
	
	//CONSTRUCTORES
	public Producto() {
		this(0, "", "", new Proveedor(), new Marca(), 0, 0.0);
	}//FIN CONSTRUCTOR

	public Producto(Integer sysPK, String codigo, String descripcion, Proveedor proovedor, Marca marca, Integer stock, Double precio) {
		this.sysPK = new SimpleObjectProperty<Integer>(sysPK);
		this.codigo = new SimpleStringProperty(codigo);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.proveedor = new SimpleObjectProperty<Proveedor>(proovedor);
		this.marca = new SimpleObjectProperty<Marca>(marca);
		this.stock = new SimpleObjectProperty<Integer>(stock);
		this.precio = new SimpleObjectProperty<Double>(precio);
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

	public String getDescripcion() {
		return descripcion.get();
	}//FIN METODO

	public void setDescripcion(String descripcion) {
		this.descripcion.set(descripcion);
	}//FIN METODO
	
	public StringProperty descripcionProperty() {
		return descripcion;
	}//FIN METODO

	public Proveedor getProveedor() {
		return proveedor.get();
	}//FIN METODO

	public void setProveedor(Proveedor proveedor) {
		this.proveedor.set(proveedor);
	}//FIN METODO
	
	public ObjectProperty<Proveedor> proveedorProperty() {
		return proveedor;
	}//FIN METODO
	
	public Marca getMarca() {
		return marca.get();
	}//FIN METODO

	public void setMarca(Marca marca) {
		this.marca.set(marca);
	}//FIN METODO
	
	public ObjectProperty<Marca> marcaProperty() {
		return marca;
	}//FIN METODO

	public Integer getStock() {
		return stock.get();
	}//FIN METODO//FIN METODO

	public void setStock(Integer stock) {
		this.stock.set(stock);
	}//FIN METODO

	public ObjectProperty<Integer> stockProperty() {
		return stock;
	}//FIN METODO
	
	public Double getPrecio() {
		return precio.get();
	}//FIN METODO

	public void setPrecio(Double precio) {
		this.precio.set(precio);
	}//FIN METODO
	
	public ObjectProperty<Double> precioProperty() {
		return precio;
	}//FIN METODO

	@Override
	public String toString() {
		return "Producto [sysPK=" + sysPK + ", codigo=" + codigo + ", producto=" + descripcion + ", proveedor=" + proveedor
				+ ", marca=" + marca + ", stock=" + stock + ", precio=" + precio + "]";
	}//FIN METODO
}//FIN CLASE
