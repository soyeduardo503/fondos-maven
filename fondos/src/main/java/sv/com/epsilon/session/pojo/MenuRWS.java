/**
 * 
 */
package sv.com.epsilon.session.pojo;

/**
 * @author 50364
 *
 */
public class MenuRWS implements RequestWS{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String context;
	private Integer idRol;
	public MenuRWS(String context, Integer idRol) {
		super();
		this.context = context;
		this.idRol = idRol;
	}
	public MenuRWS() {
		super();
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
	
	
	
	
}
