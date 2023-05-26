/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
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
	private Calendar fechaInicial;
	private Calendar fechaFinal;
	private String description;
	private List<Categoria> listCategoria;
	private List<GastoExt> gastos;
	private String tipo;
	private Presupuesto presupuesto;
	private boolean revertido=false;
	
	
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
	public Calendar getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Calendar fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Calendar getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Calendar fechaFinal) {
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
	public List<GastoExt> getGastos() {
		return gastos;
	}
	public void setGastos(List<GastoExt> gastos) {
		this.gastos = gastos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	public boolean isRevertido() {
		return revertido;
	}
	public void setRevertido(boolean revertido) {
		this.revertido = revertido;
	}
	
	
	
	
	
	

}
