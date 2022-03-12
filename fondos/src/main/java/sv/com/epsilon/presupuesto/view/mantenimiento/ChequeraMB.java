/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Chequera;
import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.facade.ChequeraFacade;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class ChequeraMB extends AbstractMantto<Chequera, ChequeraFacade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	


	private Cuenta cuenta;
	private boolean showNew=false;
	
	
	public ChequeraMB() {
		super(Chequera.class,ChequeraFacade.class);
	}
	
	
	@Override
	public void edit(){
		
	}
	
	
	
	public void update(Chequera c) {
		try {
			initFacade();
			getFacade().persis(c);
			new MessageGrowlContext().send("Guardado!!!!", "Datos guardados correctamente!!!");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void reset() {
		
		this.setItemSelected(new Chequera());
		getItemSelected().setAct("A");
		getItemSelected().setCorrelativo(0);
		getItemSelected().setIdCuenta(cuenta!=null?cuenta.getIdCuenta():0);
		
	}
	
	@Override
	public void save() {
		getItemSelected().setCorrelativo(getItemSelected().getDesde());
		getItemSelected().setAct("A");
		getItemSelected().setIdCuenta(cuenta.getIdCuenta());
		new ExecuteForm().Update(":idChequeraNew:idChequeras");
		try {
			super.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	public void open(Cuenta c) {
		try {
			this.cuenta=c;
			initFacade();
			setList(getFacade().findAllByCount(c));
			new ExecuteForm().Update(this.getIdFormNew());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nueva chequera");
		this.setHeaderPage("Mantenimiento  Chequeras ");
		this.setHeaderTable("Chequera");
		this.setIdFormNew("idChequeraNew");
		this.setIdTable("idTableChequera");
		this.setIdWtgNew("idWtgChequera");
		this.setWtgDialog("wgtDlgChequera");
		
	}

	@Override
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
		try {
			initFacade();
			
				
			
		
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
		getItemSelected().setAct("A");
		
		
	}

	@Override
	public void validateDataValue() throws Exception {
		// TODO Auto-generated method stub
		
	}


	public Cuenta getCuenta() {
		return cuenta;
	}


	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}


	public boolean isShowNew() {
		return showNew;
	}


	public void setShowNew(boolean showNew) {
		this.showNew = showNew;
	}


	public void hidePanel() {
		String[] componentsUpdate= new String[] {"idChequeraNew:panelChequeraNuevo","idChequeraNew:btnSaveC"};
		new ExecuteForm().Update(componentsUpdate);
	}


}
