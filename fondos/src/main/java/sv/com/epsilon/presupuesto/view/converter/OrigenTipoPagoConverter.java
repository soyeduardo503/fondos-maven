/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author 50364
 *
 */
@FacesConverter(value = "origenTipoPago")
public class OrigenTipoPagoConverter implements Converter{

	/**
	 * 
	 */
	public OrigenTipoPagoConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		return "Efectivo".equals(arg2)?"B":"A";
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return String.valueOf(arg2).equals("A")?"Cuenta":"Efectivo";
	}

}
