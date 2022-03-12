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
@FacesConverter(value="tipoPersonaConverter")
public class TipoPersonaConverter implements Converter {

	/**
	 * 
	 */
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Log.info("valor de getAsObject "+arg2);
		
		return arg2.equals("Natural")?"N":"J";
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Log.info("valor de getAsString "+arg2);
		return (String.valueOf(arg2).equals("N"))?"Natural":"Juridica";
		
	}

}
