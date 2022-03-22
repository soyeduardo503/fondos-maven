/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zeta
 */

public class Tipodesembolso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idTipoDesembolso;
    private String tipoDesembolso;
    private String nombre;
    private String act;
    
    private Integer idEmpresa;
    private List<Gasto> gastoList;

    public Tipodesembolso() {
    }

    
    public Integer getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public Tipodesembolso(Integer idTipoDesembolso) {
        this.idTipoDesembolso = idTipoDesembolso;
    }

    public Integer getIdTipoDesembolso() {
        return idTipoDesembolso;
    }

    public void setIdTipoDesembolso(Integer idTipoDesembolso) {
        this.idTipoDesembolso = idTipoDesembolso;
    }

    public String getTipoDesembolso() {
        return tipoDesembolso;
    }

    public void setTipoDesembolso(String tipoDesembolso) {
        this.tipoDesembolso = tipoDesembolso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    @XmlTransient
    public List<Gasto> getGastoList() {
        return gastoList;
    }

    public void setGastoList(List<Gasto> gastoList) {
        this.gastoList = gastoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDesembolso != null ? idTipoDesembolso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodesembolso)) {
            return false;
        }
        Tipodesembolso other = (Tipodesembolso) object;
        if ((this.idTipoDesembolso == null && other.idTipoDesembolso != null) || (this.idTipoDesembolso != null && !this.idTipoDesembolso.equals(other.idTipoDesembolso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Tipodesembolso[ idTipoDesembolso=" + idTipoDesembolso + " ]";
    }
    
}
