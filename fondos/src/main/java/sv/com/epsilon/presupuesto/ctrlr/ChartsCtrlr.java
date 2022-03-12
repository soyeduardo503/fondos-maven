/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import sv.com.epsilon.entities.Presupuesto;
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
//	        values.add(65);
//	        values.add(59);
//	        values.add(80);
//	        values.add(81);
//	        values.add(56);
//	        
//	        values.add(40);
	        
	        List<String> labels = new ArrayList<>();
	        List<Mes> listMeses = new Meses().getList();
	        for(Mes m:listMeses) {
	        	labels.add(m.getNombre());
	        	values.add(Math.round(100));
	        }
//	        dataSet.setData(values);
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
	 
	 
}
