/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sv.com.epsilon.entities.Catingreso;
import sv.com.epsilon.facade.CatingresoFacade;

/**
 * @author usuario07
 *
 */
@FacesConverter(value="catingresoConverter")
public class CatingresoConverter extends AbstractConverter<Catingreso,CatingresoFacade> implements Converter {

	/**
	 * 
	 */
	public CatingresoConverter() {
		super(Catingreso.class,CatingresoFacade.class);
	}

	
	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
	
		if("".equalsIgnoreCase(key))
			return null;
		return this.find(key);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objEntity) {
		
		if(objEntity==null)
			return "";
		if(objEntity instanceof  String)
			return "";
		if(objEntity instanceof Catingreso)
			return ((Catingreso) objEntity).getNombre();
		return this.find(objEntity).getNombre();
	}

}
