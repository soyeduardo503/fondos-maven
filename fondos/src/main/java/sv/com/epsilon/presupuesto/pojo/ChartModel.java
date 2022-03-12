/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;

/**
 * @author usuario07
 *
 */
public class ChartModel implements Serializable {

	/**
	 * 
	 */
	public ChartModel() {
		
	}

//	private void createPolarAreaModel() {
//        polarAreaModel = new PolarAreaChartModel();
//        ChartData data = new ChartData();
//         
//        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
//        List<Number> values = new ArrayList<>();
//        values.add(11);
//        values.add(16);
//        values.add(7);
//        values.add(3);
//        values.add(14);
//        dataSet.setData(values);
//         
//        List<String> bgColors = new ArrayList<String>();
//        bgColors.add("rgb(255, 99, 132)");
//        bgColors.add("rgb(75, 192, 192)");
//        bgColors.add("rgb(255, 205, 86)");
//        bgColors.add("rgb(201, 203, 207)");
//        bgColors.add("rgb(54, 162, 235)");
//        dataSet.setBackgroundColor(bgColors);
//         
//        data.addChartDataSet(dataSet);
//        List<String> labels = new ArrayList<>();
//        labels.add("Red");
//        labels.add("Green");
//        labels.add("Yellow");
//        labels.add("Grey");
//        labels.add("Blue");
//        data.setLabels(labels);
//         
//        polarAreaModel.setData(data);
//    }
	
	  public MeterGaugeChartModel createMeterGaugeModels(Presupuesto presupuesto) {
		  
		  BigDecimal b=new BigDecimal(0);
			if(presupuesto.getTotal()>0)
				b= new BigDecimal(presupuesto.getTotal()-presupuesto.getActual()).divide(new BigDecimal(presupuesto.getTotal())).multiply(new BigDecimal(100));
		  
        MeterGaugeChartModel meterGaugeModel2 = initMeterGaugeModel(b.intValue());
        meterGaugeModel2.setTitle(presupuesto.getNombrePresupuesto());
        List<String> bgColors = new ArrayList<String>();
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(201, 203, 207)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(255, 16, 9 )");
        
        
        
        
        
        //dataSet.setBackgroundColor(bgColors);
        meterGaugeModel2.setSeriesColors("b4ff60,60ffdd,ff63be,FF0202");
        meterGaugeModel2.setGaugeLabel("");
        meterGaugeModel2.setGaugeLabelPosition("bottom");
        meterGaugeModel2.setShowTickLabels(false);
        meterGaugeModel2.setLabelHeightAdjust(110);
        meterGaugeModel2.setIntervalOuterRadius(100);
        return meterGaugeModel2;
    }

	private MeterGaugeChartModel initMeterGaugeModel(Integer value) {
		Number n= value;
		List<Number> rango=new ArrayList<Number>();
		rango.add(20);
		rango.add(40);
		rango.add(60);
		rango.add(100);
		return new MeterGaugeChartModel(n, rango);
	}
	public PieChartModel createPieModel(Presupuesto presupuesto){
		PieChartModel pie=new PieChartModel();
		for(Categoria c:presupuesto.getCategoriaList()){
			pie.set(c.getNombre(), c.getMonto());
		}
		pie.setSeriesColors("ff63be,4bc0be,60ffdd,b4ff60");
		return pie;
	}
	
}
