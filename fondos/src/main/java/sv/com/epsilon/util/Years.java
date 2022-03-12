/**
 * 
 */
package sv.com.epsilon.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author usuario07
 *
 */
@ManagedBean
@RequestScoped
public class Years {

	/**
	 * 
	 */
	public Years() {

	}

	public List<Integer> obtenerAnios(){
		List<Integer> list=new ArrayList<Integer>();
		for(int i=2018;i<Calendar.getInstance().get(Calendar.YEAR);i++){
			list.add(i);
		}
		return list;
	}
}
