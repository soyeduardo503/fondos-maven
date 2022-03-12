/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import sv.com.epsilon.entities.Categoria;

/**
 * @author usuario07
 *
 */
public class ViewNode implements Serializable, Comparable<ViewNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7399949267097964570L;
	private String nombre="";
	private int  hijos=0;
	private double  total=0.0;
	
	private double disponible=0.0;
	private double gastado=0.0;
	private int percent=0;
	private String codigo="";
	private Categoria categoria;
	private Object nodo;
	private Double montoPadre;

	/**
	 * 
	 */
	
	
	public ViewNode() {
		
	}
	

	public ViewNode(String nombre, int hijos, double total,
			double disponible, double gastado,Categoria c, Object nodo,int percent,String codigo,Double montoPadre) {
		super();
		this.nombre = nombre;
		this.hijos = hijos;
		this.total = total;
		this.disponible = disponible;
		this.gastado = gastado;
		this.nodo=nodo;
		this.categoria=c;
		this.percent=percent;
		this.codigo=codigo;
		this.montoPadre=montoPadre;
	}

	public Double getPorcentajeDisponible(){
		if(total>0)
			return new BigDecimal(getTotal()-getDisponible()).divide(new BigDecimal(getTotal()),2,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).doubleValue();
		return 0.0;
	}
	public String getColorDisponible(){
		double d=0.0;
		if(total>0)
			d= new BigDecimal(getTotal()-getDisponible()).divide(new BigDecimal(getTotal()),2,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).doubleValue();
		
		if(d<20)
			return "b4ff60";
		if(d<50)
			return "fffc1a";
		return "ff5a5a";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHijos() {
		return hijos;
	}

	public void setHijos(int hijos) {
		this.hijos = hijos;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDisponible() {
		return disponible;
	}

	public void setDisponible(double disponible) {
		this.disponible = disponible;
	}

	public double getGastado() {
		return gastado;
	}

	public void setGastado(double gastado) {
		this.gastado = gastado;
	}
	

	public Object getNodo() {
		return nodo;
	}


	public void setNodo(Object nodo) {
		this.nodo = nodo;
	}

	

	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	


	public int getPercent() {
		return percent;
	}


	public void setPercent(int percent) {
		this.percent = percent;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	@Override
	public int compareTo(ViewNode o) {
		return 	this.nombre.compareTo(o.getNombre());
		
	}


	public Double getMontoPadre() {
		return montoPadre;
	}


	public void setMontoPadre(Double montoPadre) {
		this.montoPadre = montoPadre;
	}


	
	

}
