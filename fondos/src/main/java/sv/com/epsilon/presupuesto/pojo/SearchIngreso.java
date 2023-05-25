/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.Date;
import java.util.List;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Financiamiento;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Proveedor;

/**
 * @author martinezc
 *
 */
public class SearchIngreso {

	/**
	 * 
	 */
	
	private String textProveedor;
	private Integer idCatingreso;
	private Double monto;
	
	private Date fechaInicial;
	private Date fechaFinal;
	private String description;
	private List<Financiamiento> list;
	private Presupuesto presupuesto;
	
	
	
	public SearchIngreso() {
		
	}
	public String getTextProveedor() {
		return textProveedor;
	}
	public void setTextProveedor(String textProveedor) {
		this.textProveedor = textProveedor;
	}
	
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	public Integer getIdCatingreso() {
		return idCatingreso;
	}
	public void setIdCatingreso(Integer idCatingreso) {
		this.idCatingreso = idCatingreso;
	}
	public List<Financiamiento> getList() {
		return list;
	}
	public void setList(List<Financiamiento> list) {
		this.list = list;
	}
	
	

}
