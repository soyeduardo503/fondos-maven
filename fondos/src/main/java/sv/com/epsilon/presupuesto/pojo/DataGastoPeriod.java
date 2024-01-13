/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.HashMap;
import java.util.List;

import org.primefaces.model.charts.line.LineChartModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.entities.Cierre;
/**
 * @author martinezc
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGastoPeriod {

	/**
	 * 
	 */
	
	private LineChartModel chart;
	private List<Cierre> listCierre;
	private List<GastoReal> list;
	
	

}
