/**
 * 
 */
package sv.com.epsilon.presupuesto.view.consulta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.pojo.DetalleGastoMes;
import sv.com.epsilon.presupuesto.pojo.SearchParameterGastoMensual;
import sv.com.epsilon.presupuesto.view.PresupuestoViewMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ConsultaDetalleGastoEspecificoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5281944287718965908L;
	/**
	 * 
	 */
	
	private Categoria categoria;
	private List<DetalleGastoMes> list=new ArrayList<>();
	private List<DetalleGastoMes> listComplete=new ArrayList<>();
	private SearchParameterGastoMensual search = new SearchParameterGastoMensual();
	@ManagedProperty(value = "#{presupuestoViewMB}")
	private PresupuestoViewMB presupuestoViewMB;
	private Boolean showAll=true;

	
	public void load(DetalleGastoMes det) {
		categoria=det.getCategoria();
		search.setPresupuesto(presupuestoViewMB.getPresupuesto());
		search.setCategoria(categoria );				
		listComplete = new GastoCtrlr().invocarBusqueda(search);
		
		updateShow();
		
	}
	
	public void updateShow() {
		if(listComplete!=null) {
			if(!showAll) {
				list=listComplete.stream().filter(v->v.getMontos()!=null).toList();
			}else {
				list=listComplete;
			}
		}
		new ExecuteForm().update("IDDetalleGastosEspecificosMes:pnelDataGastoMes2");
	}
}
