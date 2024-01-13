/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.ctrlr.annotation.ClassTarget;
import sv.com.epsilon.ctrlr.annotation.Position;
import sv.com.epsilon.entities.Gasto;

/**
 * @author martinezc
 *
 */
@ClassTarget(value = Gasto.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoLoad {

	/**
	 * 
	 */
	
	@Position(methodTarget = "setCheque",classTarget = Gasto.class,type = String.class)
	private String noTransfer;
	
    private String mes;
    @Position(methodTarget = "setFecha",classTarget = Gasto.class,type = Date.class)
    private String fecha;    
    private String categoria;
    
    private String codigo;
    
    private String oldcodigo;
    
    private String descripcionCodigo;
    @Position(methodTarget = "setNumeroComprobante",classTarget = Gasto.class,type = String.class)
    private String numSujExc;
    
    private String montoAbono;
    @Position(methodTarget = "setTotal",classTarget = Gasto.class,type = Double.class)
    private Double montoGasto;
    private String proveedor;
    @Position(methodTarget = "setDescripcion",classTarget = Gasto.class,type = String.class)
    private String concepto;
    private Integer idProveedor;
    
    private double saldo;
	

    public Calendar date() throws ParseException {
    	Calendar c=Calendar.getInstance();
    	Date d=new SimpleDateFormat("dd/MM/yyyy").parse(getFecha());
    	c.setTime(d);
    	return c;
    }
}
