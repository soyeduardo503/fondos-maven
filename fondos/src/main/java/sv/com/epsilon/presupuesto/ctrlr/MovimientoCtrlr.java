/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.List;

import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.facade.MovimientoFacade;

/**
 * @author 50364
 *
 */
public class MovimientoCtrlr {

	private MovimientoFacade facade=new MovimientoFacade();
	public  void save(Movimiento mov) throws Exception {
		
		facade.persist(mov);
//		new CategoriaFacade().updateMontoDisponible(mov.getMonto(),CodigoCtrlr.getCodigoPadre(
//				mov.getIdCategoria().getCodigo()));
	}
	
	public List<Movimiento> load(Integer idGasto){
		return facade.list("/byGasto/"+idGasto);
	}
}
