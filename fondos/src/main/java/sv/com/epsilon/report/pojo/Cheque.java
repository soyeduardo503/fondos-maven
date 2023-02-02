package sv.com.epsilon.report.pojo;

public class Cheque {

	public Cheque() {
		
	}
	private String proveedor;
	private String cantidadLetras;
	private Double cantidad;
	private String concepto;
	private String fecha;
	
	
	public Cheque(String proveedor, String cantidadLetras, Double cantidad, String concepto) {
		super();
		this.proveedor = proveedor;
		this.cantidadLetras = cantidadLetras;
		this.cantidad = cantidad;
		this.concepto = concepto;
	}
	
	
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getCantidadLetras() {
		return cantidadLetras;
	}
	public void setCantidadLetras(String cantidadLetras) {
		this.cantidadLetras = cantidadLetras;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	

}
