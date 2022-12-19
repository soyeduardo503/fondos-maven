/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.xml.bind.annotation.XmlTransient;

import sv.com.epsilon.ctrlr.annotation.Id;

/**
 *
 * @author Zeta
 */
public class Financiamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idFinanciamiento;

    private String nombre;

    private String cuenta;

    private String donador;

    private Double Monto;
    
    private Date fecha;
    

    private Date fechaRegistro;
    

    private List<Movimiento> movimientoList;
    
    
    private String act;
    
    private Presupuesto idPresupuesto;
    
    
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
