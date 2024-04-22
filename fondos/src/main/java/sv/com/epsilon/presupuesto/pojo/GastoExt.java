/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import sv.com.epsilon.entities.Catingreso;
import sv.com.epsilon.entities.Financiamiento;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Proveedor;

/**
 * @author martinezc
 *
 */
@Data
public class GastoExt extends Gasto {

	public GastoExt() {
		
	}
	
	private Double Abono;
	@JsonIgnore
	private Double saldo;
	private String typeTransaccion="S";
	public GastoExt(Gasto g) {
			
			super(g.getIdGasto(), g.getNombre(), g.getDescripcion(), g.getNombrePantalla(), g.getAct(), g.getCodigo(), g.getCheque(),
					g.getTotal(),g.getIdEmpresa(),  g.getFecha(), g.getFechaRegistro(), g.getReciboList(),g.getImagenList(), g.getMovimientoList(), 
					g.getIdTipoDesembolso(), g.getIdProveedor(), g.getKpresupuesto(), g.getStatus(), g.getNumeroComprobante());
			typeTransaccion="S";
			this.setMovimientoList(g.getMovimientoList());
	}
		
		public GastoExt(Financiamiento f,Optional<Catingreso> nombre) {
			if(nombre.isPresent())
				this.setIdProveedor(new Proveedor(nombre.get().getDescripcion(),nombre.get().getDescripcion()));
			this.setDescripcion(f.getNombre());
			this.setTotal(f.getMonto());
			this.setAbono(f.getMonto());
			Calendar c=Calendar.getInstance();
			c.setTime(f.getFecha());
			this.setFecha( c);
			this.setStatus("T");
			typeTransaccion="E";
			setMovimientoList(new ArrayList<>());
		}
		
		public Date dateFromFecha() {
			return this.getFecha().getTime();
		}
}
