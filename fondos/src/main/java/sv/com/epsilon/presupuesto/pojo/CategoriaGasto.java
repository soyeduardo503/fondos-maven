package sv.com.epsilon.presupuesto.pojo;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;

public class CategoriaGasto {

	private Categoria categoria;
	private Double monto;
	private String year;
	private Double disponible;
	private Presupuesto presupuesto;
	private String txtCategoria;
	
	
	public CategoriaGasto() {
		super();
	}


	public CategoriaGasto(Categoria c, double d, Presupuesto p, String year) {
		super();
		this.categoria = c;
		this.monto = d;
		this.presupuesto=p;
		this.year=year;
	}
	
	
	public CategoriaGasto(Categoria categoria, Double monto) {
		super();
		this.categoria = categoria;
		this.monto = monto;
	}


	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public Presupuesto getPresupuesto() {
		return presupuesto;
	}


	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}


	public String getTxtCategoria() {
		return txtCategoria;
	}


	public void setTxtCategoria(String txtCategoria) {
		this.txtCategoria = txtCategoria;
	}


	public Double getDisponible() {
		return disponible;
	}


	public void setDisponible(Double disponible) {
		this.disponible = disponible;
	}

	
	
}
