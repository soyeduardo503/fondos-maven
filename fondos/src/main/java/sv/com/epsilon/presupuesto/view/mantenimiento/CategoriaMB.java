/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.diagram.DefaultDiagramModel;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.ctrlr.CategoriaCtrlr;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.GeneradorCodigo;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class CategoriaMB extends AbstractMantto<Categoria, CategoriaFacade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	private GeneradorCodigo generardorCodigo=new GeneradorCodigo();
	
	private DefaultDiagramModel diagram;
	private List<Presupuesto> listPresupuesto;
	private Presupuesto presupuestoSelected;
	private String formCarga="loadCategoriasFromFile";
	
	public CategoriaMB() {
		super(Categoria.class,CategoriaFacade.class);
	}
	
	public void asignarCodigo(){
		generardorCodigo.makeCode(this.getItemSelected());
		new ExecuteForm().Update(this.getIdFormNew()+":codigo");
	}
	@Override
	public void edit(){
		
	}
	public void crearCategoria(Categoria cPadre){
		
		this.getItemSelected().setIdCategoriaPadre(cPadre);
		this.getItemSelected().setAct("A");
		this.getItemSelected().setActual(this.getItemSelected().getMonto());
	}
	
	

	public DefaultDiagramModel getDiagram() {
		return diagram;
	}

	public void setDiagram(DefaultDiagramModel diagram) {
		this.diagram = diagram;
	}

	@Override
	public void reset() {
		
		this.setItemSelected(new Categoria());
		
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	public void openDiagram(Categoria cat) {
		getFacade().findListSubCategoriaByCod(cat);
		diagram=new CategoriaCtrlr().createDiagram(cat);
	}
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nueva Categoria");
		this.setHeaderPage("Mantenimiento Categorias (Articulos,Tipo de objeto, Clase )");
		this.setHeaderTable("Tipo de Categoria productos");
		
	}

	@Override
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
		try {
			PresupuestoFacade facade= new PresupuestoFacade();
			//facade.init(usuarioSessionMB.getToken(), usuarioSessionMB.getIdEmpresa());
			this.listPresupuesto=facade.findAllActive();
			if(listPresupuesto!=null&&listPresupuesto.size()>0) {
				this.presupuestoSelected=listPresupuesto.get(0);
				loadCategories();
			}
			//this.callLoad();
			defineHeaders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void cargarPresupuesto() {
		
	}
	
	public void loadCategories() {
		if(presupuestoSelected!=null) {
			try {
				initFacade();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setList(this.getFacade().findByCodPresupuesto(presupuestoSelected));
		}
	}
	
	public void callList(Categoria cat) {
		this.getFacade().findListSubCategoria(cat);
	}
	
	public Integer countSubs(Categoria cat) {
		return this.getFacade().CountBySubCategoria(cat);
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

	public List<Presupuesto> getListPresupuesto() {
		return listPresupuesto;
	}

	public void setListPresupuesto(List<Presupuesto> listPresupuesto) {
		this.listPresupuesto = listPresupuesto;
	}

	public Presupuesto getPresupuestoSelected() {
		return presupuestoSelected;
	}

	public void setPresupuestoSelected(Presupuesto presupuestoSelected) {
		this.presupuestoSelected = presupuestoSelected;
	}

	public void cargarArchivo() {
		
	}

	public String getFormCarga() {
		return formCarga;
	}

	public void setFormCarga(String formCarga) {
		this.formCarga = formCarga;
	}

	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}

	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}
	
	
	

}
