/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "recibo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibo.findAll", query = "SELECT r FROM Recibo r where r.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Recibo.findByIdRecibo", query = "SELECT r FROM Recibo r WHERE r.idRecibo = :idRecibo and r.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Recibo.findByName", query = "SELECT r FROM Recibo r WHERE r.nombre = :nombre and r.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Recibo.findByNombre", query = "SELECT r FROM Recibo r WHERE r.nombre = :nombre and r.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Recibo.findByFechaPantalla", query = "SELECT r FROM Recibo r WHERE r.fechaPantalla = :fechaPantalla"),
    @NamedQuery(name = "Recibo.findByTipoPersona", query = "SELECT r FROM Recibo r WHERE r.tipoPersona = :tipoPersona"),
    @NamedQuery(name = "Recibo.findByDui", query = "SELECT r FROM Recibo r WHERE r.dui = :dui"),
    @NamedQuery(name = "Recibo.findByNit", query = "SELECT r FROM Recibo r WHERE r.nit = :nit"),
    @NamedQuery(name = "Recibo.findByAct", query = "SELECT r FROM Recibo r WHERE r.act = :act and r.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Recibo.findByIva", query = "SELECT r FROM Recibo r WHERE r.iva = :iva")})
public class Recibo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRecibo")
    private Integer idRecibo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "FechaPantalla")
    @Temporal(TemporalType.DATE)
    private Date fechaPantalla;
    @Column(name = "TipoPersona")
    private String tipoPersona;
    @Column(name = "DUI")
    private String dui;
    @Column(name = "NIT")
    private String nit;
    @Column(name = "IVA")
    private String iva;
    @Column(name = "act")
    private String act;
    @Column(name = "idEmpresa")
    private Integer idEmpresa;
    @JoinColumn(name = "idGasto", referencedColumnName = "idGasto")
    @ManyToOne(optional = false)
    private Gasto idGasto;

    public Recibo() {
    }

    public Recibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }

    public Recibo(Integer idRecibo, String nombre) {
        this.idRecibo = idRecibo;
        this.nombre = nombre;
    }
    

    
    public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public Integer getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaPantalla() {
        return fechaPantalla;
    }

    public void setFechaPantalla(Date fechaPantalla) {
        this.fechaPantalla = fechaPantalla;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
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
        hash += (idRecibo != null ? idRecibo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recibo)) {
            return false;
        }
        Recibo other = (Recibo) object;
        if ((this.idRecibo == null && other.idRecibo != null) || (this.idRecibo != null && !this.idRecibo.equals(other.idRecibo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Recibo[ idRecibo=" + idRecibo + " ]";
    }
    
}
