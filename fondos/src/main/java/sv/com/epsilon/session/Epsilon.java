/**
 * 
 */
package sv.com.epsilon.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import sv.com.epsilon.util.Language;
import sv.com.epsilon.util.Log;

/**
 * @author 50364
 *
 */
public class Epsilon implements Serializable {

	/**
	 * 
	 */

	private HashMap<String, Object> values = new HashMap<String, Object>();

	private String language = "ES-SV";
	private String mensaje = "";
	private boolean valido = true;
	
	
	private Language lang=new Language(Language.defaultLang);

	public Epsilon() {
		
	}
	
	public String getValueFromCookie(String key) {
		Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
		return  cookies.get(key)!=null?cookies.get(key).toString():"";
//		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//	    Cookie cookie = null;
//
//	    Cookie[] userCookies = request.getCookies();
	}
	
	 public void setCookie(String key,String value) {
		 HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		 Cookie oreo = new Cookie(key, value);
		 //oreo.setDomain("epsilon-sv.com");
		 oreo.setMaxAge(2419200);
		 response.addCookie(oreo);
	 }
	
	public Object getCookie(String key) {
		Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
		return  cookies.get(key);
//		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//	    Cookie cookie = null;
//
//	    Cookie[] userCookies = request.getCookies();
	}
	

	public Language getLang() {
		return lang;
	}

	public void setLang(Language lang) {
		this.lang = lang;
	}

	public void addValue(String key, Object value) {
		this.values.put(key, value);
	}

	public Object getValue(String key) {
		return getValues().get(key);
	}

	public String getToken() {
		return (values.get("token")).toString();
	}
	
	public Integer getIdEmpresa() {
		return (Integer) (values.get("idEmpresa")!=null?values.get("idEmpresa"):1);
	}

	public String getContext() {
		if( values.get("context")!=null)
			return values.get("context").toString();
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getContextName();
		String allPath=FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		Log.info("->"+contextPath);
		Log.info("->"+allPath);

		return contextPath;
		
	}

	public String getUser() {
		return values.get("user").toString();
	}

	public Integer getIdRol() {
		return Integer.parseInt(values.get("idRol").toString());
	}
	
	public Integer getIdUser() {
		return  Integer.parseInt( values.get("idUser").toString());
	}

	

	public HashMap<String, Object> getValues() {
		return values;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public void setValues(HashMap<String, Object> values) {
		this.values = values;
	}

	
	
	
	

}
