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

import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.facade.CuentaFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.Log;
/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class ListaCuentasMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	private CuentaFacade facade=new CuentaFacade();
	private List<Cuenta> list;
	public ListaCuentasMB() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			facade.init(usuarioSessionMB.getToken(), usuarioSessionMB.getIdEmpresa());
			list=load();
		}
	}
	public List<Cuenta> load() {
		try {
			return facade.findAllActiveByEmpresa();
		}catch (Exception e) {
			Log.error(e,"Error en carga de cuentas");
			return new ArrayList<Cuenta>();
		}
		
	}
	public List<Cuenta> getList() {
		return list;
	}
	public void setList(List<Cuenta> list) {
		this.list = list;
	}
	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}
	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}

	
	
	
}
