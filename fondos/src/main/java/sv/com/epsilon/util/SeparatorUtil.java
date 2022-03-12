/**
 * 
 */
package sv.com.epsilon.util;

/**
 * @author usuario07
 *
 */
public class SeparatorUtil {

	/**
	 * 
	 */
	
	private static String separaCaracter=System.getProperty("file.separator");
	
	
	private static String separadorLinux="/";
	
	public SeparatorUtil() {
		
	}
	
	public static String  replace(String url){
		if(!separaCaracter.equals(separadorLinux))
			return url.replaceAll("/",separaCaracter+separaCaracter);
		else
			return url;
	}

}
