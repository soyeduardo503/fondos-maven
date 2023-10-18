/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import sv.com.epsilon.entities.Catingreso;
import sv.com.epsilon.entities.Cierre;
import sv.com.epsilon.entities.Financiamiento;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.CierreFacade;
import sv.com.epsilon.facade.FinanciamientoFacade;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.ctrlr.CategoriaGastoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.CierreCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.CierreMensualCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.pojo.CategoriaGasto;
import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.presupuesto.pojo.Periodo;
import sv.com.epsilon.presupuesto.pojo.SearchGasto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.Mes;
import sv.com.epsilon.util.PeriodoUtil;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
public class ListaCierreMensualMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653392476005317568L;
	/**
	 * 
	 */

	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	private SearchGasto search = new SearchGasto();
	private List<Proveedor> listProveedor = new ProveedorFacade().findAllActive();
	private List<GastoExt> list;
	private List<GastoExt> listRetencion;
	List<Cierre> listCierres;
	private Gasto gasto = null;
	private List<CategoriaGasto> listDetalle = null;
	private String order;
	private Integer month;
	private Mes mes;
	private Cierre current;
	private Periodo periodo;
	private Double montoInicial;
	private Double montoFinal;
	private Boolean reload=false;
	private boolean vdefault=true;

	public ListaCierreMensualMB() {

	}

	public Double sumaTotal() {
		return list.stream().mapToDouble(a -> a.getTotal()).sum();
	}

	public Double sumaTotalRetencion() {
		return list.stream().filter(v -> v.getIdProveedor().getRetencion().equalsIgnoreCase("S"))
				.mapToDouble(a -> a.getTotal()).sum();
	}

	public void switchOrder() {
		new CierreCtrlr().orderBy(this.order,this.list);
	}

	public void load(Gasto g) {
		this.gasto = g;
		listDetalle = new CategoriaGastoCtrlr().convert(g.getIdGasto());
		new ExecuteForm().ExecuteUpdate("idDetailGasto", "PF('wgtDetail').show();");
	}

	public void preRender() {
		if (!FacesContext.getCurrentInstance().isPostback()||reload) {
			vdefault=true;
			loadInfo();
		}
	}
	public void loadInfo() {
			int idPre=usuarioSessionMB.getIdPresupuestoSelected();
			listCierres=new CierreFacade().list("/all/byPresupuesto/"+idPre);
			try {

				SearchGasto searcher = new SearchGasto();
				searcher.setPresupuesto(usuarioSessionMB.getPresupuestoSelected());
				if(month==null||vdefault)
					this.month = usuarioSessionMB.getPresupuestoSelected().getMesCierre();
				if (month != null) {
					periodo = PeriodoUtil.make(Integer.valueOf(usuarioSessionMB.getPresupuestoSelected().getYear()),
							month);
					searcher.setFechaInicial(periodo.getInicio());
					searcher.setFechaFinal(periodo.getFin());

				}
				list = new ArrayList<>();
				if (month != 1) {						
						new CierreCtrlr().findFirstIncome(idPre,usuarioSessionMB.getPresupuestoSelected().getMesCierre(),usuarioSessionMB.getPresupuestoSelected().getYear(),list);
					
				}
				// list.add(incomeFirst());
				list.addAll(new GastoCtrlr().invocarBusqueda(searcher));
				this.month = searcher.getPresupuesto().getMesCierre();
				this.setMes(new Mes(month));

				CierreMensualCtrlr.appliedIncomesExpencies(list);
				Optional<GastoExt> initialv = list.stream().findFirst();
				Optional<GastoExt> finalv = list.stream().reduce((first, second) -> second);
				{
					montoInicial = initialv.get().getSaldo();
					montoFinal = finalv.get().getSaldo();
				}
				loadFound();

			} catch (Exception e) {
				e.printStackTrace();
			}
		
		reload=false;
	}

	private GastoExt incomeFirst() {
		Optional<Financiamiento> income = new FinanciamientoFacade().findFirst(
				usuarioSessionMB.getIdPresupuestoSelected(), usuarioSessionMB.getPresupuestoSelected().getMesCierre());
		Optional<Catingreso> tx = Optional.of(new Catingreso(0, "SALDO INICIAL "));
		if (income.isPresent())
			return new GastoExt(income.get(), tx);
		else
			return new GastoExt();
	}

	public void invocacionBusqueda() {

		try {
			search.setPresupuesto(usuarioSessionMB.getPresupuestoSelected());

			if (month != null && !month.equals("")) {
				Periodo periodo = PeriodoUtil.make(Integer.valueOf(usuarioSessionMB.getPresupuestoSelected().getYear()),
						month);
				search.setFechaInicial(periodo.getInicio());
				search.setFechaFinal(periodo.getFin());
			}
			list = new GastoCtrlr().invocarBusqueda(search);
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
		if (list != null && list.size() > 0)
			new ExecuteForm().ExecuteUpdate(new String[] { "idGastosTable:idTableGastos" },
					new String[] { "PF('findGasto').hide();" });
		else
			new ExecuteForm().ExecuteUpdate(new String[] {},
					new String[] { "PF('findGasto').hide();", "PF('noFoundG').show();" });
	}

	public void loadGasto(Gasto g) {

	}
	
	public void create() {
		Cierre c=new Cierre(null, Calendar.getInstance(), usuarioSessionMB.getPresupuestoSelected().getMesCierre(), montoInicial, montoFinal, "A", usuarioSessionMB.getIdUser(), usuarioSessionMB.getIdPresupuestoSelected(), usuarioSessionMB.getPresupuestoSelectedDlg().getYear());
		try {
			if(new CierreFacade().action("/do", true, c)) {
				usuarioSessionMB.updatePresupuesto();
				this.list=new ArrayList<>();
				this.reload=true;
				this.preRender();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
