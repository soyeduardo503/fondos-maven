/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Projection;
import sv.com.epsilon.presupuesto.pojo.ChartData;
import sv.com.epsilon.presupuesto.pojo.GastoReal;
import sv.com.epsilon.presupuesto.pojo.SerieData;
import sv.com.epsilon.presupuesto.pojo.ValueToChart;
import sv.com.epsilon.util.Mes;
import sv.com.epsilon.util.Util;

/**
 * @author martinezc
 *
 */
public class DrawChartCtrlr {

	/**
	 * 
	 */
	private ChartData chartData;
	
	public DrawChartCtrlr() {
		
	}
	
	public ChartData setPresupuesto(Presupuesto p,DataGastoPeriodCtrlr dataGasto) {
		ChartData chartData=build(true,ChartData.LINE,p.getNombrePresupuesto(),"Meses","Montos", makeSerieDataFromData( dataGasto));
		return chartData;
	}
	
	private List<SerieData> makeSerieDataFromData(DataGastoPeriodCtrlr dataGasto) {
		List<SerieData> list=new ArrayList<>();
		SerieData gastos		= 	fromGasto(dataGasto.getList());
		SerieData projections	=	fromProjections(dataGasto.getListProjections(),dataGasto.getList().stream().map(v->v.getMes().getIdMes()).collect(Collectors.toList()));		
		list.add(projections);
		list.add(gastos);
		return list;
	}

	private SerieData fromGasto(List<GastoReal> list) {
		SerieData serieData=new SerieData();
		serieData.setSerieName("Gasto Real");
		List<ValueToChart> listData= list.stream().map(r-> new ValueToChart( r.getMes().getIdMes(),r.getGastoReal())).collect(Collectors.toList());
		serieData.setValues(listData);
		return serieData;
	}

	private SerieData fromProjections(List<Projection> listProjections,List<Integer> l) {
		SerieData serieData=new SerieData();
		serieData.setSerieName("Gasto Previsto");
		List<ValueToChart> listData= listProjections.stream().filter(v->l.contains(v.getMesLetter().getIdMes())).map(r-> new ValueToChart( r.getMesLetter().getIdMes(),r.getAmount())).collect(Collectors.toList());
		serieData.setValues(listData);
		return serieData;
	}

	public ChartData build(boolean b,String type,String title,String xTitle,String yTitle,List<SerieData> serieData) {
		List<Object> xLegends=fromSerieData(serieData);
		chartData=new ChartData().setMonthly(b).setTitle(title).setXTitle(xTitle).setYTtitle(yTitle).setSeries(serieData).setXLegends(xLegends).setTitle(type);
		return chartData;
	}
	public ChartData build(String type,String title,String xTitle,String yTitle,List<SerieData> serieData) {
		List<Object> xLegends=fromSerieData(serieData);
		chartData=new ChartData().setTitle(title).setXTitle(xTitle).setYTtitle(yTitle).setSeries(serieData).setXLegends(xLegends).setTitle(type);
		return chartData;
	}
	
	private List<Object> fromSerieData(List<SerieData> serieData) {
		List<ValueToChart> listCombined=new ArrayList<>();
		for(SerieData serie: serieData) {
			listCombined.addAll(serie.getValues());
			
		}
		List<Object> listValues = listCombined.stream().map(serie->Integer.parseInt( serie.getKey())).filter(Util.distinctByKey(val->val)).collect(Collectors.toList());
		return listValues;
	}

	public void draw() {
		
	}
	
	

}
