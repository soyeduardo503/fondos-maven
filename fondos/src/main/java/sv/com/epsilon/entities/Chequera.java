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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "chequera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chequera.findAll", query = "SELECT c FROM Chequera c Where c.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Chequera.findByAct", query = "SELECT c FROM Cuenta c where c.act=:act and c.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Chequera.findByName", query = "SELECT c FROM Chequera c WHERE c.nombre like :nombre and c.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Chequera.findByIdCuenta", query = "SELECT c FROM Chequera c WHERE c.idCuenta = :idCuenta"),
   })
public class Chequera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idChequera")
    private Integer idChequera;
    @Basic(optional = false)
    @Column(name = "correlativo")
    private Integer correlativo;
    @Basic(optional = false)
    @Column(name = "act")
    private String act;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "idCuenta")
    private Integer idCuenta;
    
    @Basic(optional = false)
    @Column(name = "desde")
    private Integer desde;
    @Basic(optional = false)
    @Column(name = "hasta")
    private Integer hasta;
    @Column(name = "idEmpresa")
    private Integer idEmpresa;

    public Chequera() {
    }

    
    public Integer getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public Chequera(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

   
    public Integer getIdChequera() {
		return idChequera;
	}

	public void setIdChequera(Integer idChequera) {
		this.idChequera = idChequera;
	}

	public Integer getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Integer getDesde() {
		return desde;
	}

	public void setDesde(Integer desde) {
		this.desde = desde;
	}

	public Integer getHasta() {
		return hasta;
	}

	public void setHasta(Integer hasta) {
		this.hasta = hasta;
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chequera)) {
            return false;
        }
        Chequera other = (Chequera) object;
        if ((this.idChequera == null && other.idCuenta != null) || (this.idChequera != null && !this.idChequera.equals(other.idChequera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Chequera[ idChequera=" + idChequera + " ]";
    }
    
}
