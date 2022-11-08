/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.List;

import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.presupuesto.pojo.CategoriaGasto;

/**
 * @author martinezc
 *
 */
public class CategoriaGastoCtrlr {

	/**
	 * 
	 */
	List<CategoriaGasto> list;
	public CategoriaGastoCtrlr() {
	}
	

	public List<CategoriaGasto> convert(Integer idGasto ){
		List<Movimiento>listMov =new MovimientoCtrlr().load(idGasto);
		list=new ArrayList<CategoriaGasto>();
		listMov.forEach(CategoriaGastoCtrlr::fillValue);
		
		
		return list;
	}
	
	public static void fillValue(Movimiento mov) {
		CategoriaGasto catGas=new CategoriaGasto();
		catGas.setMonto(mov.getMonto());
		catGas.setCategoria(mov.getIdCategoria());
		
		//catGas.setTxtCategoria(mov.);
	}
	
	
}
