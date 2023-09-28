/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;

/**
 * @author martinezc
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchParameterGastoMensual {

	/**
	 * 
	 */
	private Presupuesto Presupuesto;
	private boolean accumulated=true;
	private Categoria categoria;
	private List<DetalleGastoMes> list;
	

}
