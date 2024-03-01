/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author martinezc
 *
 */
@Data
public class SerieData {

	/**
	 * 
	 */
	private String serieName;
	private List<ValueToChart> values;
	
	public SerieData() {
		// TODO Auto-generated constructor stub
	}

	public SerieData(ArrayList<ValueToChart> ar) {
		values=ar;
	}

}
