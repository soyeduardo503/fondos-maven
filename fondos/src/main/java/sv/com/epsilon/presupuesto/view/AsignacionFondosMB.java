/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.TreeNode;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.ctrlr.AsignacionCtrlr;
import sv.com.epsilon.presupuesto.pojo.NodeModel;
import sv.com.epsilon.presupuesto.pojo.ViewNode;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class AsignacionFondosMB {

	@ManagedProperty(value = "#{sesionMB}")
	private UsuarioSessionMB sesionMB;
	private Categoria itemSelected;
	private Presupuesto presupuesto;
	//private TreeNode nodo;
	private List<Presupuesto> list=new PresupuestoFacade().findAllActive();
	private  List<Categoria> allCategoria;
	private String wgtSelectPresupuesto="wgtDialogSelect";
	private String idFormSelectPresupuesto="wgtDialogSelect";
	private String idFormFondos="idFondos";
	private BigDecimal  asignable=new BigDecimal(0);
	private String percentAsignable="100";
	private String codSelected="N/F";
	
	
	
	
	/**
	 * 
	 */
	public AsignacionFondosMB() {
		
	}
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			cargarPresupuesto();
		}
	}
	public void reloadView() {
		this.cargarPresupuesto();
	}
	
	public void reload() {
		cargarPresupuesto();
	}
	private void cargarPresupuesto() {
		
//			if(sesionMB.getPresupuestoSelected()!=null) {
//				presupuesto=sesionMB.getPresupuestoSelected();
//			}else
			if(presupuesto==null)
				presupuesto=list.size()>0?list.get(0):null;
				
			if(presupuesto!=null) {
				CategoriaFacade facade=new CategoriaFacade();
				//presupuesto= sesionMB.getPresupuestoSelected();
				allCategoria=facade.findByCodPresupuesto(presupuesto);
				//crearEstructuraCompleta();
				//facade.close();
				
			}
		
		
	}
	
	
	
	public String styleCell(String cod) {
		//21FPT01
		
		if(cod.contains(codSelected)) {
			if(cod.equals(codSelected))				
				return "focusRow";
			else {
				if(cod.length()==codSelected.length()+2)
				return "focusRowSon";
			}
		}
		return null;
	}
	
//	public void crearNodo(){
//		presupuesto= sesionMB.getPresupuestoSelected();
//		CategoriaFacade facade=new CategoriaFacade();
//		presupuesto.setCategoriaList(facade.findByPresupuesto(presupuesto));
//		crearEstructuraCompleta();
//		String[] componentes=new String[]{"IDFrmPresupuestos:IDPnlgPresupuestoSelected","IDFrmTableDetail:IDTreenode"};
//		new ExecuteForm().Update(componentes);
//	}
	
	public void asignarCodigo(Categoria c) {
		this.itemSelected=c;
		
		this.codSelected=c.getCodigo().substring(0, c.getCodigo().length()-2);
		new ExecuteForm().update(":IDFrmTableDetail:detalleAsignacionFondo");
	}
	
	public void calcularAsignanablePresupuesto() {
		
		this.asignable=new BigDecimal( AsignacionCtrlr.disponible(presupuesto));
		percentAsignable=""+new AsignacionCtrlr().percentComplete(presupuesto, asignable.doubleValue());
	}
	
	public void validar() {
		
	}

	
	
	public List<Categoria> getAllCategoria() {
		return allCategoria;
	}

	public void setAllCategoria(List<Categoria> allCategoria) {
		this.allCategoria = allCategoria;
	}

	public String getCodSelected() {
		return codSelected;
	}

	public void setCodSelected(String codSelected) {
		this.codSelected = codSelected;
	}

	public List<Presupuesto> getList() {
		return list;
	}

	public void setList(List<Presupuesto> list) {
		this.list = list;
	}

	public UsuarioSessionMB getSesionMB() {
		return sesionMB;
	}
	public void setSesionMB(UsuarioSessionMB sesionMB) {
		this.sesionMB = sesionMB;
	}
//	public void crearEstructuraCompleta(){
//		nodo=new NodeModel().crearEstructura(presupuesto);
//	}

	public Categoria getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(Categoria itemSelected) {
		this.itemSelected = itemSelected;
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

//	public TreeNode getNodo() {
//		return nodo;
//	}
//
//	public void setNodo(TreeNode nodo) {
//		this.nodo = nodo;
//	}

	public String getWgtSelectPresupuesto() {
		return wgtSelectPresupuesto;
	}

	public void setWgtSelectPresupuesto(String wgtSelectPresupuesto) {
		this.wgtSelectPresupuesto = wgtSelectPresupuesto;
	}

	public String getIdFormSelectPresupuesto() {
		return idFormSelectPresupuesto;
	}

	public void setIdFormSelectPresupuesto(String idFormSelectPresupuesto) {
		this.idFormSelectPresupuesto = idFormSelectPresupuesto;
	}

	public String getIdFormFondos() {
		return idFormFondos;
	}

	public void setIdFormFondos(String idFormFondos) {
		this.idFormFondos = idFormFondos;
	}

	public BigDecimal getAsignable() {
		return asignable;
	}

	public void setAsignable(BigDecimal asignable) {
		this.asignable = asignable;
	}

	public String getPercentAsignable() {
		return percentAsignable;
	}

	public void setPercentAsignable(String percentAsignable) {
		this.percentAsignable = percentAsignable;
	}
	
	
	
	
	

}
