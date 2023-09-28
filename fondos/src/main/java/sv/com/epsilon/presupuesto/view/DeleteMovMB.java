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
public class DeleteMovMB   implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gasto gasto;
	private String idForm="idDeleteMov";
	private String wgt="wgtDeleteMov";
	public DeleteMovMB() {
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
			new GastoFacade().action("/delete/"+this.gasto.getIdGasto(), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new MessageGrowlContext().send("Pago eliminado", "El pago se elimino satisfactoriamente!!!");
		
	}
	
	public void show() {
		
	}
	
	
	
	
}
