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

public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer idDocumento;

    private String nombre;

    private String descripcion;

    private String codDocumento;

    private String extension;

    private String url;

    private String act;
   

    private Integer idEmpresa;

    public Documento() {
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
   
   

    public Integer getIdDocumento() {
		return idDocumento;
	}



	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}



	public String getCodDocumento() {
		return codDocumento;
	}



	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
	}



	public String getExtension() {
		return extension;
	}



	public void setExtension(String extension) {
		this.extension = extension;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

   

    public Integer getIdEmpresa() {
		return idEmpresa;
	}



	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}



	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Documento[ idDocumento=" + idDocumento + " ]";
    }
    
}
