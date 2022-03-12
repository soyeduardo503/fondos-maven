/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.ctrlr.session;

import java.io.Serializable;

/**
 *
 * @author eduardx
 */

public class UserSessionToken implements Serializable {

    private static final long serialVersionUID = 1L;
   

    private String user;
    private String rol;
    private String context;
    private String act;
	
	

	

    public UserSessionToken() {
		super();
		// TODO Auto-generated constructor stub
	}





	public UserSessionToken(String user, String rol, String context, String act) {
		super();
		this.user = user;
		this.rol = rol;
		this.context = context;
		this.act = act;
	}





	public String getUser() {
		return user;
	}





	public void setUser(String user) {
		this.user = user;
	}





	public String getRol() {
		return rol;
	}





	public void setRol(String rol) {
		this.rol = rol;
	}





	public String getContext() {
		return context;
	}





	public void setContext(String context) {
		this.context = context;
	}





	public String getAct() {
		return act;
	}





	public void setAct(String act) {
		this.act = act;
	}





	@Override
    public String toString() {
        return "sv.com.epsilon.entities.User[ idUser=" + user + " ]";
    }
    
}
