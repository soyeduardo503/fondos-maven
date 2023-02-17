/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.presupuesto.pojo.CategoriaGasto;

/**
 * @author martinezc
 *
 */
public class CategoriaGastoCtrlr {

	/**
	 * 
	 */
	public CategoriaGastoCtrlr() {
	}
	

	public List<CategoriaGasto> convert(Integer idGasto ){
		return copy(idGasto, false);
	}
	
	public List<CategoriaGasto> copy(Integer idGasto , boolean copy){
		
		List<Movimiento>listMov =new MovimientoCtrlr().load(idGasto);
		List<CategoriaGasto> list=new ArrayList<CategoriaGasto>();
		listMov.forEach(v->{
			list.add(fillValue(v,copy));
		});
		
		return list;
	}
	
	public  CategoriaGasto fillValue(Movimiento mov,boolean copy) {
		CategoriaGasto catGas=new CategoriaGasto();
		catGas.setMonto(mov.getMonto());
		catGas.setDisponible( new CategoriaFacade().getMontoDisponible(CodigoCtrlr.getCodigoPadre( mov.getIdCategoria().getCodigo())).doubleValue());
		catGas.setCategoria(mov.getIdCategoria());
		catGas.setIdMovimiento(copy?0: mov.getIdMovimiento());
		return catGas;
		//catGas.setTxtCategoria(mov.);
	}
	
	
}
