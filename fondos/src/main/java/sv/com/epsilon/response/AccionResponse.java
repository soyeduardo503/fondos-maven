/**
 * 
 */
package sv.com.epsilon.response;

/**
 * @author 50364
 *
 */

public class AccionResponse implements Response{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2067841808957798002L;
	private Integer status=-1;
	public AccionResponse(String status) {
		super();
		this.status = Integer.parseInt(status);
	}
	
	public AccionResponse(Integer status) {
		super();
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
}
