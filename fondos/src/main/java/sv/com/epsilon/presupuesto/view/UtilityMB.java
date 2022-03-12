/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import sv.com.epsilon.presupuesto.ctrlr.AsignacionCtrlr;
import sv.com.epsilon.presupuesto.pojo.ViewNode;

/**
 * @author 50364
 *
 */
@ManagedBean
@RequestScoped
public class UtilityMB {

	/**
	 * 
	 */
	public UtilityMB() {
		
	}
	public int percentDad(ViewNode nodo) {
		return AsignacionCtrlr.calcPercent(nodo.getMontoPadre(), nodo.getTotal());
	}
	
	public void asignado(ViewNode nodo,DefaultTreeNode tree) {
		
	}
	

	
}
