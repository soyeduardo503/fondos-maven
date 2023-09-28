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
@AllArgsConstructor
@NoArgsConstructor
public class SearchDetalleGastoMes {

	/**
	 * 
	 */
	
	private Boolean all;	
	private Categoria categoria;
	private Presupuesto presupuesto;
	private Boolean acumulado;
	private List<DetalleGastoMes> list;
	
	
}
