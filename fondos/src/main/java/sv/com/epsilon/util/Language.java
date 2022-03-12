/**
 * 
 */
package sv.com.epsilon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author eduardx
 *
 */


public class Language {

	/**
	 * 
	 */

	public static final String US="EN-US";
	public static final String ESA="ES-SV";
	public static final String GER="GER-GER";
	public static final String FR="FR-FR";
	public static final String defaultLang="ES-SV";
	public String selected=defaultLang;
	private HashMap<String, String> values=new HashMap<>();
	
	
	
	public Language() {

	}
	
	
	public Language(String selected) {
		super();
		this.selected = selected;
		this.load();
	}


	public String getSelected() {
		return selected;
	}


	public void setSelected(String selected) {
		this.selected = selected;
	}


	public void load() {
		String file="C:\\files\\lang\\:lang:.properties";
		if(selected==null) {
			selected=defaultLang;
		}
		file=file.replace(":lang:", selected);
		   try (InputStream input = new FileInputStream(file)) {
			   	
	            Properties prop = new Properties();
	            prop.load(input);
	            prop.forEach((key, value) -> values.put(key.toString(), value.toString()));

	            prop.load(input);
		   }catch (Exception e) {
			
		}
		
	}
	
	public boolean load(String lang) {
		String file="C:\\files\\lang\\:lang:.properties";
		
		file=file.replace(":lang:", lang);
		File fexist=new File(file);
		if(!fexist.exists())
			return false;
		   try (InputStream input = new FileInputStream(file)) {
			   	
	            Properties prop = new Properties();
	            prop.load(input);
	            prop.forEach((key, value) -> values.put(key.toString(), value.toString()));

	            prop.load(input);
	            return true;
		   }catch (Exception e) {
			   e.printStackTrace();
			return false;
		}
		
	}
	
	
	public String getValue(String k) {
		if(values.isEmpty())
			load();
		return values.get(k)!=null?values.get(k):"N/F";
	}

}
