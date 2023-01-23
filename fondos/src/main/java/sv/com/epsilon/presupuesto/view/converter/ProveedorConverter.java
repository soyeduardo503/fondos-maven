/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.ProveedorFacade;

/**
 * @author usuario07
 *
 */
@FacesConverter(value="proveedorConverter")
public class ProveedorConverter extends AbstractConverter<Proveedor,ProveedorFacade> implements Converter {

	/**
	 * 
	 */
	public ProveedorConverter() {
		super(Proveedor.class,ProveedorFacade.class);
	}

	
	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
		if("".equals(key))
			return null;
		return this.find(key);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objEntity) {
		System.out.println(objEntity.getClass());
		if(objEntity instanceof  String || objEntity==null)
			return "";
		if(objEntity instanceof Proveedor) {
			
			String value= this.getKeyString(objEntity);
			System.out.println("returned value ->"+value);
			return value;
		}
		
		return "";
	}

}
