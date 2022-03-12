/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sv.com.epsilon.util.Util;

/**
 * @author 50364
 *
 */
@FacesConverter(value = "moneyConverter")
public class MoneyConverter implements Converter{

	/**
	 * 
	 */
	public MoneyConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		return new BigDecimal ( arg2.replace("$", ""));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		BigDecimal money=new BigDecimal(0);
		if(arg2 instanceof BigDecimal)
			money =(BigDecimal)(arg2);
		if(arg2 instanceof Double)
			money =new BigDecimal ((Double)arg2);
		
		return "$"+Util.round(money, 2);
	}

}
