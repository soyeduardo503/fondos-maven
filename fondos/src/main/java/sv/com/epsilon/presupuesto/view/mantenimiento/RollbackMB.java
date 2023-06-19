/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.presupuesto.pojo.Rollback;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
@Data
public class RollbackMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8506021305029775602L;

	private Rollback rollback=new Rollback();
	private Gasto gasto;
	
	
	public void callRollback() {
		WSClient<Rollback>	client=getClient();
		rollback.setIdGasto(gasto.getIdGasto());
		try {
			client.action("/call", true,rollback);
			rollback=new Rollback();
			new MessageGrowlContext().send("Gasto ha sido anulado", "Total del gasto a cero");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new MessageGrowlContext().sendError("Error al anular pago", "no se logro anular el gasto",e);
		}
	}


	private WSClient<Rollback> getClient() {
		// TODO Auto-generated method stub
		WSClient cl = new WSClient<>(Rollback.class);
	
		return cl;
	}
	
	
	
}
