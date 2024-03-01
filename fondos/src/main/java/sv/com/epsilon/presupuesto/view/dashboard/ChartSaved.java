/**
 * 
 */
package sv.com.epsilon.presupuesto.view.dashboard;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.primefaces.model.StreamedContent;

import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.presupuesto.pojo.ChartData;
import sv.com.epsilon.presupuesto.pojo.SerieData;
import sv.com.epsilon.presupuesto.pojo.ValueToChart;
import sv.com.epsilon.util.Meses;

/**
 * @author martinezc
 *
 */
@Slf4j
public class ChartSaved {

	/**
	 * 
	 */
	public ChartSaved() {
		// TODO Auto-generated constructor stub
	}

	
	private StreamedContent chart;
	
	
	
	private PieDataset createDataset(List<ValueToChart> items) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		items.forEach(item->dataset.setValue(item.getKey(), item.getValue()));
		
		return dataset;
	}

	public static void main(String[] args) throws IOException {
		
		ChartData chartData=new ChartData();
		chartData.setType("LINE");
		chartData.setSeries(new ArrayList<>());
		SerieData serieData1=new SerieData();
		serieData1.setSerieName("Datos 1");
		serieData1.setValues(new ArrayList<>());
		serieData1.getValues().add(new ValueToChart("1", 2550.0));
		serieData1.getValues().add(new ValueToChart("2", 306.0));
		serieData1.getValues().add(new ValueToChart("3", 4500.0));
		
		chartData.getSeries().add(serieData1);
		SerieData serieData2=new SerieData();
		serieData2.setSerieName("Datos 2");
		serieData2.setValues(new ArrayList<>());
		serieData2.getValues().add(new ValueToChart("4", 3550.0));
		serieData2.getValues().add(new ValueToChart("5", 3406.0));
		serieData2.getValues().add(new ValueToChart("6", 500.0));
		chartData.getSeries().add(serieData2);
		chartData.setTitle("Prueba de gastos");
		List<Object> xLegends=new ArrayList<>();
		xLegends.add(1);
		xLegends.add(2);
		xLegends.add(3);
		chartData.setXLegends(xLegends);
		CategoryChart chart = new CategoryChartBuilder().width(600).height(600).title("Temperature vs. Color").xAxisTitle("Color").yAxisTitle("Temperature").theme(ChartTheme.GGPlot2).build();
		 

		chart.addSeries("fish", new ArrayList<String>(Arrays.asList(new String[] { "Blue", "Red", "Green", "Yellow", "Orange" })), new ArrayList<Number>(Arrays.asList(new Number[] { -40, 30, 20, 60,
			        60 })));
			    
		
		chartData.setXTitle("Datos X");
		chartData.setYTitle("Data Y");
		
		new ChartSaved().buildPieChart(chartData);
	    // Show it
	    
	}
	
	public  void buildPieChart(ChartData chartData) throws IOException {
		   PieChart chart = new PieChartBuilder().width(800).height(800).title(chartData.getTitle()).build();
		   
		    // Customize Chart
		    chart.getStyler().setLegendVisible(true);		    
		    //chart.getStyler().setPlotContentSize(.6);
		    //chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
		    chart.getStyler().setCircular(false);
		    Color color=Color.white;
		    chart.getStyler().setSeriesColors( ChartSaved.getSerieColors());
		    chart.getStyler().setPlotBackgroundColor(color);
		    chart.getStyler().setChartBackgroundColor(color);
		    // Series
		    SerieData populateData = chartData.getMainSerie();
		    populateData.getValues().forEach(v->{
		    	chart.addSeries(v.getKey(), v.getValue());
		    });
		    BitmapEncoder.saveBitmap(chart, "C:/files/output/pie", BitmapFormat.PNG);
		    
	}
	
	public  void buildLineChart(ChartData chartData) throws IOException {
		
		
		
	    // Create Chart
	    XYChart chart = new XYChartBuilder().width(800).height(600).title(chartData.getTitle()).xAxisTitle(chartData.getXTitle()).yAxisTitle(chartData.getYTitle()).build();
	    
	    chartData.getSeries().forEach(t-> chart.addSeries(t.getSerieName(),chartData.getXLegends(),createSerie(t.getValues())));
	    
	    Color color=Color.white;
	    chart.getStyler().setPlotBackgroundColor(color);
	    chart.getStyler().setChartBackgroundColor(color);
	    chart.getStyler().setSeriesColors( ChartSaved.getSerieColors());
	    if(chartData.getMonthly())
	    	chart.getStyler().setxAxisTickLabelsFormattingFunction(x->Meses.obtenerMesLetra(x.intValue() ).substring(0,3));
	    BitmapEncoder.saveBitmap(chart, "C:/files/output/line", BitmapFormat.PNG);
	    // Show it
	    
	}
	 

	
	private List<Number> createSerie(List<ValueToChart> items) {
		List<Number> list = items.stream().map(v-> v.getValue()).collect(Collectors.toList());		
		return list;
	}
	
	
	
	private static Color[] getSerieColors() {
		 return  new Color[] {  new Color(232, 72, 85),new Color(33,158,188), new Color(142, 202, 230),new Color(2,48,71), new Color(255,183,3), new Color(251, 133, 0),
				 new Color(154, 184, 122),new Color(255, 102, 99) , new Color(224, 141, 172)};
		
	}
}
