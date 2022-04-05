/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Tipodesembolso;
import sv.com.epsilon.facade.TipodesembolsoFacade;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class TipoDesembolsoMB extends AbstractMantto<Tipodesembolso, TipodesembolsoFacade> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	

	
	public TipoDesembolsoMB() {
		super(Tipodesembolso.class,TipodesembolsoFacade.class);
	}
	
	public void asignarCodigo(){
		
		new ExecuteForm().Update(this.getIdFormNew()+":codigo");
	}

	@Override
	public void reset() {
		
		try {
			this.setItemSelected(this.oneNewObject());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nueva Presupuesto");
		this.setHeaderPage("Mantenimiento Presupuestos");
		this.setHeaderTable("Tabla de Presupuesto ");
		
	}

	@Override
	public void preRender() {
		try {
			if(FacesContext.getCurrentInstance().isPostback()) {
				this.callLoad();
				defineHeaders();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	



	@Override
	@PreDestroy
	public void preDestroy() {
		super.destroy();
		
	}

	public void openEditCuenta(Tipodesembolso td) {
		
	}

	@Override
	protected void setValueMod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDefaultValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateDataValue() throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
