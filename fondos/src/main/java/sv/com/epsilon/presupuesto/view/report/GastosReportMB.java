/**
 * 
 */
package sv.com.epsilon.presupuesto.view.report;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import lombok.Data;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.presupuesto.view.PresupuestoViewMB;

/**
 * @author 50364
 *
 */
@ManagedBean
@RequestScoped
@Data
public class GastosReportMB {

	/**
	 * 
	 */
	@ManagedProperty("#{presupuestoViewMB}")
	private PresupuestoViewMB presupuestoViewMB;
	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB session;
	private List<Categoria> items=presupuestoViewMB.getListCatDist();
	
	




}
