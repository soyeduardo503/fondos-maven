/**
 * 
 */
package sv.com.epsilon.presupuesto.view.consulta;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.diagram.DefaultDiagramModel;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.ctrlr.CategoriaCtrlr;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.presupuesto.view.mantenimiento.AbstractMantto;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.GeneradorCodigo;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class ConsultaGastosMB  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	
	
	public ConsultaGastosMB() {
	}
	


	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
		
		}
	}
	
	public void cargarPresupuesto() {
		
	}
	
	
	
	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}

	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}
	
	
	

}
