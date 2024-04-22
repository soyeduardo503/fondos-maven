/**
 * 
 */
package sv.com.epsilon.presupuesto.view.dashboard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.facade.Top5Facade;
import sv.com.epsilon.presupuesto.pojo.PresupuestoDashboard;
import sv.com.epsilon.presupuesto.pojo.Top5Gasto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
@Data
public class DashboardPrincipalMB {

	/**
	 * 
	 */

	private List<PresupuestoDashboard> list;
	private List<Top5Gasto> listTop5Gasto;
	
	private List<Top5Gasto> listTop5GastoMes;
	
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB sessionMb; 
	
	
	
	public DashboardPrincipalMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void preRender() {
		
		if(!FacesContext.getCurrentInstance().isPostback()) {
			List<Presupuesto> list;
			this.list=new ArrayList<PresupuestoDashboard>();
			 list=new PresupuestoFacade().findAllActive();
			 for(Presupuesto p:list) {
				PresupuestoDashboard pd=new PresupuestoDashboard();
				pd.setPresupuesto(p);
				double gastado=(p.getTotal()-p.getActual());
				double porcentaje=(gastado/p.getTotal())*100;
				pd.setPercent(new  BigDecimal(porcentaje).intValue());
				this.list.add(pd);
			}
			 callTop5();
		}
		
		
	}
	
	public void callTop5() {
		Optional<Presupuesto> presupuesto = new PresupuestoFacade().defaultValue();
		int idSelected=1;
		if(presupuesto.isPresent()) {
			idSelected=presupuesto.get().getIdPresupuesto();
		}
		listTop5Gasto=new Top5Facade().findByPresupuesto(idSelected);
		listTop5GastoMes=new Top5Facade().findByPresupuestoMensual(idSelected);
	}
	
	
	
	public void selectPresupuesto(Presupuesto p) {
		this.sessionMb.setPresupuestoSelected(p);
		
	}
	
	
	

	
}
