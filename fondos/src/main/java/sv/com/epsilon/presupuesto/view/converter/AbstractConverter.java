/**
 * 
 */
package sv.com.epsilon.presupuesto.view.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;



/**
 * @author usuario07
 *
 */
public abstract class AbstractConverter<K,T> {

	private Class<T> c;
	private Class<K> k;
	private T facade;
	private K itemSelected;
	/**
	 * 
	 */
	public AbstractConverter(Class<K> k,Class<T> c) {
		this.c=c;
		this.k=k;
		
	}
	
	public K find(Object key) {
		try {
			initFacade();
		//	Object key=getKey(entity);
			String callMethodfindAll="findById";
			 return (K) facade.getClass().getMethod(callMethodfindAll, new Class[]{Integer.class}).invoke(facade, new Object[]{Integer.parseInt(key.toString())});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getKey(Object obj){
		System.out.println(obj);
		if(obj!=null){
			;
			Field[] fields= obj.getClass().getDeclaredFields();
			System.out.println(fields.length);
			if(fields.length>0)
			for(Field f: fields){
				Annotation[] anotacions = f.getAnnotations();
				if(anotacions.length>0){ 
					for(Annotation a:anotacions){
						System.out.println(a.annotationType().getName());
						System.out.println(a.annotationType().getSimpleName());
						if(a.annotationType().getSimpleName().equals("Id")){
							//.isInstance( javax.persistence.Id.class)){
							return getValue(obj,f);
						}
					}
				}
			}
		}
		//System.out.println("No se encontro Key en el objeto" +obj);
		return "";
	}
	
	public String getKeyString(Object obj){
		Object key=getKey(obj);
		return new StringBuilder().append(key!=null?key:"").toString();
	}
	
	private Object getValue(Object obj,Field f){
		 try {
			 f.setAccessible(true);
			return f.get(obj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	 }
	
	private void initFacade() throws InstantiationException, IllegalAccessException{
		if(facade==null)
			facade=c.newInstance();
	}
	

}
