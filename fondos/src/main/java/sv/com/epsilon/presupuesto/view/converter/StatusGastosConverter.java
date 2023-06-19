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
@FacesConverter(value="statusMovConverter")
public class StatusGastosConverter implements Converter {

	/**
	 * 
	 */
	private static final String ACTIVE="A";
	private static final String FINALIZE="F";
	private static final String INGRESO="I";
	private static final String REVERT="R";
	private static final String LIQUIDADO="L";
	private static final String TINGRESO="T";

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Log.debug("valor de getAsObject "+arg2);
		switch(arg2) {
		case "Activo" : return ACTIVE;
		case "Finalizado" : return FINALIZE;
		case "Ingreso": return INGRESO;
		case "Liquidado"  : return LIQUIDADO;
		case "Tingresado": return TINGRESO;
		}
		
		
		return arg2.equals("A")?"Activo":"false";
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Log.debug("valor de getAsString "+arg2);
		switch(String.valueOf(arg2)) {
			
			case ACTIVE: return "Activo";
			case FINALIZE: return "Finalizado";
			case INGRESO: return "Ingreso";
			case REVERT: return "Revertido";
			case LIQUIDADO: return "Liquidado";
			case TINGRESO : return "Ingreso";
		}
		return (String.valueOf(arg2).equals("A"))?"1":"I";
		
	}

}
