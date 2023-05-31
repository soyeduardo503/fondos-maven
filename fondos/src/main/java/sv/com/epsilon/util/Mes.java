/**
 * 
 */
package sv.com.epsilon.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Zeta
 *
 */
public class Mes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String nombre;
	private int idMes;
	private String valor;
	private final static List<String> list=init();
	private final static List<Mes> all=initAll();

	private Mes() {
		
	}
	
	

	private static List<Mes> initAll() {
		List<Mes> list=new ArrayList<>();
		for(int i=1;i<13;i++) {
			list.add(new Mes(i));
		}
		return list;
	}



	private static List<String> init() {
		String[] list=new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		
		return Stream.of(list).toList();
	}

	public Mes(int idmes) {
		this.idMes=idmes;
		nombre=list.get(idmes-1);
	}
	
	public Mes(String nombre, String valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	public Mes(String nombre, int idMes) {
		super();
		this.nombre = nombre;
		this.idMes = idMes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdMes() {
		return idMes;
	}

	public void setIdMes(int idMes) {
		this.setNombre(list.get(idMes-1));
		this.idMes = idMes;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Mes [nombre=" + nombre + ", idMes=" + idMes + ", valor=" + valor + "]";
	}
	
	public static List<Mes> untilCurrentMonth(){
		
		return all.stream().filter(m->m.idMes<=Calendar.getInstance().get(Calendar.MONTH)).toList();
	}
	
	
	public static List<Mes> all(){
		return all;
	}
	

}