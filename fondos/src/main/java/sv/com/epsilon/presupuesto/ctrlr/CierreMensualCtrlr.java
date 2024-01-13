/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
			saldo=( g.getStatus().equals("T")?( saldo+ new BigDecimal( g.getAbono()).setScale(2,RoundingMode.HALF_UP).doubleValue()):
				(saldo-new BigDecimal( g.getTotal()).setScale(2,RoundingMode.HALF_UP).doubleValue()));
			saldo=new BigDecimal(saldo).setScale(2,RoundingMode.HALF_UP).doubleValue();
			g.setSaldo(saldo);
		}
	}
}
