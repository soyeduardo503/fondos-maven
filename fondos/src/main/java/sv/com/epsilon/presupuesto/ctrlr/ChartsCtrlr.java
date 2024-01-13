/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
import sv.com.epsilon.presupuesto.pojo.GastoReal;
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
	        if(p.getYear()==Calendar.getInstance().get(Calendar.YEAR)) {
	        	listMeses=listMeses.stream().filter(m->m.getIdMes()<Calendar.getInstance().get(Calendar.MONTH)+2).toList();
	        }
	        for(Mes m:listMeses) {
	        	NumberResponse resp = gastoFacade.getNumber("/amount/month/"+p.getYear()+"/"+m.getIdMes()+"/"+p.getIdPresupuesto());
	        	if(resp.getCod()!=9999) {
	        		labels.add(m.getNombre());
	        		values.add( resp.getDoubleValue());
	        	}
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
	 
	 
	 
	 public LineChartModel createLineYear(List<GastoReal> list,Presupuesto p) {
	        LineChartModel lineModel = new LineChartModel();
	        ChartData data = new ChartData();

	        LineChartDataSet dataSet = new LineChartDataSet();
	        LineChartDataSet dataSetR = new LineChartDataSet();
	        
	        List<Object> values = new ArrayList<>();
	        List<Object> valuesP = new ArrayList<>();
	        
	        List<String> labels = new ArrayList<>();
	       
	        List<Mes> listMeses = new Meses().getList().stream().filter(m->exist(list,m)).toList();
	        GastoFacade gastoFacade=new GastoFacade();
	        list.stream().forEach(gr-> {
	        	labels.add(gr.getMes().getNombre());
	        	values.add(gr.getGastoReal());
	        	valuesP.add(gr.getGastoPrevisto());
	        });
//	        for(Mes m:listMeses) {
//	        		
//	        		Optional<GastoReal> gr=list.stream().filter(gr->gr.getMes().equals(m)).findFirst();
//	        		if(gr.)
//	        		labels.add(m.getNombre());
//	        		values.add( );
//	        		}
//	        
//	        }
	        
	        /*Data set valores reales*/
	        dataSet.setData(values);
	        dataSet.setFill(false);
	        dataSet.setLabel("Gastos por Mes");
	        dataSet.setBorderColor("rgb(75, 192, 192)");
	        /*Data set valores previstos*/
	        dataSetR.setData(valuesP);
	        dataSetR.setFill(false);
	        dataSetR.setLabel("Gastos previstos");
	        dataSetR.setBorderColor("rgb(60, 193, 45)");
	        
	        
	        data.addChartDataSet(dataSet);
	        data.addChartDataSet(dataSetR);
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
	 private boolean exist(List<GastoReal> list, Mes m) {
		// TODO Auto-generated method stub
		return list.stream().filter(gs->gs.getMes().equals(m)).findFirst().isPresent();
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
