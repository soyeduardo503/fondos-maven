/**
 * 
 */
package sv.com.epsilon.session.pojo;

/**
 * @author 50364
 *
 */
public class Token {

	/**
	 * 
	 */
	private String context;
	private boolean valid=false;
	public Token() {
		// TODO Auto-generated constructor stub
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	

}
