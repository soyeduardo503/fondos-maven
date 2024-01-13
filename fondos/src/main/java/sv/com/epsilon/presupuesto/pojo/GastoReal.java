/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.util.Mes;

/**
 * @author martinezc
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoReal {

	/**
	 * 
	 */
	private Mes mes;
	private Double gastoReal;
	private Double gastoPrevisto;
	

	
	
}
