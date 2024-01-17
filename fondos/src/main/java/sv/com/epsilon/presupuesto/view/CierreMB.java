/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import sv.com.epsilon.entities.Cierre;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CierreFacade;
import sv.com.epsilon.presupuesto.ctrlr.PresupuestoCtrlr;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
public class CierreMB implements Serializable {

	
	private Presupuesto presupuestoSelected;
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	List<Cierre> list;
	
	private String form="idFormCierre";
	private String title;
	private String wgt="WgtDlgCierre";
	
	/**
	 * 
	 */
	
	public CierreMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			presupuestoSelected=new PresupuestoCtrlr().predeterminado(usuarioSessionMB.getIdEmpresa());
		}
	}
	
	
	
	public void load() {
		presupuestoSelected= usuarioSessionMB.getPresupuestoSelected();
		title="Cierres del presupuesto "+presupuestoSelected.getNombrePresupuesto();
		list=new CierreFacade().getList("/all/byPresupuesto/"+presupuestoSelected.getIdPresupuesto());
	}

}
