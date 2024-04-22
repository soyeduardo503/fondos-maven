/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.PresupuestoFacade;

/**
 * 
 */
@ManagedBean
@ViewScoped
@Data
public class ListaPresupuestosMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3629097315420908488L;
	/**
	 * 
	 */
	private List<Presupuesto> list=init();
	public ListaPresupuestosMB() {
		
	}

	
	public List<Presupuesto> init() {
		
			list=new PresupuestoFacade().findAllActive();
			list.sort(new Comparator<Presupuesto>() {
				public int compare(Presupuesto o1, Presupuesto o2) {
					return o2.getYear().compareTo(o1.getYear());
				}
			});
		return list;
	}
}
