package sv.com.epsilon.presupuesto.view.listas;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import sv.com.epsilon.facade.FinanciamientoFacade;
import sv.com.epsilon.presupuesto.pojo.IngresosAcumulados;

@ManagedBean
@ViewScoped
@Data
public class ListaIngresoAcumuladoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1785936000521757900L;
	List<IngresosAcumulados> list;
	public ListaIngresoAcumuladoMB() {
		
	}
	
	
	public void load(Integer idPresupuesto) {
		list =(List<IngresosAcumulados>) new FinanciamientoFacade().getList("/ingresos/acumulados/"+idPresupuesto, IngresosAcumulados.class);
	}
	

}
