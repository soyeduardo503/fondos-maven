/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import javax.faces.bean.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;

/**
 * @author martinezc
 *
 */
@ManagedBean
@RequestScope
public class Colors {

	
	
	public String getColor(String name) {
		return sv.com.epsilon.util.Colors.valueOf(name).toString();
	}
}
