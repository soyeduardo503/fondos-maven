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
@FacesConverter(value="statusConverter")
public class StatusConverter implements Converter {

	/**
	 * 
	 */
	private static final String ACTIVE="A";
	private static final String FINALIZE="F";
	private static final String INACTIVE="I";

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Log.info("valor de getAsObject "+arg2);
		switch(arg2) {
		case "Activo" : return ACTIVE;
		case "Finalizado" : return FINALIZE;
		case "Inactive": return INACTIVE;
		}
		
		
		return arg2.equals("A")?"Activo":"false";
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Log.info("valor de getAsString "+arg2);
		switch(String.valueOf(arg2)) {
			
			case ACTIVE: return "Activo";
			case FINALIZE: return "Finalizado";
			case INACTIVE: return "Inactivo";
		}
		return (String.valueOf(arg2).equals("A"))?"1":"I";
		
	}

}
