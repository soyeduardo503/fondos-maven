/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.presupuesto.pojo.SearchGasto;
import sv.com.epsilon.util.ExecuteForm;

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
	
	
	private SearchGasto search=new SearchGasto();
	private List<Proveedor> listProveedor=new ProveedorFacade().findAllActive();
	private List<Gasto> list;
	
	
	public SearchGastoMB() {
		
	}
	
	
	
	public void invocacionBusqueda() {
		
		
		try {
			new GastoCtrlr().invocarBusqueda(search).forEach(v->list.add(v));
			loadFound();
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
	
	
	
	public List<Gasto> getList() {
		return list;
	}



	public void setList(List<Gasto> list) {
		this.list = list;
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
