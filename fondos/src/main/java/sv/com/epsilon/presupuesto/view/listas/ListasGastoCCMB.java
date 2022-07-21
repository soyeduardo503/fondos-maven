/**
 * 
 */
package sv.com.epsilon.presupuesto.view.listas;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.entities.Cajachica;
import sv.com.epsilon.entities.Gasto;

/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListasGastoCCMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Gasto> list;
	

	public void loadGastos(Cajachica cc) {
		
	}


	
	
	
}
