/**
 * 
 */
package sv.com.epsilon.session.pojo;

/**
 * @author 50364
 *
 */
public class TokenValidRWS implements RequestWS {

	/**
	 * 
	 */
	private String token;
	private String context;
	public TokenValidRWS() {
		
	
	}
	
	
	public TokenValidRWS(String token, String context) {
		super();
		this.token = token;
		this.context = context;
	}


	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	
	

}
