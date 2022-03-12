/**
 * 
 */
package sv.com.epsilon.presupuesto.session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.ctrlr.wsclient.AppCtrlr;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.SessionActive;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.session.Epsilon;
import sv.com.epsilon.session.pojo.SessionActiveRequest;
import sv.com.epsilon.session.pojo.SessionActiveResponse;
import sv.com.epsilon.util.RedirectNv;

/**
 * @author Zeta
 *
 */
@ManagedBean
@SessionScoped
public class UsuarioSessionMB extends Epsilon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8513779293499728415L;
	/**
	 * 
	 */
	private Categoria categoriaSelected;
	private Presupuesto presupuestoSelected;
	private Presupuesto presupuestoSelectedDlg;
	private List<Presupuesto> listPresupuesto;
	
	
	
	
		
	
	public void preRender(){
		if(!FacesContext.getCurrentInstance().isPostback()) {
			
			//String token=super.getValueFromCookie("token");
			
			String	token=fromURL();
			System.out.println("token in other context->"+token);
			this.addValue("token", token);
			if(token!=null) {
				 SessionActiveResponse active;
				try {
					active = new AppCtrlr().findSessionActive(token,"");
					 if( getValue("context").equals(active.getContext())){
						 this.addValue("idRol",active.getIdRol());
						 this.addValue("user", active.getUser());
						 this.addValue("idUser", active.getIdUser());
						 this.addValue("idEmpresa", active.getIdEmpresa());
					 }
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				 
				 
			}
		
		}
	}
	
	public void isOk() {
		
		RedirectNv.goMain(getContext(),getToken());
	}
	private String fromURL() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token");
		
	}
	public void loadTokenFromURL(){
		if(!FacesContext.getCurrentInstance().isPostback()) {
			String token=super.getValueFromCookie("token");
			System.out.println("token in other context->"+token);
			this.addValue("token", token);
			RedirectNv.goMain(getContext(),token);
		}
		
	}
	
	{
		presupuestoSelected=new PresupuestoFacade().findById(1);
		this.addValue("context","eMoney");
	}
	
	
	public UsuarioSessionMB() {
		
	}
	
	
//	public void login() throws Exception {
//		
//		
//			
//			RedirectNv.goMain();
//		
//	}
	
	public void loadPresupuestos() {
		listPresupuesto=new PresupuestoFacade().findAllActive();
	}
	
	public void asignarSelected() {
		this.presupuestoSelected=presupuestoSelectedDlg;
	}
	
	public void logout() {
		
		setValido(false);
		
	}


	
	@javax.annotation.PreDestroy
	public void PreDestroy() {
		
		this.setValido(false);
	}


	


	public Categoria getCategoriaSelected() {
		return categoriaSelected;
	}


	public void setCategoriaSelected(Categoria categoriaSelected) {
		this.categoriaSelected = categoriaSelected;
	}


	public Presupuesto getPresupuestoSelected() {
		return presupuestoSelected;
	}


	public void setPresupuestoSelected(Presupuesto presupuestoSelected) {
		this.presupuestoSelected = presupuestoSelected;
	}


	

	


	public List<Presupuesto> getListPresupuesto() {
		return listPresupuesto;
	}


	public void setListPresupuesto(List<Presupuesto> listPresupuesto) {
		this.listPresupuesto = listPresupuesto;
	}


	public Presupuesto getPresupuestoSelectedDlg() {
		return presupuestoSelectedDlg;
	}


	public void setPresupuestoSelectedDlg(Presupuesto presupuestoSelectedDlg) {
		this.presupuestoSelectedDlg = presupuestoSelectedDlg;
	}
	
	
	 

	

	
	


}
