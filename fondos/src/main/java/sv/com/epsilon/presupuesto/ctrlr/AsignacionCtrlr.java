/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.math.BigDecimal;
import java.util.List;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.util.Util;

/**
 * @author usuario07
 *
 */
public class AsignacionCtrlr {

	
	public boolean asignacionPresupuesto(Presupuesto p,double monto){
		if(monto<0)
			return false;
		if(p.getTotal()==0){
			p.setTotal(monto);
			//p.setActual(monto);
			
		}else{
			p.setTotal(p.getTotal()+monto);
			p.setActual(p.getActual()+monto);
		}
		return true;
	}
	
	private boolean verificarPresupuesto(Presupuesto p,double monto){
		
		if(disponible(p)>=monto){
			
			
		return true;
		}
		return false;
	}
	private boolean actualizarCategoriaPadre(Categoria cp,double monto){
		
		if(cp.getMonto()>0){
			
			cp.setMonto(cp.getMonto()+monto);
			return true;
		}
		return false;
	}
	
	public boolean validarAsignacion(Categoria categoria, double monto){
		if(categoria.getIdPresupuesto()!=null){
			/**si es categoria principal*/
			Presupuesto p=categoria.getIdPresupuesto();
			if( validarAsignacion(categoria,disponible(p),p.getTotal(),monto)){
				return verificarPresupuesto(p,monto);
				
			}
			
		}else{
			/**si es categoria hija*/
			
			
			if( validarAsignacion(categoria, disponible(categoria.getIdCategoriaPadre()), categoria.getMonto(), monto))
				actualizarPadres(categoria.getIdCategoriaPadre(),monto);
		}
		return false;
		
	}
	
	private boolean actualizarPadres(Categoria cPadre, double monto) {
		if(cPadre.getIdCategoriaPadre()==null)
		{
			if(verificarPresupuesto(cPadre.getIdPresupuesto(),monto))
			 return actualizarCategoriaPadre(cPadre, monto);
			
		}else{
			if(actualizarCategoriaPadre(cPadre, monto))
				return actualizarPadres(cPadre.getIdCategoriaPadre(),monto);
		}
		
		return false;
	}

	/**Categoria a asignar, disponible de presupuesto o de categoria padre, monto asignado al momento a la categoria, monto a asignar*/
	private boolean validarAsignacion(Categoria categoria,double disponible,double montoAsignado,Double monto){
		if(disponible>=monto){
			/**si el monto es posible de lo asignado y esta a cero */
			if(montoAsignado==0){
				categoria.setMonto(monto);
				categoria.setActual(monto);
				new EventoCtrlr().registrarEvento("Asignar a "+categoria.obtenerNombreCompleto()+" -> $"+monto,EventoCtrlr.ingreso);
			}else{
				/*si ya tiene gastos registrados y monto asignado(incremento)*/
				categoria.setMonto(categoria.getMonto()+monto);
				categoria.setActual(categoria.getActual()+monto);
				new EventoCtrlr().registrarEvento("Incrementar a "+categoria.obtenerNombreCompleto()+" -> $"+monto,EventoCtrlr.ingreso);
			}
		}else{
			return false;
		}
		return true;
	}
	public static int percentComplete(Presupuesto p, Double d) {
		return  calcPercent(p.getTotal(),d);
	}
	public static int percentComplete(Presupuesto p) {
		
		return calcPercent(p.getTotal(),disponible(p));
	}
	public  static int percentCompleteCategoria(Categoria c) {
		return calcPercent(c.getMonto(),disponible(c));
		//return new BigDecimal((p.getMonto()-disponible(p))/p.getMonto()).intValue();
	}
	public static Double disponible(Presupuesto p){
		
		
//		BigDecimal montoRecalculado= montoRecalculado(p.getCategoriaList());
		return Util.subAndRound2(new BigDecimal (p.getTotal()),montoRecalculado(p.getCategoriaList())).doubleValue();
	}
	
	public static Double disponible(Categoria c){
		return Util.subAndRound2(new BigDecimal(c.getMonto()), montoRecalculado(c.getCategoriaList())).doubleValue();
	}
	
	private static BigDecimal montoRecalculado(List<Categoria> cats){
		BigDecimal monto=new BigDecimal(0.0);
		for(Categoria c:cats){
			monto=Util.addAndRound2(monto, new BigDecimal(c.getMonto()));
		}
		return monto;
	}
	
	public boolean montoDisponible(Presupuesto p,Double monto) {
		
		BigDecimal total = new BigDecimal (p.getTotal());
		BigDecimal asignado=  montoRecalculado(p.getCategoriaList());
		BigDecimal disponible=Util.subAndRound2(total, asignado);
		
		return total.compareTo(asignado)!=-1 && disponible.compareTo(new BigDecimal(monto))!=-1;
	}
	

	public boolean montoDisponible(Categoria categoriaPadre,Double monto) {
		
		BigDecimal total = new BigDecimal (categoriaPadre.getMonto());
		BigDecimal asignado=  montoRecalculado(categoriaPadre.getCategoriaList());
		BigDecimal disponible=Util.subAndRound2(total, asignado);
		return total.compareTo(asignado)==-1 && disponible.compareTo(new BigDecimal(monto))!=-1;
	}
	
	public static int calcPercent(Double total,Double monto) {
		return new BigDecimal ((total-monto)/total).intValue();
	}
	
	
	
}
