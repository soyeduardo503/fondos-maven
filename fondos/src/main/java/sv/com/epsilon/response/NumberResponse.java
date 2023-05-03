/**
 * 
 */
package sv.com.epsilon.response;

/**
 * @author martinezc
 *
 */
public class NumberResponse {

	private Integer cod=0;
	private Object value;
	private String description;
	/**
	 * 
	 */
	
	
	public NumberResponse() {
		
	}
	
	
	
	public NumberResponse(Object value, String description) {
		super();
		this.value = value;
		this.description = description;
	}



	public NumberResponse(Integer cod, Object value, String description) {
		super();
		this.cod = cod;
		this.value = value;
		this.description = description;
	}
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



	public int getIntValue() {
		return Integer.parseInt(value.toString());
		
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(value.toString());
	}
	

}
