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
public class CountAcount {

	/**
	 * 
	 */
	
	private String code;
	private Integer count;
	public CountAcount(String code, Integer count) {
		super();
		this.code = code;
		this.count = count;
	}
	public CountAcount(String code, Long count) {
		super();
		this.code = code;
		this.count = count.intValue();
	}

	
}
