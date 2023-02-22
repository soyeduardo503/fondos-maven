/**
 * 
 */
package sv.com.epsilon.presupuesto.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sv.com.epsilon.ctrlr.wsclient.AppCtrlr;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.ctrlr.PresupuestoCtrlr;
import sv.com.epsilon.session.Epsilon;
import sv.com.epsilon.session.pojo.SessionActiveResponse;
import sv.com.epsilon.util.Log;
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
	private Integer idPresupuestoSelected;
	private List<Presupuesto> listPresupuesto;
	
	
	
	
		
	
	public void preRender(){
		if(!FacesContext.getCurrentInstance().isPostback()) {
			
			//String token=super.getValueFromCookie("token");
			
			String	token=fromURL();
			fieldValues(token);
		
		}
	}
	
	public void fieldValues(String token) {
		System.out.println("token in other context->"+token);
		this.addValue("token", token);
		super.setCookie("token", token);
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
	
	public void isOk() {
		
		RedirectNv.goMain(getContext(),getToken());
	}
	private String fromURL() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token");
		
	}
	public void loadTokenFromURL(){
		if(!FacesContext.getCurrentInstance().isPostback()) {
			Optional<Cookie> token=super.getValueFromCookie("token");
			System.out.println("token in other context->"+(token.isPresent()?token.get():"NO_TOKEN"));			
			this.addValue("token", token);
			this.setCookie("token",token.get().getValue());
			RedirectNv.goMain(getContext(),token.get().getValue());
		}
		
	}
	
	{
		//TODO eliminar presupuesto quemado
		if(idPresupuestoSelected==null||idPresupuestoSelected==0) {
			idPresupuestoSelected=1;
		}
		presupuestoSelected=new PresupuestoFacade().findById(idPresupuestoSelected);
		this.addValue("context","fondos");
	}
	
	
	public UsuarioSessionMB() {
		try {
			Optional<Cookie> token=super.getValueFromCookie("token");
			Log.info("token found->"+(token.isPresent()?token.get():"NO_TOKEN"));
			if(token.isPresent()) {
				fieldValues(token.get().getValue());
			}
		}catch (Exception e) {
			System.out.println("token null");
		}
	}
	
	
	public void callSelectedValues() {
		
	}
	
	public String createValues() throws JsonProcessingException {
		HashMap<String,String> values=new HashMap<String, String>();
		values.put("idPresupuesto",this.idPresupuestoSelected+"");
		ObjectMapper mapper=new ObjectMapper();
		return mapper.writeValueAsString(values);
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
		int idEmpresa=0;
		if(presupuestoSelected==null) {
			
			if(getIdEmpresa()==null) {
				getValues().put("idEmpresa",1);
				idEmpresa=1;
			}				
			else
				idEmpresa=getIdEmpresa();
			presupuestoSelected=new PresupuestoCtrlr().predeterminado(idEmpresa);
		}
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
