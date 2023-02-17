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
@FacesConverter(value="primaryConverter")
public class PrimaryConverter implements Converter {

	/**
	 * 
	 */
	private static final String ACTIVE="A";
	private static final String FINALIZE="F";
	private static final String INACTIVE="I";
	private static final String REVERT="R";
	private static final String LIQUIDATED="L";

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		
		
		return arg2.equals("1")?"*":"";
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	
	
		return (String.valueOf(arg2).equals("A"))?"1":"I";
		
	}

}
