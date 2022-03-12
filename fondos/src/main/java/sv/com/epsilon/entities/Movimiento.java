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
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m"),
    @NamedQuery(name = "Movimiento.findByIdMovimiento", query = "SELECT m FROM Movimiento m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "Movimiento.findByTipo", query = "SELECT m FROM Movimiento m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Movimiento.findByFecha", query = "SELECT m FROM Movimiento m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Movimiento.findByFechas", query = "SELECT m FROM Movimiento m WHERE m.fecha between :fecha1 and :fecha2 "),
    @NamedQuery(name = "Movimiento.findByMonto", query = "SELECT m FROM Movimiento m WHERE m.monto = :monto"),
    @NamedQuery(name = "Movimiento.findByFechaRegistro", query = "SELECT m FROM Movimiento m WHERE m.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Movimiento.findByIdGasto", query = "SELECT m FROM Movimiento m WHERE m.idGasto = :idGasto"),
    @NamedQuery(name = "Movimiento.findByIdUsuario", query = "SELECT m FROM Movimiento m WHERE m.idUsuario = :idUsuario"),
    @NamedQuery(name = "Movimiento.findByCuenta", query = "SELECT m FROM Movimiento m WHERE m.cuenta = :cuenta")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idMovimiento")
    private Integer idMovimiento;
    @Basic(optional = false)
    @Column(name = "Tipo")
    private String tipo;
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Monto")
    private Double monto;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "IdUsuario")
    private Integer idUsuario;
    @Column(name = "Cuenta")
    private String cuenta;
    
    @JoinColumn(name = "idGasto", referencedColumnName = "idGasto")
    @ManyToOne
    private Gasto idGasto;
    
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    @ManyToOne(optional = true)
    private Categoria idCategoria;
    
    @JoinColumn(name = "idFinanciamiento", referencedColumnName = "idFinanciamiento")
    @ManyToOne
    private Financiamiento idFinanciamiento;

    public Movimiento() {
    }

    public Movimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Movimiento(Integer idMovimiento, String tipo) {
        this.idMovimiento = idMovimiento;
        this.tipo = tipo;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaRegistro() {
    	if(fechaRegistro==null)
    		fechaRegistro=new Date();
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    


    public Gasto getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(Gasto idGasto) {
		this.idGasto = idGasto;
	}

	public Financiamiento getIdFinanciamiento() {
        return idFinanciamiento;
    }

    public void setIdFinanciamiento(Financiamiento idFinanciamiento) {
        this.idFinanciamiento = idFinanciamiento;
    }
    
    

    public Categoria getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Movimiento[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
