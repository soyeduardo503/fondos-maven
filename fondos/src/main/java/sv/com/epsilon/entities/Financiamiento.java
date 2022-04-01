/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "financiamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Financiamiento.findAll", query = "SELECT f FROM Financiamiento f Where  f.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Financiamiento.findByAct", query = "SELECT c FROM Financiamiento c where c.act=:act and c.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Financiamiento.findByIdFinanciamiento", query = "SELECT f FROM Financiamiento f WHERE f.idFinanciamiento = :idFinanciamiento"),
    @NamedQuery(name = "Financiamiento.findByName", query = "SELECT f FROM Financiamiento f WHERE f.nombre = :nombre and  f.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Financiamiento.findByCuenta", query = "SELECT f FROM Financiamiento f WHERE f.cuenta = :cuenta and  f.idEmpresa=:idEmpresa")})
public class Financiamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFinanciamiento")
    private Integer idFinanciamiento;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Cuenta")
    private String cuenta;
    @Column(name = "donador")
    private String donador;
    @Column(name = "monto")
    private Double Monto;
    
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    
    @OneToMany(mappedBy = "idFinanciamiento")
    private List<Movimiento> movimientoList;
    
    @Basic(optional = false)
    @Column(name = "act")
    private String act;
    
    @JoinColumn(name = "idPresupuesto", referencedColumnName = "idPresupuesto")
    @ManyToOne(optional = true)
    private Presupuesto idPresupuesto;
    
    @Column(name = "idEmpresa")
    private Integer idEmpresa;

    public Financiamiento() {
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


	public Presupuesto getIdPresupuesto() {
		return idPresupuesto;
	}


	public void setIdPresupuesto(Presupuesto idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}


	public Financiamiento(Integer idFinanciamiento) {
        this.idFinanciamiento = idFinanciamiento;
    }

    public Integer getIdFinanciamiento() {
        return idFinanciamiento;
    }

    public void setIdFinanciamiento(Integer idFinanciamiento) {
        this.idFinanciamiento = idFinanciamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }
    
    

    public String getDonador() {
		return donador;
	}



	public void setDonador(String donador) {
		this.donador = donador;
	}



	public Double getMonto() {
		return Monto;
	}



	public void setMonto(Double monto) {
		Monto = monto;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Date getFechaRegistro() {
		return fechaRegistro;
	}



	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}



	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idFinanciamiento != null ? idFinanciamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Financiamiento)) {
            return false;
        }
        Financiamiento other = (Financiamiento) object;
        if ((this.idFinanciamiento == null && other.idFinanciamiento != null) || (this.idFinanciamiento != null && !this.idFinanciamiento.equals(other.idFinanciamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Financiamiento[ idFinanciamiento=" + idFinanciamiento + " ]";
    }
    
}
