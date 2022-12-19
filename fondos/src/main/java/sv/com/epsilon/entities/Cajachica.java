/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Date;

import sv.com.epsilon.ctrlr.annotation.Id;

/**
 *
 * @author Zeta
 */

public class Cajachica implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer idCajachica;
    

    private String act;
    
    private String nombre;
    
    
    private Double montofinal;
    
    private Integer vinicial;
    
    
    private Integer vfinal;
    
    private Date fechaInicio;
    
    private Date fechaFin;

    
    private Integer year;
    
    private Integer idEmpresa;

    public Cajachica() {
    }

    


	public Integer getIdCajachica() {
		return idCajachica;
	}




	public void setIdCajachica(Integer idCajachica) {
		this.idCajachica = idCajachica;
	}




	public String getAct() {
		return act;
	}




	public void setAct(String act) {
		this.act = act;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public Double getMontofinal() {
		return montofinal;
	}




	public void setMontofinal(Double montofinal) {
		this.montofinal = montofinal;
	}




	public Integer getVinicial() {
		return vinicial;
	}




	public void setVinicial(Integer vinicial) {
		this.vinicial = vinicial;
	}




	public Integer getVfinal() {
		return vfinal;
	}




	public void setVfinal(Integer vfinal) {
		this.vfinal = vfinal;
	}




	public Date getFechaInicio() {
		return fechaInicio;
	}




	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}




	public Date getFechaFin() {
		return fechaFin;
	}




	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}




	public Integer getYear() {
		return year;
	}




	public void setYear(Integer year) {
		this.year = year;
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
        hash += (idCajachica != null ? idCajachica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cajachica)) {
            return false;
        }
        Cajachica other = (Cajachica) object;
        if ((this.idCajachica == null && other.idCajachica != null) || (this.idCajachica != null && !this.idCajachica.equals(other.idCajachica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Cajachica[ idCajachica=" + idCajachica + " ]";
    }
    
}
