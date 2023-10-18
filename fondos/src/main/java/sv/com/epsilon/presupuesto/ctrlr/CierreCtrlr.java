/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import sv.com.epsilon.entities.Cierre;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.facade.CierreFacade;
import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.util.Mes;

/**
 * @author martinezc
 *
 */
public class CierreCtrlr {

	/**
	 * 
	 */
	public CierreCtrlr() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Mes> findMesesCerrados(Integer idPresupuesto){
	
		return new CierreFacade().findMesesCerrados(idPresupuesto);
	}

	public void findFirstIncome(Integer idPresupuesto, Integer month, Integer year,List<GastoExt> list) {
		Optional<Cierre> c1 = new CierreFacade().find(idPresupuesto, month - 1);
		if (c1.isPresent()) {
			Cierre c = c1.get();
			GastoExt fIncomes = new GastoExt();
			fIncomes.setAbono(c.getMontoFinal());
			fIncomes.setDescripcion("Monto inicial mensual");
			fIncomes.setAct("T");
			fIncomes.setStatus("T");
			fIncomes.setTotal(c.getMontoFinal());
			Calendar d = Calendar.getInstance();
			d.set(Calendar.DAY_OF_MONTH, 1);
			d.set(Calendar.MONTH, month - 1);
			d.set(Calendar.YEAR, year);
			fIncomes.setFecha(d.getTime());
			list.add(fIncomes);
		}
	}

	public void orderBy(String order,List<GastoExt> list) {
		
			if (order == null || order.equalsIgnoreCase("DESC")) {
				list.sort(new Comparator<Gasto>() {
					public int compare(Gasto o1, Gasto o2) {
						return o2.getFecha().compareTo(o1.getFecha());
					}
				});
			} else {
				list.sort(new Comparator<Gasto>() {
					public int compare(Gasto o1, Gasto o2) {
						return o1.getFecha().compareTo(o2.getFecha());
					}
				});
			}
		
		
	}
}
