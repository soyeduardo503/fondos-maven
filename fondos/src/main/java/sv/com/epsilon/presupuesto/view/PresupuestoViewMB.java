/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.LineChartModel;

import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.presupuesto.ctrlr.ChartsCtrlr;
import sv.com.epsilon.presupuesto.pojo.NodeModel;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class PresupuestoViewMB implements Serializable{

	/**
	 * 
	 */
	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB session;
	private Presupuesto presupuesto;
	private TreeNode nodo;
	private String filtro="";
	private org.primefaces.model.charts.line.LineChartModel lineModel;
	private CategoriaFacade facade= new CategoriaFacade();
	
	
	public PresupuestoViewMB() {

	}
	
	public void preRenderView(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			presupuesto= session.getPresupuestoSelected();
			presupuesto.setCategoriaList(facade.findByPresupuestoWithoutClose(presupuesto));
			crearEstructuraCompleta();
			facade.close();
			lineModel=new ChartsCtrlr().createLineYear(presupuesto);
		}
	}
	public void crearNodo(){
		presupuesto= session.getPresupuestoSelected();
		presupuesto.setCategoriaList(facade.findByPresupuesto(presupuesto));
		crearEstructuraCompleta();
		String[] componentes=new String[]{"IDFrmPresupuestos:IDPnlgPresupuestoSelected","IDFrmTableDetail:IDTreenode"};
		new ExecuteForm().Update(componentes);
	}
	
	public void close(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			facade.close();
		}
	}
	public void crearEstructuraCompleta(){
		nodo=new NodeModel().crearEstructura(presupuesto);
	}
	public void actualizarNodo(){
		if(filtro.equals("")){
			crearEstructuraCompleta();
		}else{
			crearEstructuraFiltro(filtro);
		}
		new ExecuteForm().Update("IDFrmTableDetail:IDTTDetalles");
	}
	

	private void crearEstructuraFiltro(String filtro) {
		nodo=new NodeModel().crearEstructura(presupuesto,filtro);
		System.out.println(nodo.getChildCount());
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	public TreeNode getNodo() {
		return nodo;
	}

	public void setNodo(TreeNode nodo) {
		this.nodo = nodo;
	}

	public UsuarioSessionMB getSession() {
		return session;
	}

	public void setSession(UsuarioSessionMB session) {
		this.session = session;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public org.primefaces.model.charts.line.LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(org.primefaces.model.charts.line.LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	
	
}
