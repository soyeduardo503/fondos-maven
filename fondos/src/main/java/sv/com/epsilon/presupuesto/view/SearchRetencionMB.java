/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.presupuesto.view.listas.ListaCierresMB;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
public class SearchRetencionMB {
	@ManagedProperty(value = "#{cierreMensualMB}")	
	private CierreMensualMB cierres;
	@ManagedProperty(value = "#{listaCierresMB}")
	private ListaCierresMB listaCierresMB;
	public List<GastoExt> getList(){
		if(cierres!=null&& cierres.getList()!=null)
			return cierres.getList().stream().filter(g->g.getIdProveedor().getRetencion()!=null&& g.getIdProveedor().getRetencion().equalsIgnoreCase("S")).collect(Collectors.toList());
		else
			return listaCierresMB.getList().stream().filter(g->g.getIdProveedor().getRetencion()!=null&& g.getIdProveedor().getRetencion().equalsIgnoreCase("S")).collect(Collectors.toList());
	}
	 
}
