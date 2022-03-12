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
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "Banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT c FROM Banco c WHERE c.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Banco.findByAct", query = "SELECT c FROM Banco c where c.act=:act and c.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Banco.findByIdBanco", query = "SELECT c FROM Banco c WHERE c.idBanco = :idBanco and c.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Banco.findByName", query = "SELECT c FROM Banco c WHERE c.nombre = :name and c.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Banco.findByIdEmpresa", query = "SELECT c FROM Banco c WHERE c.idEmpresa = :idEmpresa")}
    )
public class Banco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBanco")
    private Integer idBanco;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "act")
    private String act;
    
    
    
    @Column(name = "idEmpresa")
    private Integer idEmpresa;

    public Banco() {
    }

    public Banco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Banco(Integer idBanco, String nombre) {
        this.idBanco = idBanco;
        this.nombre = nombre;
        
    }

    public Banco(Integer idBanco, Integer idEmpresa) {
		this.idBanco=idBanco;
		this.idEmpresa=idEmpresa;
	}

	public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
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
	

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idBanco != null ? idBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.idBanco == null && other.idBanco != null) || (this.idBanco != null && !this.idBanco.equals(other.idBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Banco[ idBanco=" + idBanco + " ]";
    }
    
}
