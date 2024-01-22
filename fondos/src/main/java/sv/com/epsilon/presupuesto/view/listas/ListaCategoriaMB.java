/**
 * 
 */
package sv.com.epsilon.presupuesto.view.listas;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.facade.CategoriaFacade;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
@Slf4j
public class ListaCategoriaMB {

	/**
	 * 
	 */
	private List<Categoria> list;
	private String cod="";
	
	public ListaCategoriaMB() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void load() {
		
			list=new CategoriaFacade().children(cod);
		
	}
	public void load(String cod) {
		this.cod=cod;
		load();
	}

}
