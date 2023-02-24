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
public abstract class WgtDialog<T> {
	private String id="idWgtDefault";
	private String wgt="wgtDefault";
	private Integer height=200;
	private Integer width=900;
	protected String headerText="";
	private T itemSelected;
	

	
	public abstract void define();
	
}
