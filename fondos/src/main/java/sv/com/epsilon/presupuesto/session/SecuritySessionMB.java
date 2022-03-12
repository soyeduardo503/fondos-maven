/**
 * 
 */
package sv.com.epsilon.presupuesto.session;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.ctrlr.wsclient.AppCtrlr;
import sv.com.epsilon.session.pojo.SistemaResponse;
import sv.com.epsilon.util.RedirectNv;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class SecuritySessionMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1688987185114305133L;
	/**
	 * 
	 */
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	public SecuritySessionMB() {
	
	}
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			if(usuarioSessionMB.getToken()!=null) {
				redirect();
			}
		}
	}
	
	public void redirect() {
		try {
			SistemaResponse sistema = new AppCtrlr().obtenerPagePrincipal(usuarioSessionMB.getContext(), usuarioSessionMB.getToken());
			new RedirectNv().Redirect(sistema.getPagePrincipal());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}

	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}
	
	

}
