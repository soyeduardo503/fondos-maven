/**
 * 
 */
package sv.com.epsilon.util;

import java.io.Serializable;

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

	public Mes() {

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
	
	

}