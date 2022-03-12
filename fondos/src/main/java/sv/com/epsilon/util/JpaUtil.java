package sv.com.epsilon.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    
//	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("eBudget");
//	
//
//    public  static EntityManager getEntityManager() {
//    	try {
//    		
//    		return emf.createEntityManager();
//    		//return new Configuration().configure().buildSessionFactory();
//    			
//    	} catch (Throwable ex) {	
//    	
//    		System.err.println("Initial SessionFactory creation failed." + ex);
//    		throw new ExceptionInInitializerError(ex);
//    	}
//    }
//
//    
//    
//    public static void shutdown() {
//    	// Close caches and connection pools
//    	emf.close();
//    }
//    
//public static void print(Object ob) {
//	if(ob==null){
//		Log.info("Objeto nulo");
//		return ;
//	}
//	Class<? extends Object> typeClass = ob.getClass();
//		try {
//			Log.info("********************************");
//			Log.info("Object [Clase] : "+typeClass.getSimpleName());
//			ArrayList<Field> campos=new ArrayList<Field>(Arrays.asList(typeClass.getDeclaredFields()));
//			
//			campos.addAll(Arrays.asList( typeClass.getSuperclass().getDeclaredFields()));
//			
//			
//			
//			
//			
//			for (int i = 0; i < campos.size(); i++) {
//				campos.get(i).setAccessible(true);
//			
//				{
//					Log.info(campos.get(i).getName()+"-> "+campos.get(i).get(ob));			
//
//				}
//
//			}
//
//			
//		}
//		catch(Exception e){
//			Log.error( "error al acceder a los atributos del objeto "+ typeClass.getSimpleName(),e);	
//		
//		}
//		finally {
//		
//			
//		}
//	}

}
