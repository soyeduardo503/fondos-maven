/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.line.LineChartModel;

import lombok.Data;
import sv.com.epsilon.entities.Cierre;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Projection;
import sv.com.epsilon.facade.CierreFacade;
import sv.com.epsilon.facade.ProjectionFacade;
import sv.com.epsilon.presupuesto.pojo.DataGastoPeriod;
import sv.com.epsilon.presupuesto.pojo.GastoReal;
import sv.com.epsilon.util.Mes;

/**
 * @author martinezc
 *
 */
@Data
public class DataGastoPeriodCtrlr {

	/**
	 * 
	 */
	private List<Cierre> listCierres;
	private List<Projection> listProjections;
	private List<GastoReal> list;
	private Presupuesto presupuesto;
	private DataGastoPeriod data;

	public DataGastoPeriodCtrlr() {

	}
	
	public DataGastoPeriodCtrlr presupuesto(Presupuesto pr) {
		this.presupuesto=pr;
		return this;
	}

	public void load(Presupuesto pr) {
		this.presupuesto = pr;
		listCierres = new CierreFacade().list("/all/byPresupuesto/" + pr.getIdPresupuesto());
		listProjections = new ProjectionFacade().getByPresupuesto(pr.getIdPresupuesto());
		process();
	}



	public void load() {
		if (presupuesto != null) {
			listCierres = new CierreFacade().list("/all/byPresupuesto/" + presupuesto.getIdPresupuesto());
			listProjections = new ProjectionFacade().getByPresupuesto(presupuesto.getIdPresupuesto());
			process();
		}
	}

	private void process() {
		list=new ArrayList<>();
		
		listCierres.forEach(c->list.add(new GastoReal(new Mes(c.getMes()),c.getGasto(),listProjections.stream().filter(p->p.getMonth()==c.getMes()).findFirst().orElse(new Projection()).getAmount())));
		
	}
	
	
	public DataGastoPeriod build() {
		this.data=new DataGastoPeriod();
		load();
		data.setChart( createChart());
		data.setListCierre(listCierres);
		data.setList(list);
		
		return data;
		
	}
	
	public LineChartModel createChart() {
		return new ChartsCtrlr().createLineYear(list, presupuesto);
	}
}
