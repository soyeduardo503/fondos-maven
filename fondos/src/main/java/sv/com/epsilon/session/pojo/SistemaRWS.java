/**
 * 
 */
package sv.com.epsilon.session.pojo;

/**
 * @author 50364
 *
 */
public class SistemaRWS implements RequestWS{

	private String context;

	public SistemaRWS() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SistemaRWS(String context) {
		super();
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
}
