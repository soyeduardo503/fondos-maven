/**
 * 
 */
package sv.com.epsilon.util;

import org.apache.log4j.Logger;

/**
 * @author usuario07
 *
 */

public class Log {

	/**
	 * 
	 */
	public Log() {
	
	}

	public static Logger log = Logger.getLogger("epsilon");
	
	
	public static void Message(String msj){
		if(msj!=null){
			if(!msj.startsWith("iniciando prerender")){
				//Log.info(msj);
				log.info(msj);
			}
		}
		else
			Log.info("Mensaje vacio");
			
	}
	public static void Message(Object msj){
	//	Log.info(msj==null?null:msj.toString());
		log.info(msj==null?null:msj.toString());
		
	}
	public static void info(Object msj){
		Message(msj);
		
	}
	public static void info(String msj){
		Message(msj);
	}
	
	public static void error( Exception e,String msj){
		Error(msj,e);
	}
	public static void error(String msj, Exception e){
		Error(msj,e);
	}
	public static void Error(String msj, Exception e){
		
		log.error(msj, e);
		try{
			e.printStackTrace();
		}finally{
			System.err.println(msj+" "+e.getMessage());
		}
	}
	
	public static void warm(String st, Exception e){
		//Log.info(st+e.getMessage());
		log.warn(st, e);
		
	}
	
	public static void debug(String st){
		//Log.info(st+e.getMessage());
		log.debug(st);
		
	}
}
