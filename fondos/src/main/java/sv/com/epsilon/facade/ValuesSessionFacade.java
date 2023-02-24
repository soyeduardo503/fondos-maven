/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.Map;
import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.presupuesto.pojo.ValuesToken;

/**
 *
 * @author Zeta
 */

public class ValuesSessionFacade extends WSClient<ValuesToken> {


    public ValuesSessionFacade() {
        super(ValuesToken.class,"localhost","8181","WSEpsilonUSER",true);
        setToken( getValueFromCookie("token").get().getValue());
    }
    
    public Optional<Cookie> getValueFromCookie(String key) {
		Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
		return  cookies.get(key)!=null?Optional.of((Cookie)cookies.get(key)):Optional.empty();
//		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//	    Cookie cookie = null;
//
//	    Cookie[] userCookies = request.getCookies();
	}
    
   
    public Optional<ValuesToken> fetch(){
    	Optional<ValuesToken> value=get("/values/previous/"+getToken());
    	
    	return   			value;
    }
    
}
