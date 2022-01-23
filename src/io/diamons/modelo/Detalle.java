package io.diamons.modelo;

public class Detalle {
	
	//PROPIEDADES
	private int id;
    private int idProducto;
    private int cantidad;
    private double precio;
    private int idVenta;
    
    //CONSTRUCTORES
    public Detalle() {
    }//FIN CONSTRUCTOR

	public Detalle(int id, int idProducto, int cantidad, double precio, int idVenta) {
		super();
		this.id = id;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.idVenta = idVenta;
	}//FIN CONSTRUCTOR

	public int getId() {
		return id;
	}//FIN METODO

	public void setId(int id) {
		this.id = id;
	}//FIN METODO

	public int getIdProducto() {
		return idProducto;
	}//FIN METODO

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}//FIN METODO

	public int getCantidad() {
		return cantidad;
	}//FIN METODO

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}//FIN METODO

	public double getPrecio() {
		return precio;
	}//FIN METODO

	public void setPrecio(double precio) {
		this.precio = precio;
	}//FIN METODO

	public int getIdVenta() {
		return idVenta;
	}//FIN METODO

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}//FIN METODO

	@Override
	public String toString() {
		return "Detalle [id=" + id + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", idVenta=" + idVenta + "]";
	}//FIN METODO
}//FIN CLASE
