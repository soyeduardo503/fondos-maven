/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.Date;

import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.facade.BancoFacade;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.ChequeraFacade;
import sv.com.epsilon.facade.CuentaFacade;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.view.IngresoGastoMB;

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
		facade.save(gasto);
//		MovimientoFacade movimientoFacade=new MovimientoFacade();
	
			
			
	
		
	}
	
	
	public static void loadin(IngresoGastoMB gastoMB) {
		gastoMB.setListPresupuesto(new PresupuestoFacade().findAllActive());
		Integer idPresupuesto=1;
		if(gastoMB.getListPresupuesto().isEmpty())
			return ;
		
		gastoMB.setPresupuestoSelected(gastoMB.getListPresupuesto().get(0));
		idPresupuesto=gastoMB.getListPresupuesto().get(0).getIdPresupuesto();
		
		gastoMB.getGasto().setFecha(new Date());
		gastoMB.setListCategoriaPrincipal(new CategoriaFacade().findPrincipalByCodPresupuesto(idPresupuesto));
		
		gastoMB.setListBanco(new BancoFacade().findAllActive());
			if(gastoMB.getListBanco().size()==1) {
				gastoMB.setBancoSelected(gastoMB.getListBanco().get(0));
				gastoMB.setListCuenta(new CuentaFacade().findByIdBanco(gastoMB.getBancoSelected().getIdBanco()));
				if(gastoMB.getListCuenta().size()==1) {
					gastoMB.setCuentaSelected(gastoMB.getListCuenta().get(0));
					gastoMB.setListChequera(new ChequeraFacade().findByIdCuenta(gastoMB.getCuentaSelected().getIdCuenta()));
					if(gastoMB.getListChequera().size()==1) {
						gastoMB.setAutomaticChequera(true);
						gastoMB.setChequeraSelected(gastoMB.getListChequera().get(0));
					}
					
				}
				
			}
		
	}
	
}
