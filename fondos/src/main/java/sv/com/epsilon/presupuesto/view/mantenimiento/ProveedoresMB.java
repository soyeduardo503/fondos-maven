/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class ProveedoresMB extends AbstractMantto<Proveedor, ProveedorFacade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{usuarioSessionMB}")	
	private UsuarioSessionMB sesionMB;

	
	
	/**
	 * 
	 */
	
	
	
	
	
	
	public ProveedoresMB() {
		super(Proveedor.class,ProveedorFacade.class);
		
	}
	
	
	
	
	public UsuarioSessionMB getSesionMB() {
		return sesionMB;
	}




	public void setSesionMB(UsuarioSessionMB sesionMB) {
		this.sesionMB = sesionMB;
	}




	@Override
	public void edit(){
		
	}
	
	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nuevo proveedor");
		this.setHeaderPage("Mantenimiento Proveedores");
		this.setHeaderTable("Proveedor");
		
	}

	@Override
	public void preRender() {
		try {
			if(!FacesContext.getCurrentInstance().isPostback()) {
				this.callLoad();
				defineHeaders();
				setDefaultValue();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void same() {
		getItemSelected().setNombreLegal(getItemSelected().getNombre());
	}
	
	@Override
	public void save() {
		if(getItemSelected().getIdProveedor()==null || getItemSelected().getIdProveedor()==0)
			setDefaultValue();
		else
			setValueMod();
		try {
			super.save();
			showSuccess();
		} catch (Exception e) {
			new MessageGrowlContext().sendError("Error al guardar", "No se pudo guardar el proveedor", e);
		}
	}
	@Override
	protected void setValueMod() {
		
		this.getItemSelected().setIdUSuarioModifico(sesionMB.getIdRol());
	}

	@Override
	protected void setDefaultValue() {
		if(sesionMB!=null) {
			//TODO implement user
			this.getItemSelected().setIdEmpresa(sesionMB.getIdEmpresa());
			this.getItemSelected().setFechaIngreso(new Date());
			//this.getItemSelected().setIdUsuarioCreo(sesionMB.getIdUser());
		}
	}

	@Override
	@PreDestroy
	public void preDestroy() {
		super.destroy();
		
	}


	@Override
	public void reset() {
		try {
			this.setItemSelected(this.oneNewObject());
			this.getItemSelected().setTipo("N");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}




	@Override
	public void validateDataValue() throws Exception {
		
		
	}

	

}
