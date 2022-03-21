package sv.com.epsilon.util;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

//    private static final SessionFactory sessionFactory = buildSessionFactory();
//    private static ServiceRegistry serviceRegistry;
//
//    private static SessionFactory buildSessionFactory() {
//    	try {
//
//    		File fileContext = null;
//    		
//    		fileContext=new File("C:/files/db/hibernate.cfg.fondos.xml");
//    		Log.info(fileContext.getAbsolutePath());
//    		Log.info("Exist ->"+fileContext.exists());
//    		if(!fileContext.exists())
//    			fileContext=new File("/opt/epsilon/db/hibernate.cfg.epsilon.fondos.xml");
//			return    new Configuration().configure(fileContext).buildSessionFactory();
//    		//return new Configuration().configure().buildSessionFactory();
//    			
//    	} catch (Exception ex) {	
//    		Log.error(ex, "ERror al inicar conexion a bd");
//    		System.err.println("Initial SessionFactory creation failed." + ex);
//    		throw new ExceptionInInitializerError(ex);
//    	}
//    }
//
//    public static SessionFactory getSessionFactory() {
//    	
//        return sessionFactory;
//    }
//    
//    public static void shutdown() {
//    	// Close caches and connection pools
//    	getSessionFactory().close();
//    }

}
