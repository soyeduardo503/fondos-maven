/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.TreeNode;
import org.primefaces.model.charts.donut.DonutChartModel;

import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.Distribution5Facade;
import sv.com.epsilon.presupuesto.ctrlr.ChartsCtrlr;
import sv.com.epsilon.presupuesto.pojo.Distribution;
import sv.com.epsilon.presupuesto.pojo.NodeModel;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
@Slf4j
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
	private List<Distribution> listDist;
	private DonutChartModel distModel;
	private Integer percentExecution;
	private List<Categoria> listCatDist;
	
	
	public PresupuestoViewMB() {

	}
	
	public void preRenderView(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			presupuesto= session.getPresupuestoSelected();
			presupuesto.setCategoriaList(facade.findByPresupuestoWithoutClose(presupuesto));
			crearNodo();
			facade.close();
			lineModel=new ChartsCtrlr().createLineYear(presupuesto);
			makeModel(presupuesto.getCodigo());
			//facade.foundExecution(presupuesto.getIdPresupuesto()); 
			percentExecution=  (int) (((presupuesto.getTotal()-presupuesto.getActual())/presupuesto.getTotal())*100);
			log.info("Porcentaje ->"+percentExecution);
		}
	}
	private void makeModel(String codigo) {
		distModel= new ChartsCtrlr().createDonutModel( new Distribution5Facade().findByPresupuesto(codigo));
		
		
	}
	public void crearNodo() {
		listCatDist=presupuesto.getCategoriaList().stream().filter(cat->cat.getCodigo().length()==7).toList();
		log.info(""+listCatDist.size());
	}
	
//	public void crearNodoDeprecade(){
//		presupuesto= session.getPresupuestoSelected();
//		presupuesto.setCategoriaList(facade.findByPresupuesto(presupuesto));
//		crearEstructuraCompleta();
//		String[] componentes=new String[]{"IDFrmPresupuestos:IDPnlgPresupuestoSelected","IDFrmTableDetail:IDTreenode"};
//		new ExecuteForm().Update(componentes);
//	}
	
	public void close(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			facade.close();
		}
	}
//	public void crearEstructuraCompleta(){
//		nodo=new NodeModel().crearEstructura(presupuesto);
//	}
//	public void actualizarNodo(){
//		if(filtro.equals("")){
//			crearEstructuraCompleta();
//		}else{
//			crearEstructuraFiltro(filtro);
//		}
//		new ExecuteForm().Update("IDFrmTableDetail:IDTTDetalles");
//	}
	

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

	
	
	

	public List<Categoria> getListCatDist() {
		return listCatDist;
	}

	public void setListCatDist(List<Categoria> listCatDist) {
		this.listCatDist = listCatDist;
	}

	public Integer getPercentExecution() {
		return percentExecution;
	}

	public void setPercentExecution(Integer percentExecution) {
		this.percentExecution = percentExecution;
	}

	public DonutChartModel getDistModel() {
		return distModel;
	}

	public void setDistModel(DonutChartModel distModel) {
		this.distModel = distModel;
	}

	public List<Distribution> getListDist() {
		return listDist;
	}

	public void setListDist(List<Distribution> listDist) {
		this.listDist = listDist;
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

	public String style(Categoria cat) {
		int value=(int)(((cat.getMonto()-cat.getActual())/cat.getMonto())*100);
		if(value>=70)
			return "primary";
		if(value<70&&value>30)
			return "warning";
		
		return "danger";
	}
	public String icon(Categoria cat) {
		int value=(int)(((cat.getMonto()-cat.getActual())/cat.getMonto())*100);
		if(value>=70)
			return "smile-o";
		if(value<70&&value>30)
			return "meh-o";
		
		return "frown-o";
	}
	
	
}
