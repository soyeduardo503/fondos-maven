/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Chequera;
import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.ChequeraFacade;
import sv.com.epsilon.facade.CuentaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class CuentaMB extends AbstractMantto<Cuenta, CuentaFacade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	


	private List<Presupuesto> listPresupuesto;
	private Presupuesto presupuestoSelected;
	private Chequera chequera=new Chequera();

	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	public CuentaMB() {
		super(Cuenta.class,CuentaFacade.class);
	}
	
	
	
	
	
	
	@Override
	public void save() {
		try {
			saveWithoutclose();
			chequera.setAct("A");
			chequera.setCorrelativo(chequera.getDesde());
			chequera.setIdCuenta(this.getItemSelected().getIdCuenta());
			new ChequeraFacade().save(chequera);
			this.updateDialogClose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void reset() {
		
		this.setItemSelected(new Cuenta());
		chequera=new Chequera();
		
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nueva Cuenta");
		this.setHeaderPage("Mantenimiento Cuentas y Chequeras ");
		this.setHeaderTable("Cuentas");
		
	}
	

	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}






	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}






	@Override
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
		try {
			initFacade();
			this.setList(getFacade().findAll());
			this.listPresupuesto=new PresupuestoFacade().findAllActive();
			if(listPresupuesto!=null&&listPresupuesto.size()>0) {
				this.presupuestoSelected=listPresupuesto.get(0);
				
			}
			//this.callLoad();
			defineHeaders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	

	@Override
	@PreDestroy
	public void preDestroy() {
		super.destroy();
		
	}

	@Override
	protected void setValueMod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDefaultValue() {
		
		
		chequera=new Chequera();
		getItemSelected().setAct("A");
		getItemSelected().setIdEmpresa(getUsuarioSessionMB().getIdEmpresa());
	}

	@Override
	public void validateDataValue() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<Presupuesto> getListPresupuesto() {
		return listPresupuesto;
	}

	public void setListPresupuesto(List<Presupuesto> listPresupuesto) {
		this.listPresupuesto = listPresupuesto;
	}

	public Presupuesto getPresupuestoSelected() {
		return presupuestoSelected;
	}

	public void setPresupuestoSelected(Presupuesto presupuestoSelected) {
		this.presupuestoSelected = presupuestoSelected;
	}


	public Chequera getChequera() {
		return chequera;
	}


	public void setChequera(Chequera chequera) {
		this.chequera = chequera;
	}

	

}
