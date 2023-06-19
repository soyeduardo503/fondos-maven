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
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.FinanciamientoFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.MessageGrowlContext;

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
	public void reset() {//TODO delete presupuesto 1
		abono=new Financiamiento();
		abono.setAct("A");
		abono.setIdEmpresa(1);
		abono.setIdPresupuesto(new Presupuesto(sesionMB.getIdPresupuestoSelected()));
		abono.setFecha(new Date());
		abono.setFechaRegistro(new Date());
	}
	
	
	public void save() {
		try {
			this.abono.setDonador(abono.getIdCatingreso()+"");
			new FinanciamientoFacade().save(this.abono);
			
			this.reset();
			new MessageGrowlContext().send("Movimiento guardado!!!","Accion completada");
			new ExecuteForm().update("IDFormGasto");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	
}
