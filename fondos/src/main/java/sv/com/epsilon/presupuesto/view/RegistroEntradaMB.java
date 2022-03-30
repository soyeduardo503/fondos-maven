/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.presupuesto.ctrlr.AsignacionCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.DisponibleCtrlr;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Financiamiento;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

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
	}
		
	
	
}
