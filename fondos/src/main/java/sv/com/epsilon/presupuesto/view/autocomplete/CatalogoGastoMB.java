/**
 * 
 */
package sv.com.epsilon.presupuesto.view.autocomplete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Tablagasto;
import sv.com.epsilon.facade.TablagastoFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class CatalogoGastoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9064222258153867090L;
	/**
	 * 
	 */
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	private List<Tablagasto> gastos=new TablagastoFacade().findAllActive();
	private List<String> gastoString=new ArrayList<String>();
	private List<String> filtro=new ArrayList<String>();
	
	
	public CatalogoGastoMB() {
		
	}
	
	public List<String> obtenerGastos(String gasto){
		
		if(gastoString==null||gastoString.isEmpty()){
			//si no se ha filtrado aunt
			for(Tablagasto tg:gastos){
				if(tg.getNombre().toUpperCase().contains(gasto.toUpperCase()))
					gastoString.add(tg.getNombre());
			}
			filtro=gastoString;
		}else{
			filtro=new ArrayList<String>();
			for(String filtro:gastoString){
				if(filtro.toUpperCase().contains(gasto.toUpperCase())){
					this.filtro.add(filtro);
				}
				
			}
		}
		if(filtro.isEmpty())
			filtro.add(gasto);
		return filtro;
	}

	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}

	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}
	
	
	
	

}
