/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author martinezc
 *
 */
@Data
public class ChartData {

	/**
	 * 
	 */
	private String title;
	private String xTitle;
	private String yTitle;
	private List<SerieData> series;
	private List<?> xLegends;
	private String type;
	public final static String LINE="LINE";
	public final static String PIE="PIE";
	public final static String BAR="BAR";
	public final static String GAUG="GAUG";
	private Boolean monthly=false;
	
	public ChartData() {
		
	}

	public ChartData setTitle(String title) {
		this.title=title;
		return this;
	}
	
	public ChartData setXTitle(String xTitle) {
		this.xTitle=xTitle;
		return this;
	}
	
	public ChartData setYTtitle(String yTitle) {
		this.yTitle=yTitle;
		return this;
	}
	
	public ChartData setSeries(List<SerieData> list) {
		this.series=list;
		return this;
	}
	
	public ChartData setXLegends(List<?> list) {
		this.xLegends=list;
		return this;
	}
	
	public ChartData setType(String type) {
		this.type=type;
		return this;
	}
	public ChartData setMonthly(Boolean monthly) {
		this.monthly=monthly;
		return this;
	}
	
	public SerieData getMainSerie() {
		if(series!=null&&series.size()>0) {
			return series.get(0);
		}
		return new SerieData(new ArrayList<ValueToChart>());
	}
}
