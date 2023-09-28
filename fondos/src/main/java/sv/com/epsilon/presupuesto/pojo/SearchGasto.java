/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.Calendar;
import java.util.List;

import lombok.Data;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Proveedor;

/**
 * @author martinezc
 *
 */
@Data
public class SearchGasto {

	/**
	 * 
	 */
	
	private String textProveedor;
	private Proveedor proveedor;
	private Double monto;
	private String numeroCheque;
	private Calendar fechaInicial;
	private Calendar fechaFinal;
	private String description;
	private List<Categoria> listCategoria;
	private List<GastoExt> gastos;
	private String tipo;
	private Presupuesto presupuesto;
	private boolean revertido=false;
	private boolean all=true;
	
	public SearchGasto() {
		
	}
	
	public boolean getRevertido() {
		return revertido;
	}
	
	
	
	
	

}
