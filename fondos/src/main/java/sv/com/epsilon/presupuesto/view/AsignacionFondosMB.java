/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.ctrlr.AsignacionMontosCtrlr;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class AsignacionFondosMB {

	@ManagedProperty(value = "#{usuarioSessionMB}")
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
	private List<Categoria> listModified;
	private boolean showAllCat=false;
	
	private Categoria catModSelected;
	private Double total=0.0;
	
	/**
	 * 
	 */
	public AsignacionFondosMB() {
		
	}
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			if(sesionMB.getIdPresupuestoSelected()!=null) {
				presupuesto=sesionMB.getPresupuestoSelected();
				presupuesto=new PresupuestoFacade().findById(presupuesto.getIdPresupuesto());
			}
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
				allCategoria = facade.findByCodPresupuesto7(presupuesto);
				this.total=presupuesto.getTotal();
				
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
		//this.catModSelected=c;
		this.codSelected=c.getCodigo().substring(0, c.getCodigo().length()-2);
		new ExecuteForm().update(":IDFrmTableDetail:detalleAsignacionFondo");
	}
	
	
	
//	public void calcularAsignanablePresupuesto() {
//		
//		this.asignable=new BigDecimal( AsignacionCtrlr.disponible(presupuesto));
//		percentAsignable=""+new AsignacionCtrlr().percentComplete(presupuesto, asignable.doubleValue());
//	}
	
	
	
	
	public boolean isShowAllCat() {
		return showAllCat;
	}

	public void setShowAllCat(boolean showAllCat) {
		this.showAllCat = showAllCat;
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
	
	public void tempCategoriaSelected(Categoria cat) {
		this.catModSelected=cat;
	}
	
	
	public void refactorAmount(Categoria catModSelected) {
	  AsignacionMontosCtrlr.calcularMontos(allCategoria, catModSelected, presupuesto);
	  total=presupuesto.getTotal();
	  new ExecuteForm().update(":IDFrmTableDetail:detalleAsignacionFondo");
	  new MessageGrowlContext().send("Presupuesto modificado ", "total $ "+presupuesto.getTotal());
	}
	
	public void save() {
		try {
			this.presupuesto.setTotal(total);
			new AsignacionMontosCtrlr().save(presupuesto, allCategoria);
		} catch (Exception e) {
			new MessageGrowlContext().sendError("Error en guardar asignacion", e.getMessage(), e);
			
		}
		new MessageGrowlContext().send("Informacion guardada!!!", "Registros guardados");
	}
	
	public Integer percent(Categoria c) {
		return AsignacionMontosCtrlr.calcularPorcentaje( c, presupuesto);
	}
	

	
	
	

}
