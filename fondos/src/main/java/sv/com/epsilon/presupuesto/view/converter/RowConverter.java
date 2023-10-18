/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @author martinezc
 *
 */
public class RowConverter <T>{

	/**
	 * 
	 */
	private Class t; 
	
	public RowConverter() {
		
	}
	
	public Integer[] positions;
	public String[] methods;
	public Class[] types;
	
	public void toMapped(T object) {
		t=object.getClass();
		
		Stream<Field> fields = Stream.of( t.getDeclaredFields()).filter(field->!field.getName().equalsIgnoreCase("serialVersionUID"));
		fields.forEach((f)-> readField(f));
		
	}

	private Object readField(Field field) {
		// TODO Auto-generated method stub
		//field.getAnnotation(                annotations.class)
		
		return null;
	}
	

}
