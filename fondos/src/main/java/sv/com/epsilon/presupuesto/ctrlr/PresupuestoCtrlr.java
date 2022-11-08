/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.Optional;

import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.PresupuestoFacade;

/**
 * @author 50364
 *
 */
public class PresupuestoCtrlr {

	
	
	
	public Presupuesto predeterminado(Integer idEmpresa){
		PresupuestoFacade facade=new PresupuestoFacade();
		Optional<Presupuesto> p = facade.select(idEmpresa);
		if(p.isPresent())
			return p.get();
		else
			return new Presupuesto();
	}
}
