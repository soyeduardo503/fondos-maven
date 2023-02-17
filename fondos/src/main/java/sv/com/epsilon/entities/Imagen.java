/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;

import sv.com.epsilon.ctrlr.annotation.Id;

/**
 *
 * @author Zeta
 */

public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer idURL;
    
    private String url;
    
    private byte[] alt;
    
    private String act;
    
    private Gasto idGasto;

    public Imagen() {
    }
    
    public Imagen(byte[] alt,Gasto gasto) {
    	this.alt=alt;
    	this.idGasto=gasto;
    }

    public Imagen(Integer idURL) {
        this.idURL = idURL;
    }

    public Integer getIdURL() {
        return idURL;
    }

    public void setIdURL(Integer idURL) {
        this.idURL = idURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getAlt() {
        return alt;
    }

    public void setAlt(byte[] alt) {
        this.alt = alt;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    

    public Gasto getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(Gasto idGasto) {
		this.idGasto = idGasto;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idURL != null ? idURL.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen) object;
        if ((this.idURL == null && other.idURL != null) || (this.idURL != null && !this.idURL.equals(other.idURL))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Imagen[ idURL=" + idURL + " ]";
    }
    
}
