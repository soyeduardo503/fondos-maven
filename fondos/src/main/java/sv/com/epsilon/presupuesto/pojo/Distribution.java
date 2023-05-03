/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author martinezc
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distribution {

	private String codigo;
	private String description;
	private Integer value;
}
