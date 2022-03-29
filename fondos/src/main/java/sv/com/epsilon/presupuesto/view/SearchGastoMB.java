/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.pojo.SearchGasto;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
public class SearchGastoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653392476005317568L;
	/**
	 * 
	 */
	
	
	private SearchGasto search;
	private List<Proveedor> listProveedor=new ProveedorFacade().findAllActive();
	
	
	public SearchGastoMB() {
		
	}
	
	
	
	public void invocacionBusqueda() {
		
		
		try {
			new GastoCtrlr().invocarBusqueda(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public SearchGasto getSearch() {
		return search;
	}



	public void setSearch(SearchGasto search) {
		this.search = search;
	}



	public List<Proveedor> getListProveedor() {
		return listProveedor;
	}



	public void setListProveedor(List<Proveedor> listProveedor) {
		this.listProveedor = listProveedor;
	}
	
	
	
	public void edit() {
		System.out.println("OK");
	}
	

}
