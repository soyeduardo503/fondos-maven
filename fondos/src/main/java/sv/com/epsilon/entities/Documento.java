/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "documento", schema = "epsilon")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
	@NamedQuery(name = "Documento.findAll", query = "SELECT c FROM Documento c Where c.idEmpresa=:idEmpresa"),
	@NamedQuery(name = "Documento.findByAct", query = "SELECT c FROM Documento c where c.act=:act and c.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Documento.findByName", query = "SELECT c FROM Documento c WHERE c.nombre=:nombre and c.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Documento.findByCod", query = "SELECT c FROM Documento c WHERE c.codDocumento = :cod and c.idEmpresa=:idEmpresa")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocumento")
    private Integer idDocumento;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "CodDocumento")
    private String codDocumento;
    @Basic(optional = false)
    @Column(name = "Extension")
    private String extension;
    @Basic(optional = false)
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @Column(name = "Act")
    private String act;
   
    @Basic(optional = false)
    @Column(name = "idEmpresa")
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
