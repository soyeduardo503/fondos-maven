/**
 * 
 */
package sv.com.epsilon.presupuesto.view.listas;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.presupuesto.pojo.CountAcount;

/**
 * @author martinezc
 *
 */
@ManagedBean
@ViewScoped
@Data
public class ListaCodigoCuentasMB {

	/**
	 * 
	 */
	private List<CountAcount> list;
	public ListaCodigoCuentasMB() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			list=(List<CountAcount>) new CategoriaFacade().getList("/countByCode/", CountAcount.class);
		}
	}

	public CountAcount get(String code) {
		return list.stream().filter(ca->ca.getCode().equalsIgnoreCase(code)).findFirst().orElse(new CountAcount(code, 0));
	}
}
