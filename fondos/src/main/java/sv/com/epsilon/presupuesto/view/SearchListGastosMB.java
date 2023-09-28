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

import org.primefaces.model.charts.donut.DonutChartModel;

import lombok.Data;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.presupuesto.ctrlr.ChartsCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.pojo.DetalleGastoMes;
import sv.com.epsilon.presupuesto.pojo.SearchParameterGastoMensual;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
public class SearchListGastosMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653392476005317568L;
	/**
	 * 
	 */

	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	private SearchParameterGastoMensual search = new SearchParameterGastoMensual();

	Categoria categoria = new Categoria();

	private List<DetalleGastoMes> listDetalle = null;

	private DonutChartModel distModel=new ChartsCtrlr().createDonutModelGastosDummy();

	

	private Boolean acumulado = true;

	public SearchListGastosMB() {

	}

//	public void resetSelectables() {
//		selectables = new CategoriaFacade()
//				.findPrincipalByCodPresupuesto(usuarioSessionMB.getPresupuestoSelected().getCodigo());
//	}
	public void preRender() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			
		}
	}

	public void invocacionBusqueda(Categoria cat) {
		this.categoria=cat;
		try {
			search.setPresupuesto(usuarioSessionMB.getPresupuestoSelected());
			search.setCategoria(categoria != null && categoria.getCodigo().length()<10 ? categoria : null);				
			listDetalle = new GastoCtrlr().invocarBusqueda(search);
			distModel= new ChartsCtrlr().createDonutModelGastos(listDetalle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}

	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}

	

	

	


}
