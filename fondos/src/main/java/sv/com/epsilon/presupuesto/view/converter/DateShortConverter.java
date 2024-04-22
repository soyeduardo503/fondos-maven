/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author usuario07
 *
 */
@FacesConverter(value="dateShortConverter")
public class DateShortConverter  implements Converter {

	/**
	 * 
	 */
	public DateShortConverter() {
		
	}

	
	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
	
		if("".equalsIgnoreCase(key))
			return null;
		
		return Calendar.getInstance();
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objEntity) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		if(objEntity==null)
			return "";
		if(objEntity instanceof  String)
			return "";
		if(objEntity instanceof Calendar)
			return sdf.format(((Calendar)objEntity).getTime());
		return "N/F";
	}

}
