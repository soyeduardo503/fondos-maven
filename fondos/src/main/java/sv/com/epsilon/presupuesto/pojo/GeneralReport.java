/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.List;

import lombok.Data;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Cierre;

/**
 * 
 */
@Data
public class GeneralReport {

	/**
	 * 
	 */
	private String title;
	private List<Categoria> items;
	private List<Cierre> listCierre;
	private List<GastoReal> list;
	private Double gastoLastMonth;
	private Double gastoProyeccion;
	private Double ingresosTotales;
	private Double gastosTotal;
	private Double gastoUltimoMes;
	
	

}
