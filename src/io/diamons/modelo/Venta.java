package io.diamons.modelo;


import java.sql.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Venta {
	
	//PROPIEDADES
    private ObjectProperty<Integer> sysPK;
    private StringProperty folio;
    private ObjectProperty<Date> fecha;
    private ObjectProperty<Cliente> cliente;
    private ObjectProperty<Producto> producto;
    private ObjectProperty<Integer> cantidadProducto;
    private DoubleProperty subTotal;
    private DoubleProperty descuento;
    private DoubleProperty impuesto;
    private DoubleProperty total;
    
    //CONSTRUCTORES
    public Venta() {
    	this(0, "", new Date(System.currentTimeMillis()), new Cliente(), new Producto(), 0, 0.0, 0.0, 0.0, 0.0);
    }//FIN CONSTRUCTOR

	public Venta(Integer sysPK, String folio, Date fecha, Cliente cliente, Producto producto, Integer cantidadProducto, Double subTotal, Double descuento, Double impuesto, Double total) {
		this.sysPK = new SimpleObjectProperty<Integer>(sysPK);
		this.folio = new SimpleStringProperty(folio);
		this.fecha = new SimpleObjectProperty<Date>(fecha);
		this.cliente = new SimpleObjectProperty<Cliente>(cliente);
		this.producto = new SimpleObjectProperty<Producto>(producto);
		this.cantidadProducto = new SimpleObjectProperty<Integer>(cantidadProducto);
		this.subTotal = new SimpleDoubleProperty(subTotal);
		this.descuento = new SimpleDoubleProperty(descuento);
		this.impuesto = new SimpleDoubleProperty(impuesto);
		this.total = new SimpleDoubleProperty(total);
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

	public String getFolio() {
		return folio.get();
	}//FIN METODO

	public void setFolio(String folio) {
		this.folio.set(folio);
	}//FIN METODO
	
	public StringProperty folioProperty() {
		return folio;
	}//FIN METODO
	
	public ObjectProperty<Date> fechaProperty() {
		return this.fecha;
	}//FIN METODO	

	public Date getFecha() {
		return this.fecha.get();
	}//FIN METODO	

	public void setFecha(final Date fecha) {
		this.fecha.set(fecha);
	}//FIN METODO
	
	public void setCliente(Cliente cliente) {
		this.cliente.set(cliente);
	}//FIN METODO
	
	public Cliente getCliente() {
		return cliente.get();
	}//FIN METODO
	
	public ObjectProperty<Cliente> clienteProperty() {
		return cliente;
	}//FIN METODO
	
	public void setProducto(Producto producto) {
		this.producto.set(producto);
	}//FIN METODO
	
	public Producto getProducto() {
		return producto.get();
	}//FIN METODO
	
	public ObjectProperty<Producto> productoProperty() {
		return producto;
	}//FIN METODO
	
	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto.set(cantidadProducto);
	}//FIN METODO
	
	public Integer getCantidadProducto() {
		return cantidadProducto.get();
	}//FIN METODO
	
	public ObjectProperty<Integer> cantidadProductoProperty() {
		return cantidadProducto;
	}//FIN METODO

	public void setSubTotal(Double subTotal) {
		this.subTotal.set(subTotal);
	}//FIN METODO
	
	public Double getSubTotal() {
		return subTotal.get();
	}//FIN METODO
	
	public DoubleProperty subTotalProperty() {
		return subTotal;
	}//FIN METODO
	
	public void setDescuento(Double descuento) {
		this.descuento.set(descuento);
	}//FIN METODO
	
	public Double getDescuento() {
		return descuento.get();
	}//FIN METODO
	
	public DoubleProperty descuentoProperty() {
		return descuento;
	}//FIN METODO
	
	public void setImpuesto(Double impuesto) {
		this.impuesto.set(impuesto);
	}//FIN METODO
	
	public Double getImpuesto() {
		return impuesto.get();
	}//FIN METODO
	
	public DoubleProperty impuestoProperty() {
		return impuesto;
	}//FIN METODO
	
	public void setTotal(Double total) {
		this.total.set(total);
	}//FIN METODO
	
	public Double getTotal() {
		return total.get();
	}//FIN METODO
	
	public DoubleProperty totalProperty() {
		return total;
	}//FIN METODO

	@Override
	public String toString() {
		return "Venta [sysPK=" + sysPK + ", folio=" + folio + ", fecha=" + fecha + ", cliente=" + cliente
				+ ", producto=" + producto + ", cantidadProducto=" + cantidadProducto + ", subTotal=" + subTotal
				+ ", descuento=" + descuento + ", impuesto=" + impuesto + ", total=" + total + "]";
	}//FIN METODO
}//FIN CLASE
