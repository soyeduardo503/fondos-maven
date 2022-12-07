/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
public class RevertMB   implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gasto gasto;
	private String idForm="idRevert";
	private String wgt="wgtRevert";
	public RevertMB() {
	}
	public Gasto getGasto() {
		return gasto;
	}
	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}
	
	

	public String getIdForm() {
		return idForm;
	}
	public void setIdForm(String idForm) {
		this.idForm = idForm;
	}
	
	
	
	public String getWgt() {
		return wgt;
	}
	public void setWgt(String wgt) {
		this.wgt = wgt;
	}
	public void action() {
		try {
			new GastoFacade().action("/revert/"+this.gasto.getIdGasto(), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new MessageGrowlContext().send("Pago reversado", "El pago se reverso satisfactoriamente!!!");
		
	}
	
	public void show() {
		
	}
	
	
	
	
}
