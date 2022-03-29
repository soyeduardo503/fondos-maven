/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Chequera;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.ChequeraFacade;

/**
 * @author usuario07
 *
 */
@FacesConverter(value="chequeraConverter")
public class ChequeraConverter extends AbstractConverter<Chequera,ChequeraFacade> implements Converter {

	/**
	 * 
	 */
	public ChequeraConverter() {
		super(Chequera.class,ChequeraFacade.class);
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
		if(objEntity==null||objEntity instanceof  String)
			return "";
		if(objEntity instanceof Chequera)
			return ((Chequera) objEntity).getNombre();
		return this.getKeyString(objEntity);
	}

}
