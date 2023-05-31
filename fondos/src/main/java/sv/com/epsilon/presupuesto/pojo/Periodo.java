/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.Calendar;

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
public class Periodo {

	private Calendar inicio;
	private Calendar fin;
}
