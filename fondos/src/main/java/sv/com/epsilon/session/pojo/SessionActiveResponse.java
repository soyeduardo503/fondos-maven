/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.session.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author eduardx
 */

public class SessionActiveResponse implements Serializable, ResponseWS {

    private static final long serialVersionUID = 1L;

    private Integer idSessionActive;
    
    private String mac;
    
    private String user;
    
    
    private Integer idRol;
    
    
    private String context;
    
    private Integer idEmpresa=1;
    private Integer idUser;
    
     
       
    
    private String act;
       

    
    private String token;
    

    public SessionActiveResponse() {
    }


    
    

	public Integer getIdSessionActive() {
		return idSessionActive;
	}





	public void setIdSessionActive(Integer idSessionActive) {
		this.idSessionActive = idSessionActive;
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








	public String getMac() {
		return mac;
	}





	public void setMac(String mac) {
		this.mac = mac;
	}





	public String getUser() {
		return user;
	}





	public void setUser(String user) {
		this.user = user;
	}





	public Integer getIdRol() {
		return idRol;
	}





	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}









	public String getToken() {
		return token;
	}





	public void setToken(String token) {
		this.token = token;
	}


	
	



	public Integer getIdUser() {
		return idUser;
	}





	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}





	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idSessionActive != null ? idSessionActive.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionActiveResponse)) {
            return false;
        }
        SessionActiveResponse other = (SessionActiveResponse) object;
        if ((this.idSessionActive == null && other.idSessionActive != null) || (this.idSessionActive != null && !this.idSessionActive.equals(other.idSessionActive))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.entities.SessionActives[ idSessionActive=" + idSessionActive + " ]";
    }





	public Integer getIdEmpresa() {
		return idEmpresa;
	}





	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}








    
}
