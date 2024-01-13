/**
 * 
 */
package sv.com.epsilon.presupuesto.view.listas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.entities.Cierre;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CierreFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.ctrlr.CierreCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.CierreMensualCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.PresupuestoCtrlr;
import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.presupuesto.pojo.MesEnabled;
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
@ViewScoped
@ManagedBean
@Data
@NoArgsConstructor
public class ListaCierresMB implements Serializable{

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Presupuesto> listPre=new PresupuestoFacade().findAllActive();
	private Presupuesto presupuestoSelected;
	private List<Mes> listMeses=Mes.all();
	private List<MesEnabled> listMesesAllow;
	private Mes mesSelected;
	private Periodo periodo;
	private List<GastoExt> list;
	List<Cierre> listCierres;
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	/**/
	private Integer month;
	private Mes mes;
	private Double montoInicial;
	private Double montoFinal;
	private Boolean reload=false;
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			presupuestoSelected=new PresupuestoCtrlr().predeterminado(usuarioSessionMB.getIdEmpresa());
			activeAllow();
			Collections.sort(listPre, new Comparator<Presupuesto>() {

				@Override
				public int compare(Presupuesto pre1, Presupuesto pre2) {
					// TODO Auto-generated method stub
					return pre2.getYear().compareTo(pre1.getYear());
				}
			});
		}
	}
	
	public void CallMonth(MesEnabled mes) {
		mesSelected=mes;
		loadInfo();
	}
	
	
	public void loadInfo() {
			int idPre=presupuestoSelected.getIdPresupuesto();
			listCierres=new CierreFacade().list("/all/byPresupuesto/"+idPre);
			try {

				SearchGasto searcher = new SearchGasto();
				searcher.setPresupuesto(usuarioSessionMB.getPresupuestoSelected());
				
				Integer month = mesSelected.getIdMes();
				if (month != null) {
					periodo = PeriodoUtil.make(Integer.valueOf(usuarioSessionMB.getPresupuestoSelected().getYear()),
							month);
					searcher.setFechaInicial(periodo.getInicio());
					searcher.setFechaFinal(periodo.getFin());

				}
				list = new ArrayList<>();
				if (month != 1) {						
						new CierreCtrlr().findFirstIncome(idPre,usuarioSessionMB.getPresupuestoSelected().getMesCierre(),presupuestoSelected.getYear(),list);
					
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

	public void loadFound() {
		if (list != null && list.size() > 0)
			new ExecuteForm().ExecuteUpdate(new String[] { "idGastosTable:idTableGastos" },
					new String[] { "PF('findGasto').hide();" });
		else
			new ExecuteForm().ExecuteUpdate(new String[] {},
					new String[] { "PF('findGasto').hide();", "PF('noFoundG').show();" });
	}
	
	public void loadPresupuesto() {
		
		activeAllow();
	}
	
	public void selectMonth(Mes m) {
		this.mesSelected=m;
	}
		
	private void activeAllow() {
		{
			listMesesAllow=new  ArrayList<>();
			List<Mes> mesesCerrados=new CierreCtrlr().findMesesCerrados(presupuestoSelected.getIdPresupuesto());
			listMeses.forEach(mes->listMesesAllow.add(new MesEnabled(mes).setEnabled(mesesCerrados.stream().filter(r-> mes.getIdMes()==r.getIdMes()).findAny().isPresent())));
		}
	}

	public MesEnabled findMes(Integer i) {
		return listMesesAllow.stream().filter(m->m.getIdMes()==i).findFirst().orElseGet(()->new  MesEnabled(Mes.defaultMes()) );
	}

}
