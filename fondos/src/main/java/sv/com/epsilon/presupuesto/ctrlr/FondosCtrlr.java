/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.List;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Movimiento;

/**
 * @author martinezc
 *
 */
public class FondosCtrlr {

	public boolean	disponible(Categoria categoriaPadre,Double monto) {
		return categoriaPadre.getMonto()-categoriaPadre.getActual()>monto;		
	}
	
	public void save(Categoria padre,List<Movimiento> movimientos) {
		
	}
	
}
