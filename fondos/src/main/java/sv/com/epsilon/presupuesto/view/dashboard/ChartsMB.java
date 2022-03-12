/**
 * 
 */
package sv.com.epsilon.presupuesto.view.dashboard;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.MeterGaugeChartModel;

import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.presupuesto.pojo.ChartModel;

/**
 * @author usuario07
 *
 */
@ManagedBean
@RequestScoped
public class ChartsMB implements Serializable {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 4163383402259288889L;

	/**
	 * 
	 */
	public ChartsMB() {
		
	}
	
	public MeterGaugeChartModel getModel(Presupuesto presupuesto){
		return new ChartModel().createMeterGaugeModels(presupuesto);
	}
	
	

	public Integer valuePresupuestoDisponible(Presupuesto presupuesto) {
		double gastado=(presupuesto.getTotal()-presupuesto.getActual());
		double porcentaje=(gastado/presupuesto.getTotal())*100;
		double disponible=100-porcentaje;
		return new BigDecimal(disponible).intValue();
	}


}
