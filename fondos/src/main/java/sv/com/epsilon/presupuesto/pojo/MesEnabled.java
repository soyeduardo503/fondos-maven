/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import lombok.Data;
import sv.com.epsilon.util.Mes;

/**
 * @author martinezc
 *
 */
@Data
public class MesEnabled extends Mes {

	/**
	 * 
	 */
	
	
	private Boolean enabled=false;
	private String icon="eye-slash";
	
	
	public MesEnabled(Mes m) {
		super(m.getIdMes());
		this.setNombre(m.getNombre());
		this.setValor(m.getValor());
		
	}
	
	public MesEnabled setEnabled(boolean b) {
		this.enabled=b;
		icon=b?"eye":"eye-slash";		
		return this;
	}
	
	public Mes cast() {
		return this;
	}
}
