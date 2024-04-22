/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.entities.Categoria;

/**
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresupuestoReport {

	/**
	 * 
	 */
	private Double porcentajeGastado;
	private Double gastoTotal;
	private Double ingresoTotal;
	private Double gastoUltimoMes;
	private List<Categoria> listCategoria;
	private List<IndicadorGastoReal> listGastoReal;
	private String title;


}
