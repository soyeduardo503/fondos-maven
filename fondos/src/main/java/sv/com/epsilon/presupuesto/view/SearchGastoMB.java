/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.presupuesto.pojo.SearchGasto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
public class SearchGastoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653392476005317568L;
	/**
	 * 
	 */
	
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	private SearchGasto search=new SearchGasto();
	private List<Proveedor> listProveedor=new ProveedorFacade().findAllActive();
	private List<GastoExt> list;
	private Date inicio;
	private Date fin;
	
	
	
	public SearchGastoMB() {
		
	}
	
	
	
	public void invocacionBusqueda() {
		
		list=new ArrayList<>();
		try {
			if(inicio!=null) {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(inicio);
				search.setFechaInicial(cal1);
			}
			if(fin!=null) {
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(fin);
				search.setFechaFinal(cal2);
			}
			search.setPresupuesto(this.usuarioSessionMB.getPresupuestoSelected());
			new GastoCtrlr().invocarBusqueda(search).forEach(v->list.add(v));
			loadFound();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	public void edit() {
		System.out.println("OK");
	}
	
	public void loadFound() {
		if(list!=null&&list.size()>0)
			new ExecuteForm().ExecuteUpdate(new String[]{"findGasto:idDlgGastoWtg"}, new String[]{"PF('findGasto').hide();","PF('dlgGastosWtg').show();"});
		else
			new ExecuteForm().ExecuteUpdate(new String[]{}, new String[]{"PF('findGasto').hide();","PF('noFoundG').show();"});
	}
	
	public void loadGasto(Gasto g) {
		
	}

}
