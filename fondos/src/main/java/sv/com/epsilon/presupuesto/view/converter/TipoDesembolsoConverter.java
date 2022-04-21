/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sv.com.epsilon.entities.Tipodesembolso;
import sv.com.epsilon.facade.TipodesembolsoFacade;

/**
 * @author usuario07
 *
 */
@FacesConverter(value="tipoDesembolsoConverter")
public class TipoDesembolsoConverter extends AbstractConverter<Tipodesembolso,TipodesembolsoFacade> implements Converter {

	/**
	 * 
	 */
	public TipoDesembolsoConverter() {
		super(Tipodesembolso.class,TipodesembolsoFacade.class);
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
		return this.getKeyString(objEntity);
	}

}
