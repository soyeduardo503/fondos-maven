/**
 * 
 */
package sv.com.epsilon.application;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import sv.com.epsilon.entities.Parametro;
import sv.com.epsilon.entities.Tipodesembolso;
import sv.com.epsilon.facade.ParametroFacade;
import sv.com.epsilon.facade.TipodesembolsoFacade;

/**
 * @author Zeta
 *
 */

public class  CatalogosApp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6307550492708654480L;
	/**
	 * 
	 */
	
	
	private static final List<Parametro> parametros=new ParametroFacade().findAllActive();
	
	private static final List<Tipodesembolso> tipodesembolsos=new TipodesembolsoFacade().findAllActive();
	
	private static final HashMap<String,Object> parametrosSistema=initPSistema();
	
	private Integer idEmpresa;
	
	public  CatalogosApp() {
		
			
			
		
	}
	
	public  CatalogosApp(Integer idEmpresa) {
		
			this.idEmpresa=idEmpresa;
			
		
	}
	private static HashMap<String, Object> initPSistema() {
		HashMap<String,Object> datos= new HashMap<String, Object>();
		for(Parametro parametro:parametros) {
			datos.put(parametro.getNombre(), parametro);
		}
		return datos;
	}
	

	/*metodo de inicio de los catalogos para la aplicacion */
	
	
	
	public List<Tipodesembolso> getInstanceofTipoDesembolso(){
		return tipodesembolsos;
	}
	
	public List<Tipodesembolso> getInstanceofParametros(){
		return tipodesembolsos;
	}
	

	
	
	public Parametro obtenerParametro(String nombreParametro) {
		return (Parametro) parametrosSistema.get(nombreParametro);
	}
	
	public String obtenerValorParametro(String nombreParametro) {
		return ((Parametro) parametrosSistema.get(nombreParametro)).getValor();
	}
	
}
