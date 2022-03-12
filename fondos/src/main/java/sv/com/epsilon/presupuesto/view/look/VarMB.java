/**
 * 
 */
package sv.com.epsilon.presupuesto.view.look;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class VarMB {

	/**
	 * 
	 */
	private final  String PRIMARY="primary";
	private final String DANGER="danger";
	private final String INFO="info";
	private final String SUCCESS="success";
	private final String WARNING="warning";
	
	public VarMB() {
		// TODO Auto-generated constructor stub
	}

	public String getPRIMARY() {
		return PRIMARY;
	}

	public String getDANGER() {
		return DANGER;
	}

	public String getINFO() {
		return INFO;
	}

	public String getSUCCESS() {
		return SUCCESS;
	}

	public String getWARNING() {
		return WARNING;
	}
	
	

	
}
