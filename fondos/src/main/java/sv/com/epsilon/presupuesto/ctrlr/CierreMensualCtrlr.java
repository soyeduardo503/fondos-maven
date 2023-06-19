/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.List;

import sv.com.epsilon.presupuesto.pojo.GastoExt;

/**
 * @author martinezc
 *
 */
public class CierreMensualCtrlr {

	
	public static void appliedIncomesExpencies(List<GastoExt> list) {
		Double saldo=0.0;
		for(GastoExt g:list) {
			saldo=( g.getStatus().equals("T")?(saldo+g.getAbono()):(saldo-g.getTotal()));
			g.setSaldo(saldo);
		}
	}
}
