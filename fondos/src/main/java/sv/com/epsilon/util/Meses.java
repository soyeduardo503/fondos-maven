/**
 * 
 */
package sv.com.epsilon.util;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Zeta
 *
 */
@ManagedBean
@ViewScoped
public class Meses implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3881008657602206538L;

	/**
	 * 
	 */
	
	private static List<Mes> list;
	{
		list=new ArrayList<Mes>();
		for(int i=1;i<13;i++)
			list.add(new Mes( i));
		
	}
	public Meses() {
		// TODO Auto-generated constructor stub
	}
	public List<Mes> getList() {
		if(list==null) {
			list=new ArrayList<Mes>();
			for(int i=1;i<13;i++)
				list.add(new Mes( new DateFormatSymbols().getMonths()[i-1],i));
		}
		return list;
	}
	public void setList(List<Mes> list) {
		this.list = list;
	}
	
	

	 public String obtenerDiaPrimeroMes() {
		 
	    	return 1+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+(Calendar.getInstance().get(Calendar.YEAR));
	    }
	    
	    public String obtenerDiaUltimoMes() {
	    	return Calendar.getInstance().getMaximum(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+(Calendar.getInstance().get(Calendar.YEAR));
	    }
	public static String obtenerNameMes(int i) {
		return new DateFormatSymbols(new Locale("es")).getMonths()[i-1];
	}
	
	public static String obtenerMesLetra(int i) {
		if(i==-1||i==0)
			i=1;
		return new Mes(i).getNombre();
	}
	
	public static String obtenerTexto(int dia, int mes) {
		return (dia+" de "+obtenerMesLetra(mes)).toUpperCase();
	}
	
}
