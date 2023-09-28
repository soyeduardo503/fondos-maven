/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Banco;
import sv.com.epsilon.entities.Catingreso;
import sv.com.epsilon.facade.BancoFacade;
import sv.com.epsilon.facade.CatingresoFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class DonantesMB extends AbstractMantto<Catingreso, CatingresoFacade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	

	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;

	
	
	

	
	public DonantesMB() {
		super(Catingreso.class,CatingresoFacade.class);
	}
	
	
	
	
	
	
//	@Override
//	public void save() {
//		try {
//			saveWithoutclose();
//			
//			this.updateDialogClose();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//

	@Override
	public void reset() {
		
		this.setItemSelected(new  Catingreso());
		setDefaultValue();
		
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nuevo Donador");
		this.setHeaderPage("Mantenimiento Donadores ");
		this.setHeaderTable("Donadores");
		this.setIdFormNew("idFormCreateDonador");
		this.setIdFormDelete("idFormDeleteCreateDonador");
		this.setIdFormList("idFormListDonador");
		this.setIdFormEdit("idFormEditDonador");
		this.setIdTable("idTablaDonador");
		this.setWtgDialog("wgtDlgDonador");
		this.setIdWtgNew("IdWgtDlgNewDonador");
		this.setDialogDelete("dltDonadorWgt");
		this.setDialogEdit("WgtEditDlgDonador");
		
		
	}

	@Override
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
		try {
			defineHeaders();
			initFacade();
			this.setList(((CatingresoFacade)getFacade()).findAll());
			
			
			//this.callLoad();
		//	defineHeaders();
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
		getItemSelected().setAct("A");
		getItemSelected().setIdEmpresa(1);
		//getItemSelected().setIdBanco(-1);
		
		
	}

	@Override
	public void validateDataValue() throws Exception {
		// TODO Auto-generated method stub
		
	}


	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}


	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}

	


	

}
