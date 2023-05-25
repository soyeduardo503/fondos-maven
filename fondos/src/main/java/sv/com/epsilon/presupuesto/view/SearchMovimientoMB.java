/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.ctrlr.CategoriaGastoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.pojo.CategoriaGasto;
import sv.com.epsilon.presupuesto.pojo.SearchGasto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
public class SearchMovimientoMB implements Serializable{

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
	private List<Gasto> list;
	private Gasto gasto=null;
	private List<CategoriaGasto> listDetalle =null;
	private String order;
	
	public SearchMovimientoMB() {
		
	}
	
	public void switchOrder() {
		if(order==null || order.equalsIgnoreCase("DESC")) {
			list.sort(new Comparator<Gasto>() {
				 public int compare(Gasto o1, Gasto o2) {
			            return o2.getFecha().compareTo(o1.getFecha());
			        }
			});
		}else {
			list.sort(new Comparator<Gasto>() {
				 public int compare(Gasto o1, Gasto o2) {
			            return o1.getFecha().compareTo(o2.getFecha());
			        }
			});
		}
	}
	
	public void load(Gasto g) {
		this.gasto=g;
		 listDetalle = new CategoriaGastoCtrlr().convert(g.getIdGasto());
		new ExecuteForm().ExecuteUpdate("idDetailGasto","PF('wgtDetail').show();");
	}
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			try {
				
				SearchGasto searcher=new SearchGasto();
				searcher.setPresupuesto(new Presupuesto(usuarioSessionMB.getPresupuestoSelected().getIdPresupuesto()));
				list=new GastoCtrlr().invocarBusqueda(searcher);
				loadFound();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void invocacionBusqueda() {
		
		
		try {
			list=new GastoCtrlr().invocarBusqueda(search);
			loadFound();
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
			new ExecuteForm().ExecuteUpdate(new String[]{"idGastosTable:idTableGastos"}, new String[]{"PF('findGasto').hide();"});
		else
			new ExecuteForm().ExecuteUpdate(new String[]{}, new String[]{"PF('findGasto').hide();","PF('noFoundG').show();"});
	}
	
	public void loadGasto(Gasto g) {
		
	}

	public Gasto getGasto() {
		return gasto;
	}

	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}

	public List<CategoriaGasto> getListDetalle() {
		return listDetalle;
	}

	public void setListDetalle(List<CategoriaGasto> listDetalle) {
		this.listDetalle = listDetalle;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
	
}
