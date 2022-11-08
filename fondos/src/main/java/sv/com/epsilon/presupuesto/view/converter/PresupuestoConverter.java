/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.PresupuestoFacade;

/**
 * @author usuario07
 *
 */
@FacesConverter(value="presupuestoConverter")
public class PresupuestoConverter extends AbstractConverter<Presupuesto,PresupuestoFacade> implements Converter {

	/**
	 * 
	 */
	public PresupuestoConverter() {
		super(Presupuesto.class,PresupuestoFacade.class);
	}

	
	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
	
		if("".equalsIgnoreCase(key))
			return find("1");
		return this.find(key);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objEntity) {
		
		return this.getKeyString(objEntity);
	}

}
