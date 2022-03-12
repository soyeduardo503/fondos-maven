/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import sv.com.epsilon.entities.Presupuesto;

/**
 * @author 50364
 *
 */
public class PresupuestoDashboard {

	/**
	 * 
	 */
	private Presupuesto presupuesto;
	private Integer percent;
	
	public PresupuestoDashboard() {
		
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	
}
