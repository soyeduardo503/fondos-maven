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
	private String desc="";
	
	public AccionResponse() {
		
	}
	 
	public AccionResponse(String status) {
		super();
		this.status = Integer.parseInt(status);
		desc=(this.status==0?"OK":"error");
	}
	
	public AccionResponse(Integer status, String desc) {
		super();
		this.status = status;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	public boolean isOk() {
		return status==0;
	}
	
}
