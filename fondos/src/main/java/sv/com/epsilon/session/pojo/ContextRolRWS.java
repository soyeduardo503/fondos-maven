/**
 * 
 */
package sv.com.epsilon.session.pojo;

import java.io.Serializable;

/**
 * @author 50364
 *
 */
public class ContextRolRWS implements Serializable,RequestWS{

	private String token;
	private String context;
	private Integer idRol;
	
	
	public ContextRolRWS() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ContextRolRWS(String context, Integer idRol) {
		super();
		this.context = context;
		this.idRol = idRol;
	}


	public ContextRolRWS(String token, String context, Integer idRol) {
		this.context = context;
		this.idRol = idRol;
		this.token=token;
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


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	
}
