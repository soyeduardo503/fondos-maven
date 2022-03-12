/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT r FROM Evento r where r.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Evento.findByidEvento", query = "SELECT r FROM Evento r WHERE r.idEvento = :idEvento"),
    @NamedQuery(name = "Evento.findByName", query = "SELECT r FROM Evento r WHERE r.descripcion = :descr and r.idEmpresa=:idEmpresa "),
    @NamedQuery(name = "Evento.findByidEmpresa", query = "SELECT r FROM Evento r WHERE r.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Evento.findByTipo", query = "SELECT r FROM Evento r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Evento.findByFechaHora", query = "SELECT r FROM Evento r WHERE r.fechahora = :fecha")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEvento")
    private Integer idEvento;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "fechahora")
    @Temporal(TemporalType.DATE)
    private Date fechahora;
    @Column(name = "descripcion")
    private String descripcion;
   
    @Column(name = "idEmpresa")
    private Integer idEmpresa;

    public Evento() {
    }

    

    public Integer getIdEvento() {
		return idEvento;
	}



	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Date getFechahora() {
		return fechahora;
	}



	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Evento[ idEvento=" + idEvento + " ]";
    }
    
}
