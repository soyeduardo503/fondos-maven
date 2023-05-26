/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import sv.com.epsilon.presupuesto.pojo.GastoExt;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
public class SearchRetencionMB {

	@ManagedProperty(value = "#{searchMovimientoMB}")
	private SearchMovimientoMB seachMovs;
	
	public List<GastoExt> getList(){
		return seachMovs.getList().stream().filter(g->g.getIdProveedor().getRetencion().equalsIgnoreCase("S")).toList();
	}
	 
}
