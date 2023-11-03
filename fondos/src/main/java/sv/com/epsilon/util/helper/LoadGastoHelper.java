/**
 * 
 */
package sv.com.epsilon.util.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.entities.Tipodesembolso;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.MovimientoCtrlr;
import sv.com.epsilon.presupuesto.pojo.GastoLoad;
import sv.com.epsilon.presupuesto.pojo.Rollback;
import sv.com.epsilon.util.Log;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author martinezc
 *
 */
@Slf4j
public class LoadGastoHelper {

	/**
	 * 
	 */
	private final static String REMESA = "REMESA";
	private final static String TRANSF = "TRANSF";
	private final static String DB = "D/B";

	public LoadGastoHelper() {
		// TODO Auto-generated constructor stub
	}

	public static Gasto assigData(GastoLoad v, List<Proveedor> proveedores, HashMap<String, String> uniq) throws Exception {
		Gasto g = new Gasto();
		
			

			g.setAct("A");
			if (!v.getNoTransfer().equals(TRANSF)) {
				g.setCheque(Integer.valueOf(v.getNoTransfer()));
				g.setIdTipoDesembolso(new Tipodesembolso(1));

			} else {
				g.setCheque(0);
				g.setIdTipoDesembolso(new Tipodesembolso(3));
			}
			Double monto = 0.0;
			try {

				monto = new BigDecimal(v.getMontoGasto()).setScale(BigDecimal.ROUND_HALF_UP, 2).doubleValue();
			} catch (Exception e) {
				e.printStackTrace();
				monto = 0.0;
			}
			g.setTotal(monto);
			g.setDescripcion(v.getConcepto().replace("*",","));
			g.setNombre(v.getDescripcionCodigo().replace("*",","));
			g.setFechaRegistro(new Date());
			g.setFecha(v.getFecha());
			g.setIdEmpresa(1);
			g.setKpresupuesto(1);
			g.setStatus("A");
			try {
			assigProveedor(g,v,proveedores);
			}catch (Exception e) {
			
				uniq.put(v.getProveedor(), v.getProveedor());
			}

			Categoria categoria = new CategoriaFacade().obtenerCategoriasFromCodigo( v.getCodigo().startsWith("23FPT")? v.getCodigo():"23FPT"+v.getCodigo());
			if(categoria==null) {
				throw new Exception("No fue posible encontrar categoria: "+v.getCodigo());
			}
			g=new GastoFacade().save(g);
			
			Movimiento mov = new Movimiento();
			mov.setFecha(g.getFecha());
			mov.setIdCategoria(categoria);
			mov.setFechaRegistro(new Date());
			mov.setIdGasto(g);
			mov.setIdPresupuesto(new Presupuesto(1));
			mov.setIdUsuario(3);
			mov.setMonto(g.getTotal());
			mov.setTipo("D");

			new MovimientoCtrlr().save(mov);
			if(g.getDescripcion().equals("ANULADO")) {
				 Rollback rollback=new Rollback();
				WSClient<Rollback>	client=getClient();
				rollback.setIdGasto(g.getIdGasto());
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
//			System.out.println("->" + line);
		
	
		return g;
}
	
	private static WSClient<Rollback> getClient() {
		// TODO Auto-generated method stub
		WSClient cl = new WSClient<>(Rollback.class);
	
		return cl;
	}

	private static void assigProveedor(Gasto g, GastoLoad v, List<Proveedor> proveedores) throws Exception {
		v.setProveedor(v.getProveedor().replace(",", ""));
		if(v.getProveedor().startsWith("PLANILLA SUELDO")) {
			v.setProveedor("FUNDACION PABLO TESAK");
		}
		Optional<Proveedor> proveedor = proveedores.stream()
				.filter(n -> n.getNombre().equalsIgnoreCase(v.getProveedor().trim())
						|| n.getNombreLegal().equalsIgnoreCase(v.getProveedor().trim()))
				.findFirst();
		if (proveedor.isPresent()) {
			g.setIdProveedor(proveedor.get());
			if (g.getIdProveedor().getRetencion().equals("S")) {
				g.setNumeroComprobante(v.getNumSujExc().replace("suj/exc#", ""));
			}
		} else {
			Log.info(v.getProveedor());
//			if(!listNoFound.contains(values[PROVEEDOR].trim()))
//				listNoFound.add(values[PROVEEDOR].trim());
			throw new Exception("No fue posible encontrar proveedor \n" + v.getProveedor().trim());
		}

	}
}