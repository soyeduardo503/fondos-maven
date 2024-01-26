/**
 * 
 */
package sv.com.epsilon.presupuesto.view.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import sv.com.epsilon.entities.Catingreso;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CatingresoFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
@Data
public class ListaCatingresoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	private List<Presupuesto> listPresupuestos=new PresupuestoFacade().findAllActive();
	
	
	
	private CatingresoFacade facade=new CatingresoFacade();
	private List<Catingreso> list=new ArrayList<>();


	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback())
			this.load();
	}
	
	public ListaCatingresoMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void load() {
		list=facade.getList("/idPresupuesto/"+1);
	}

	public List<Catingreso> getList() {
		return list;
	}

	public void setList(List<Catingreso> list) {
		this.list = list;
	}

	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}

	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}

	public Catingreso find(Integer idCatingreso) {
		return facade.findById(idCatingreso);
	}
	
}
