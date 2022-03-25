/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.Date;

import sv.com.epsilon.entities.Evento;
import sv.com.epsilon.facade.EventoFacade;

/**
 * @author usuario07
 *
 */
public class EventoCtrlr {

	public static final String ingreso="I";
	public static final String egreso="E";
	public static final String ingresoUsuario="IU";
	public static final String creacionPresupuesto="CP";
	public static final String creacionCategoria="CC";
	
	public EventoCtrlr(){
		
	}
	
	public void registrarEvento(String descripcion, String tipo){
		Evento e=new Evento();
		e.setDescripcion(descripcion);
		e.setFechahora(new Date());
		e.setTipo(tipo);
		try {
			new EventoFacade().save(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
