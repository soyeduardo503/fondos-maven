/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.entities.Gasto;

/**
 * @author martinezc
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Top5Gasto {

	private String description;
	
	private Double value;
	
	public Top5Gasto(Gasto gasto) {
		this.value=gasto.getTotal();
		this.description=gasto.getDescripcion();
	}
}
 