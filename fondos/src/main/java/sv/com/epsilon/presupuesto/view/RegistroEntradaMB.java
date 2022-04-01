/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Financiamiento;
import sv.com.epsilon.facade.FinanciamientoFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class RegistroEntradaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4005321057957924342L;

	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB sesionMB;
	
	
	private Financiamiento abono=new Financiamiento();

	
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			reset();
		}
	}
	
	/**
	 * 
	 */
	public RegistroEntradaMB() {
		
	}
	public UsuarioSessionMB getSesionMB() {
		return sesionMB;
	}
	public void setSesionMB(UsuarioSessionMB sesionMB) {
		this.sesionMB = sesionMB;
	}
	
	
	
	public Financiamiento getAbono() {
		return abono;
	}
	public void setAbono(Financiamiento abono) {
		this.abono = abono;
	}
	public void reset() {
		abono=new Financiamiento();
		abono.setFecha(new Date());
		abono.setFechaRegistro(new Date());
	}
	
	
	public void save() {
		try {
			new FinanciamientoFacade().save(this.abono);
			this.reset();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	
}
