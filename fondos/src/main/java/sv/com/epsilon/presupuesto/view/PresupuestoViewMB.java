/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.TreeNode;
import org.primefaces.model.charts.donut.DonutChartModel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.Distribution5Facade;
import sv.com.epsilon.facade.FinanciamientoFacade;
import sv.com.epsilon.presupuesto.ctrlr.ChartsCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.DataGastoPeriodCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.DrawChartCtrlr;
import sv.com.epsilon.presupuesto.pojo.DataGastoPeriod;
import sv.com.epsilon.presupuesto.pojo.Distribution;
import sv.com.epsilon.presupuesto.pojo.GastoReal;
import sv.com.epsilon.presupuesto.pojo.NodeModel;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.presupuesto.view.dashboard.ChartSaved;
import sv.com.epsilon.response.NumberResponse;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
@Slf4j
@Data
public class PresupuestoViewMB implements Serializable{

	/**
	 * 
	 */
	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB session;
	private Presupuesto presupuesto;
	
	private String filtro="";
	private org.primefaces.model.charts.line.LineChartModel lineModel;
	private CategoriaFacade facade= new CategoriaFacade();
	private List<Distribution> listDist;
	private DonutChartModel distModel;
	private Integer percentExecution;
	private List<Categoria> listCatDist;
	private DataGastoPeriod dataGasto ;
	private Double montoIngreso;
	
	
	public PresupuestoViewMB() {

	}
	
	public void preRenderView(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			presupuesto= session.getPresupuestoSelected();
			presupuesto.setCategoriaList(facade.findByPresupuestoWithoutClose(presupuesto));
			crearNodo();
			facade.close();
			
			try {
				buildCharts(presupuesto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Error en creacion de graficos");
			}
			makeModel(presupuesto.getCodigo());
			//facade.foundExecution(presupuesto.getIdPresupuesto()); 
			percentExecution=  (int) (((presupuesto.getTotal()-presupuesto.getActual())/presupuesto.getTotal())*100);
			log.info("Porcentaje ->"+percentExecution);
			NumberResponse resp = new FinanciamientoFacade().getNumber("/totalByCodPresupuesto/"+presupuesto.getCodigo());
			if(resp.getCod()==0) {
				montoIngreso=resp.getDoubleValue();
			}
		}
	}
	
	public void buildCharts(Presupuesto presupuesto) throws IOException {
		DataGastoPeriodCtrlr dataGastoCrlr=new DataGastoPeriodCtrlr();
		dataGasto= dataGastoCrlr.presupuesto(presupuesto).build();
		lineModel=dataGasto.getChart();
		try {
			new ChartSaved().buildLineChart(new DrawChartCtrlr().setPresupuesto(presupuesto, dataGastoCrlr));
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	private void makeModel(String codigo) {
		distModel= new ChartsCtrlr().createDonutModel( new Distribution5Facade().findByPresupuesto(codigo));
		
		
	}
	public void crearNodo() {
		listCatDist=presupuesto.getCategoriaList().stream().filter(cat->cat.getCodigo().length()==7).collect(Collectors.toList());;
		
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
	

//	private void crearEstructuraFiltro(String filtro) {
//		nodo=new NodeModel().crearEstructura(presupuesto,filtro);
//		System.out.println(nodo.getChildCount());
//	}

	
	
	
	


//	public TreeNode getNodo() {
//		return nodo;
//	}
//
//	public void setNodo(TreeNode nodo) {
//		this.nodo = nodo;
//	}

	public UsuarioSessionMB getSession() {
		return session;
	}

	public void setSession(UsuarioSessionMB session) {
		this.session = session;
	}

	public org.primefaces.model.charts.line.LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(org.primefaces.model.charts.line.LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public String style(Categoria cat) {
		if((cat.getMonto()-cat.getActual())<0) {
			return "danger";
		}
		int value=(int)(((cat.getMonto()-cat.getActual())/cat.getMonto())*100);
		if(value>=70)
			return "primary";
		if(value<70&&value>30)
			return "warning";
		
		return "danger";
	}
	public String icon(Categoria cat) {
		
		if((cat.getMonto()-cat.getActual())<0) {
			return "skull";
		}
		int value=(int)(((cat.getMonto()-cat.getActual())/cat.getMonto())*100);
		if(value>=70)
			return "smile-o";
		if(value<70&&value>30)
			return "meh-o";
		
		return "frown-o";
	}
	public String styleDiff(GastoReal gr) {
		Double value=gr.getGastoPrevisto()-gr.getGastoReal();
		return value>0.0?
			 "primary": "danger";
	}
	
	public String iconDiff(GastoReal gr) {
		Double value=gr.getGastoPrevisto()-gr.getGastoReal();
		return value>0.0?
			 "smile-o": "frown-o";
	}
}
