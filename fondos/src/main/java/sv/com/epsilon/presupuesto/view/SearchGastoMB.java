/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	
	
	private SearchGasto search;
	
	
	
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
	
	
	

}
