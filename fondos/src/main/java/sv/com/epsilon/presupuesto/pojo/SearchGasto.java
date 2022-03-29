/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.Date;
import java.util.List;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Proveedor;

/**
 * @author martinezc
 *
 */
public class SearchGasto {

	/**
	 * 
	 */
	
	private String textProveedor;
	private Proveedor proveedor;
	private Double monto;
	private String numeroCheque;
	private Date fechaInicial;
	private Date fechaFinal;
	private String description;
	private List<Categoria> listCategoria;
	private List<Gasto> gastos;
	
	
	public SearchGasto() {
		
	}
	public String getTextProveedor() {
		return textProveedor;
	}
	public void setTextProveedor(String textProveedor) {
		this.textProveedor = textProveedor;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
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
	public List<Categoria> getListCategoria() {
		return listCategoria;
	}
	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}
	public List<Gasto> getGastos() {
		return gastos;
	}
	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}
	
	
	
	

}
