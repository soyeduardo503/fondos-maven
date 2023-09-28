/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.presupuesto.pojo.DetalleGastoMes;
import sv.com.epsilon.presupuesto.pojo.Distribution;
import sv.com.epsilon.response.NumberResponse;
import sv.com.epsilon.util.Colors;
import sv.com.epsilon.util.Mes;
import sv.com.epsilon.util.Meses;

/**
 * @author 50364
 *
 */
public class ChartsCtrlr {

	/**
	 * 
	 */
	public ChartsCtrlr() {
		
	}

	
	 public LineChartModel createLineYear(Presupuesto p) {
	        LineChartModel lineModel = new LineChartModel();
	        ChartData data = new ChartData();

	        LineChartDataSet dataSet = new LineChartDataSet();
	        List<Object> values = new ArrayList<>();
	        
	        List<String> labels = new ArrayList<>();
	       
	        List<Mes> listMeses = new Meses().getList();
	        GastoFacade gastoFacade=new GastoFacade();
	        for(Mes m:listMeses) {
	        	NumberResponse resp = gastoFacade.getNumber("/amount/month/"+p.getYear()+"/"+m.getIdMes()+"/"+p.getIdPresupuesto());
	        	labels.add(m.getNombre());
	        	values.add( resp.getDoubleValue());
	        }
	        dataSet.setData(values);
	        dataSet.setFill(false);
	        dataSet.setLabel("Gastos por Mes");
	        dataSet.setBorderColor("rgb(75, 192, 192)");
	        
	        data.addChartDataSet(dataSet);
	        data.setLabels(labels);

	        //Options
	        LineChartOptions options = new LineChartOptions();
	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText(p.getNombrePresupuesto());
	        options.setTitle(title);

	        lineModel.setOptions(options);
	        lineModel.setData(data);
	        return lineModel;
	    }
	 
	 public DonutChartModel createDonutModel(List<Distribution> list) {
		 	DonutChartModel donutModel = new DonutChartModel();
	        ChartData data = new ChartData();

	        DonutChartDataSet dataSet = new DonutChartDataSet();
	        List<Number> values = new ArrayList<>();
	        
	        List<String> labels = new ArrayList<>();
	        List<String> bgColors = new ArrayList<>();
	        data.addChartDataSet(dataSet);
	        list.stream().forEach(dist->{values.add(dist.getValue()); labels.add(dist.getDescription()); });
	        
	        dataSet.setData(values);
	        Colors.getByCount(labels.size()).forEach(cl->bgColors.add(cl.toString()));;

	        dataSet.setBackgroundColor(bgColors);

	        
	        
	        
	        data.setLabels(labels);

	        donutModel.setData(data);
	        return donutModel;
	    }
	 
	 public DonutChartModel createDonutModelGastos(List<DetalleGastoMes> list) {
		 	DonutChartModel donutModel = new DonutChartModel();
	        ChartData data = new ChartData();

	        DonutChartDataSet dataSet = new DonutChartDataSet();
	        List<Number> values = new ArrayList<>();
	        
	        List<String> labels = new ArrayList<>();
	        List<String> bgColors = new ArrayList<>();
	        data.addChartDataSet(dataSet);
	        list.stream().forEach(dist->{values.add(dist.acumulado()); labels.add(dist.getCategoria().getNombre()); });
	        
	        dataSet.setData(values);
	        Colors.getByCount(labels.size()).forEach(cl->bgColors.add(cl.toString()));;

	        dataSet.setBackgroundColor(bgColors);

	        
	        
	        
	        data.setLabels(labels);

	        donutModel.setData(data);
	        return donutModel;
	    }
	 public DonutChartModel createDonutModelGastosDummy() {
		 List<DetalleGastoMes> list=new ArrayList<>();
		 	DonutChartModel donutModel = new DonutChartModel();
	        ChartData data = new ChartData();

	        DonutChartDataSet dataSet = new DonutChartDataSet();
	        List<Number> values = new ArrayList<>();
	        
	        List<String> labels = new ArrayList<>();
	        List<String> bgColors = new ArrayList<>();
	        data.addChartDataSet(dataSet);
	        list.stream().forEach(dist->{values.add(dist.acumulado()); labels.add(dist.getCategoria().getNombre()); });
	        
	        dataSet.setData(values);
	        Colors.getByCount(labels.size()).forEach(cl->bgColors.add(cl.toString()));;

	        dataSet.setBackgroundColor(bgColors);

	        
	        
	        
	        data.setLabels(labels);

	        donutModel.setData(data);
	        return donutModel;
	    }
	 
}
