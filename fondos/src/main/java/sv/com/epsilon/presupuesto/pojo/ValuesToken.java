/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author martinezc
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValuesToken {

	private String values;
	private String token;
	
	
	public String value(String value) throws JsonMappingException, JsonProcessingException {
		ObjectMapper object=new ObjectMapper();
		TypeReference<HashMap<String, String>> typeRef 
		  = new TypeReference<HashMap<String, String>>() {};
		return  object.readValue(values, typeRef).get(value);
	 
	}
}
