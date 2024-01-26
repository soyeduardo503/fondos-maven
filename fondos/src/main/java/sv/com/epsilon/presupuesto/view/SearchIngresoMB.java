/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Catingreso;
import sv.com.epsilon.entities.Financiamiento;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CatingresoFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.facade.SearchIngresosFacade;
import sv.com.epsilon.presupuesto.pojo.PresupuestoDashboard;
import sv.com.epsilon.presupuesto.pojo.SearchIngreso;
import sv.com.epsilon.util.ExecuteForm;
/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
@Slf4j
public class SearchIngresoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653392476005317568L;
	/**
	 * 
	 */
	
	private Presupuesto presupuestoSelected=new PresupuestoFacade().defaultValue().orElse(new Presupuesto(1));
	private SearchIngreso search=new SearchIngreso();
	private List<Catingreso> listDonador=new CatingresoFacade().findAllActive();
	private List<Financiamiento> list;
	private List<Presupuesto> presupuestoActive=new PresupuestoFacade().findAllActive();
	
	
	public SearchIngresoMB() {
		
	}
	public void loadData() {
		log.info("No load data");
	}
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()){
			
		}
	}
	
	public String findDonador(Integer idCatingreso) {
		Optional<Catingreso> donador = listDonador.stream().filter(d->d.getIdCatingreso()==idCatingreso).findFirst();
		if(donador.isPresent()) {
			return donador.get().getNombre();
		}
		return "N/E";
	}
	
	public void invocacionBusqueda() {
		
		
		try {
			search.setPresupuesto(presupuestoSelected);
			list=new SearchIngresosFacade().invocarBusqueda(search);
			new ExecuteForm().update("idIngresosTable");
//			loadFound();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public SearchIngreso getSearch() {
		return search;
	}



	public void setSearch(SearchIngreso search) {
		this.search = search;
	}



	
	
	
	public List<Catingreso> getListDonador() {
		return listDonador;
	}

	public void setListDonador(List<Catingreso> listDonador) {
		this.listDonador = listDonador;
	}

	public List<Financiamiento> getList() {
		return list;
	}



	public void setList(List<Financiamiento> list) {
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
