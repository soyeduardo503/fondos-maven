/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Tablagasto;
import sv.com.epsilon.facade.TablagastoFacade;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class TablaGastoMB extends AbstractMantto<Tablagasto, TablagastoFacade> implements Serializable {

	/**
	 * 
	 */
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3836690686131338480L;


	/**
	 * 
	 */
	
	
	
	public TablaGastoMB() {
		super(Tablagasto.class,TablagastoFacade.class);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nuevo Gasto");
		this.setHeaderPage("Mantenimiento Gasto");
		this.setHeaderTable("Tabla de catalogo de gastos ");
		
	}

	@Override
	public void preRender() {
		try {
			if(!FacesContext.getCurrentInstance().isPostback()) {
				this.callLoad();
				defineHeaders();
			}
		} catch (Exception e) {
	
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateDataValue() throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
