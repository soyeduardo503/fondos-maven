/**
 * 
 */
package sv.com.epsilon.presupuesto.view.listas;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.util.Mes;

/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
public class ListaMesesMB {

	
	public List<Mes> getListUntilCurrentMonth(){
		return Mes.untilCurrentMonth();
	}
	
	public List<Mes> getAll(){
		return Mes.all();
	}
	
	
	
	
}
