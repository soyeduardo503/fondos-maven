/**
 * 
 */
package sv.com.epsilon.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.util.ReadProperty;
import sv.com.epsilon.util.RedirectNv;

/**
 * @author Zeta
 *
 */
@ManagedBean
@ViewScoped
public class MenuUsuarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3676776304850530634L;
	ReadProperty reader=new ReadProperty();
	
	/**
	 * mainPage=/index.xhtml
logoutPage=/login/login.xhtml
mantenimiento=/mantenimientos/index.xhtml
mantenimientoEmpleados=/mantenimientos/empleados.xhtml
mantenimientoParametros=/mantenimientos/parametros.xhtml
mantenimientoPlanilla=/mantenimientos/planilla.xhtml
planilla=/planilla/index.xhtml
planillaPpal=/planilla/planilla.xhtml
	 * */
	
	public MenuUsuarioMB() {
		
	}
	
	public void goTo(String goUrl) {
		new RedirectNv(reader.read(	"url.properties", goUrl));
	}
	
	public void goToPlanilla() {
		
	}
	public void goToPlaillaPpal() {
		
	}
	public void goToParametros() {
		
	}
	
}
