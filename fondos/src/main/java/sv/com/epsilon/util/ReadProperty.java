package sv.com.epsilon.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class ReadProperty implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5390241516493354017L;
	String nombre;
	String propiedad;
	static Properties proper;
	public ReadProperty() {
		
	}

	public ReadProperty(String nombre,String propiedad) {
		this.nombre=nombre;
		this.propiedad=propiedad;
	}
	 
	 
	 
	 

	 public static void create(){
		 proper = new Properties();
		 
	    	try {
	    		//set the properties value
	    		proper.setProperty("fecha", "dd/MM/yyyy HH:mm");
	    		
	 
	    		//save properties to project root folder
	    		proper.store(new FileOutputStream("config.properties"), null);
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
	 }
	 
	 public  String read(String path,String pro){
		 Properties prop = new Properties();
		 
	    	try {
	               
	    	
	    		prop.load(ReadProperty.class.getClassLoader().getResourceAsStream(path));	 
	               
	             return prop.getProperty(pro); 
	 
	    	} catch (IOException ex) {
	    		Log.error("Error en ejecucion",ex);
	    		ex.printStackTrace();
	    		return "";
	        }
	    	
	 }
	 
	 public static String readProperty(String path,String pro){
		 proper = new Properties();
		 
	    	try {
	               
	    	
	    		proper.load(ReadProperty.class.getClassLoader().getResourceAsStream(path));	 
	               
	             return proper.getProperty(pro); 
	 
	    	} catch (IOException ex) {
	    		Log.error("Error en ejecucion",ex);
	    		
	    		ex.printStackTrace();
	    		return "";
	        }
	    	
	 }
	 
	 @Override
	 public String toString(){
		 try{
			 return read(nombre,propiedad);
		 }finally {
			 nombre=null;
			 propiedad=null;
		 }
	 }


}
