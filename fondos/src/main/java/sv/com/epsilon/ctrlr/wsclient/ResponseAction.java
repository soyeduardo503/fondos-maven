/**
 * 
 */
package sv.com.epsilon.ctrlr.wsclient;

/**
 * @author martinezc
 *
 */
public class ResponseAction<T> {

	private Integer cod=0;
	private String descripcion="";
	private T persist;
	
	
	
	public ResponseAction() {
		super();
	}
	public ResponseAction(Integer cod, String descripcion) {
		super();
		this.cod = cod;
		this.descripcion = descripcion;
	}
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public T getPersist() {
		return persist;
	}
	public void setPersist(T persist) {
		this.persist = persist;
	}
	
	
	
}
