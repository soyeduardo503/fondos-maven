/**
 * 
 */
package sv.com.epsilon.presupuesto.view.dashboard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.pojo.PresupuestoDashboard;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class DashboardPrincipalMB {

	/**
	 * 
	 */

	private List<PresupuestoDashboard> list;
	
	@ManagedProperty(value = "#{sesionMB}")
	private UsuarioSessionMB sessionMb; 
	
	
	
	public DashboardPrincipalMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void preRender() {
		List<Presupuesto> list;
		if(!FacesContext.getCurrentInstance().isPostback()) {
			this.list=new ArrayList<PresupuestoDashboard>();
			 list=new PresupuestoFacade().findAllActive();
			 for(Presupuesto p:list) {
				PresupuestoDashboard pd=new PresupuestoDashboard();
				pd.setPresupuesto(p);
				double gastado=(p.getTotal()-p.getActual());
				double porcentaje=(gastado/p.getTotal())*100;
				double disponible=100-porcentaje;
				pd.setPercent(new  BigDecimal(disponible).intValue());
				this.list.add(pd);
			}
		}
		
		
	}
	public void selectPresupuesto(Presupuesto p) {
		this.sessionMb.setPresupuestoSelected(p);
		
	}
	
	

	public List<PresupuestoDashboard> getList() {
		return list;
	}

	public void setList(List<PresupuestoDashboard> list) {
		this.list = list;
	}

	public UsuarioSessionMB getSessionMb() {
		return sessionMb;
	}

	public void setSessionMb(UsuarioSessionMB sessionMb) {
		this.sessionMb = sessionMb;
	}

	
}
