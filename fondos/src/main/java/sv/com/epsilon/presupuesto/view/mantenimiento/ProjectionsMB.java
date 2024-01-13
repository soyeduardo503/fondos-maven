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

import lombok.Data;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Projection;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.facade.ProjectionFacade;
import sv.com.epsilon.presupuesto.ctrlr.ProjectionCtrlr;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
@Data
public class ProjectionsMB extends AbstractMantto<Projection, ProjectionFacade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	

	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;

	private Presupuesto presupuestoSelected;
	
	private boolean create=false;
	
	
	

	
	public ProjectionsMB() {
		super(Projection.class,ProjectionFacade.class);
	}
	
	
	
	
	
//	
	@Override
	public void save() {
		try {
			if(create) {
				new ProjectionCtrlr().validate(getList());
				new ProjectionFacade().save(getList());
			}
			this.updateDialogClose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new MessageGrowlContext().sendError("Error al intentar guardar lista de gastos estimados por mes!", e.getMessage(), e);
		}
		
	}


	@Override
	public void reset() {
		
		this.setItemSelected(new Projection());
		setDefaultValue();
		
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	
	public void loadData() {
		if(this.presupuestoSelected.getIdPresupuesto()!=null)
			setList( getFacade().getByPresupuesto(this.presupuestoSelected.getIdPresupuesto()));
			if(getList()==null||getList().size()==0) {
				setList(new ProjectionCtrlr().makeList(this.presupuestoSelected,usuarioSessionMB.getIdEmpresa()));
			}
	}
	
	@Override
	public void openNew() {
		boolean openDialog=false;
		if(getList()==null||getList().size()==0) {
			
			openDialog=true;
			create=true;
		}
		loadData();
		if(openDialog)
			super.openNew();
		
		
	}
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nueva proyeccion");
		this.setHeaderPage("Mantenimiento Proyecciones ");
		this.setHeaderTable("Proyeccion");
		this.setIdFormNew("idFormCreateProjection");
		this.setIdFormDelete("idFormDeleteCreateProjection");
		this.setIdFormList("idFormListProjection");
		this.setIdFormEdit("idFormEditProjection");
		this.setIdTable("idTablaProjection");
		this.setWtgDialog("wgtDlgProjection");
		this.setIdWtgNew("IdWgtDlgNewProjection");
		this.setDialogDelete("dltProjectionWgt");
		this.setDialogEdit("WgtEditDlgProjection");
		
		
	}

	@Override
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
		try {
			defineHeaders();
			initFacade();
			if(usuarioSessionMB!=null&&usuarioSessionMB.getIdPresupuestoSelected()!=null) {
				this.setList(getFacade().getByPresupuesto(usuarioSessionMB.getIdPresupuestoSelected()));
				this.presupuestoSelected=new PresupuestoFacade().findById(usuarioSessionMB.getIdPresupuestoSelected());
			}
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
		getItemSelected().setIdPresupuesto(getIdEmpresa());
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
