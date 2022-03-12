/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.facade.MovimientoFacade;

/**
 * @author 50364
 *
 */
public class GastoCtrlr {

	
	
	public static  void save(Gasto gasto) throws  Exception{
		
		gasto.setIdEmpresa("1");
		if(gasto.getCheque()==null)
			throw new Exception("Requiere numero cheque/transferencia/recibo");
//		if(gasto.getMovimientoList()==null||gasto.getMovimientoList().size()==0)
//			throw new Exception("Agregar al menos un detalle del gasto");
		if(gasto.getIdProveedor()==null)
			throw new Exception("Proveedor no asignado");
		if(gasto.getIdTipoDesembolso()==null)
			throw new Exception("Seleccione un tipo de desembolso");
		if(gasto.getNombre()==null||gasto.getNombre().equals(""))
			throw new Exception("Ingrese el nombre del gasto");
		
//		if(gasto.getFecha()==null)
//			throw new Exception("Ingrese la fecha del gasto");
		if(gasto.getTotal()==0)
			throw new Exception("Monto total igual a cero");
		
		GastoFacade facade=new GastoFacade();
		facade.persis(gasto);
//		MovimientoFacade movimientoFacade=new MovimientoFacade();
	
			
			
	
		
	}
	
}
