/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.SearchGastoDetalleMesWSClient;
import sv.com.epsilon.ctrlr.wsclient.SearchGastoWSClient;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.BancoFacade;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.ChequeraFacade;
import sv.com.epsilon.facade.CuentaFacade;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.pojo.DetalleGastoMes;
import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.presupuesto.pojo.SearchGasto;
import sv.com.epsilon.presupuesto.pojo.SearchParameterGastoMensual;
import sv.com.epsilon.presupuesto.view.IngresoGastoMB;

/**
 * @author 50364
 *
 */
public class GastoCtrlr {

	private GastoFacade facade=new GastoFacade();;
	
	public static  Integer save(Gasto gasto) throws  Exception{
		
		gasto.setIdEmpresa(1);
		if(gasto.getCheque()==null)
			throw new Exception("Requiere numero cheque/transferencia/recibo");
//		if(gasto.getMovimientoList()==null||gasto.getMovimientoList().size()==0)
//			throw new Exception("Agregar al menos un detalle del gasto");
		if(gasto.getIdProveedor()==null)
			throw new Exception("Proveedor no asignado");
		if(gasto.getIdTipoDesembolso()==null)
			throw new Exception("Seleccione un tipo de desembolso");
		if(gasto.getNombre()==null||gasto.getNombre().equals(""))
			throw new Exception("Ingrese la descripcion del gasto");
		
//		if(gasto.getFecha()==null)
//			throw new Exception("Ingrese la fecha del gasto");
		if(gasto.getTotal()==0)
			throw new Exception("Monto total igual a cero");
		
		
		gasto=new GastoFacade().save(gasto);
		return gasto.getIdGasto();
//		MovimientoFacade movimientoFacade=new MovimientoFacade();
	
			
			
	
		
	}
	
	
	public static void loadin(IngresoGastoMB gastoMB,Presupuesto p) {
		gastoMB.setListPresupuesto(new PresupuestoFacade().findAllActive());
		Integer idPresupuesto=1;
		if(gastoMB.getListPresupuesto().isEmpty())
			return ;
		
		gastoMB.setPresupuestoSelected(gastoMB.getListPresupuesto().get(0));
		idPresupuesto=gastoMB.getListPresupuesto().get(0).getIdPresupuesto();
		
		gastoMB.getGasto().setFecha(Calendar.getInstance());
		gastoMB.setListCategoriaPrincipal(new CategoriaFacade().findPrincipalByCodPresupuesto(p.getCodigo()));
		
		gastoMB.setListBanco(new BancoFacade().findAllActive());
		if(gastoMB.getListBanco().isEmpty())
			return ;
			
		gastoMB.setBancoSelected(gastoMB.getListBanco().get(0));
		gastoMB.setListCuenta(new CuentaFacade().findByIdBanco(gastoMB.getBancoSelected().getIdBanco()));
		if(gastoMB.getListCuenta().isEmpty()) 
			return ;
		
		gastoMB.setCuentaSelected(gastoMB.getListCuenta().get(0));
		gastoMB.setListChequera(new ChequeraFacade().findByIdCuenta(gastoMB.getCuentaSelected().getIdCuenta()));
		if(gastoMB.getListChequera().isEmpty())
			return ;
		if(gastoMB.getListChequera().size()==1)
				gastoMB.setAutomaticChequera(true);
		//gastoMB.setIdChequeraSelected(gastoMB.getListChequera().get(0).getIdChequera());
				
					
				
				
		
		
	}
	
	public void setDeaultChequera(IngresoGastoMB gastoMB) {
		
	}


	public List<GastoExt> invocarBusqueda(SearchGasto search) throws Exception {
		return new SearchGastoWSClient().searchParam(search);
		
	}
	
	public List<Gasto> find(Integer idStarted,Integer idFinished,Integer type){
		return facade.findByRange(idStarted, idFinished, type);
	}


	public List<DetalleGastoMes> invocarBusqueda(SearchParameterGastoMensual search) {
	
		try {
			return  new SearchGastoDetalleMesWSClient().searchParam(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public static void order(List<GastoExt> list ,String order) {
		if(order==null || order.equalsIgnoreCase("DESC")) {
			list.sort(new Comparator<Gasto>() {
				 public int compare(Gasto o1, Gasto o2) {
			            return o2.getFecha().compareTo(o1.getFecha());
			        }
			});
		}else {
			list.sort(new Comparator<Gasto>() {
				 public int compare(Gasto o1, Gasto o2) {
			            return o1.getFecha().compareTo(o2.getFecha());
			        }
			});
		}
	}
	
}
