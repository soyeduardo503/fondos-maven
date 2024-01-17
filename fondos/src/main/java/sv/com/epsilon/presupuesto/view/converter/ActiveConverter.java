/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sv.com.epsilon.util.Log;
/**
 * @author 50364
 *
 */
@FacesConverter(value="activeConverter")
public class ActiveConverter implements Converter {

	/**
	 * 
	 */
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Log.debug("valor de getAsObject "+arg2);
		
		return arg2.equals("A")?"true":"false";
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Log.debug("valor de getAsString "+arg2);
		return (String.valueOf(arg2).equals("A"))?"1":"I";
		
	}

}
