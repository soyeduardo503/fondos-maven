/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.ctrlr.CategoriaGastoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.pojo.CategoriaGasto;
import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.presupuesto.pojo.Periodo;
import sv.com.epsilon.presupuesto.pojo.SearchGasto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.PeriodoUtil;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
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
	private List<Proveedor> listProveedor=loadProveedores();
	private List<GastoExt> list;
	private List<GastoExt> listRetencion;
	private Gasto gasto=null;
	private List<CategoriaGasto> listDetalle =null;
	private String order;
	private Integer month=Calendar.getInstance().get(Calendar.MONTH);
	private Integer monthCreate;
	private Presupuesto presupuestoSelected;
	private Gasto gastoSelected;
	
	
	public SearchMovimientoMB() {
		
	}
	
	private List<Proveedor> loadProveedores() {
		// TODO Auto-generated method stub
		List<Proveedor> list=new ProveedorFacade().findAllActive();
		list.sort(new Comparator<Proveedor>() {

			@Override
			public int compare(Proveedor o1, Proveedor o2) {
				// TODO Auto-generated method stub
				return o1.getNombre().compareTo(o2.getNombre());
			}
		});
		return list;
	}

	public Double sumaTotal() {
		return list.stream().mapToDouble(a->a.getTotal()).sum();
	}
	public Double sumaTotalRetencion() {
		return list.stream().filter(v->v.getIdProveedor().getRetencion().equalsIgnoreCase("S")).mapToDouble(a->a.getTotal()).sum();
	}
	
	public void switchOrder() {
		GastoCtrlr.order(list, order);
	}
	
	
	
	public String convertDate(Date d) {
		return new SimpleDateFormat("dd/MM/yyyy").format(d);
	}
	public void load(Gasto g) {
		this.gasto=g;
		 listDetalle = new CategoriaGastoCtrlr().convert(g.getIdGasto());
		//new ExecuteForm().ExecuteUpdate("idDetailGasto","PF('wgtDetail').show();");
	}
	
	public void reload(Integer idGasto) {
		
		  
		Gasto gasto= new GastoFacade().findById(idGasto);
		list.stream().filter(g->g.getIdGasto()==idGasto).findFirst().ifPresent(g->{g.setStatus(gasto.getStatus());});
	
		new ExecuteForm().update(":idGastosTable:idTableGastos");
	}
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			try {
				
				SearchGasto searcher=new SearchGasto();
				searcher.setAll(true);
				Periodo periodo = PeriodoUtil.make(Integer.valueOf( presupuestoSelected.getYear()), month);
				search.setFechaInicial(periodo.getInicio());
				search.setFechaFinal(periodo.getFin());
				searcher.setPresupuesto(usuarioSessionMB.getPresupuestoSelected());
				list=new GastoCtrlr().invocarBusqueda(searcher);
				presupuestoSelected=new PresupuestoFacade().defaultValue().get();
				loadFound();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void invocacionBusqueda() {
		
		
		try {
			search.setPresupuesto(presupuestoSelected);
			
			if(month!=null ) {
				Periodo periodo = PeriodoUtil.make(Integer.valueOf( presupuestoSelected.getYear()), month);
				search.setFechaInicial(periodo.getInicio());
				search.setFechaFinal(periodo.getFin());
				search.setAll(false);
			}else {
				search.setAll(true);
			}
			search.setMesRegistrado(monthCreate);
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
	
	
	
	public List<GastoExt> getList() {
		return list;
	}



	public void setList(List<GastoExt> list) {
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
