/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "imagen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagen.findAll", query = "SELECT i FROM Imagen i"),
    @NamedQuery(name = "Imagen.findByIdURL", query = "SELECT i FROM Imagen i WHERE i.idURL = :idURL"),
    @NamedQuery(name = "Imagen.findByIdGasto", query = "SELECT i FROM Imagen i WHERE i.idGasto = :idGasto"),
    
    @NamedQuery(name = "Imagen.findByUrl", query = "SELECT i FROM Imagen i WHERE i.url = :url"),
    @NamedQuery(name = "Imagen.findByAlt", query = "SELECT i FROM Imagen i WHERE i.alt = :alt"),
    @NamedQuery(name = "Imagen.findByAct", query = "SELECT i FROM Imagen i WHERE i.act = :act")})
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idURL")
    private Integer idURL;
    @Column(name = "URL")
    private String url;
    @Column(name = "Alt")
    private String alt;
    @Column(name = "Act")
    private String act;
    @JoinColumn(name = "idGasto", referencedColumnName = "idGasto")
    @ManyToOne(optional = false)
    private Gasto idGasto;

    public Imagen() {
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

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
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
