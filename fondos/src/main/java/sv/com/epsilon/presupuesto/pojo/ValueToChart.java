/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author martinezc
 *
 */
@Data
@AllArgsConstructor
public class ValueToChart {

	/**
	 * 
	 */
	
	private String key;
	private Double value;
	public ValueToChart() {
		// TODO Auto-generated constructor stub
	}
	
	public ValueToChart(Integer id,Double value) {
		this.key=String.valueOf(id);
		this.value=value;
	}

}
