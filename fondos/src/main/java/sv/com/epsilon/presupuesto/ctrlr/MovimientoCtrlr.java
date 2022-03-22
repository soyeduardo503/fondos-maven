/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.MovimientoFacade;

/**
 * @author 50364
 *
 */
public class MovimientoCtrlr {

	
	public static void save(Movimiento mov) throws Exception {
		MovimientoFacade facade=new MovimientoFacade();
		facade.save(mov);
		new CategoriaFacade().updateMontoDisponible(mov.getMonto(),CodigoCtrlr.getCodigoPadre(
				mov.getIdCategoria().getCodigo()));
	}
	
}
