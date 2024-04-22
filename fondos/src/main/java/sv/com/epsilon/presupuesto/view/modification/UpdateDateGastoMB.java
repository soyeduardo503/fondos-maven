/**
 * 
 */
package sv.com.epsilon.presupuesto.view.modification;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * 
 */
@ManagedBean(name = "dateGastoMB")
@ViewScoped
@Data
@Slf4j
public class UpdateDateGastoMB {

	/**
	 * 
	 */
	
	private Gasto gastoSelected;
	private String idForm="updateGastoDateID";
	private String idWgt="updateGastoDateWgt";
	private Date fecha;
	
	public UpdateDateGastoMB() {
		// TODO Auto-generated constructor stub
	}

	public void setGastoSelected(Gasto gastoSelected) {
		this.gastoSelected=gastoSelected;
		this.fecha=gastoSelected.getFecha().getTime();
	}
	
	public void update() {
		try {
			
			gastoSelected.getFecha().setTime(fecha);
			new GastoFacade().updateFecha(this.gastoSelected);
		} catch (Exception e) {
			new MessageGrowlContext().sendError("No fue posible actualizar ", "Error al intentar guardar", e);
		}
		
	}
	
	
	
}
