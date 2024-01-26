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
@AllArgsConstructor
 @NoArgsConstructor
public class IngresosAcumulados {

	/**
	 * 
	 */
	
	private Integer idDonador;
	private String nombreDonador;
	private Double total;
	

}
