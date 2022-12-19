/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;

import sv.com.epsilon.ctrlr.annotation.Id;

/**
 *
 * @author Zeta
 */

public class Correlativorecibo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idCorrelativorecibo;

    private Integer correlativo;

    private String act;

    private String nombre;

    private Integer idCuenta;
    

    private Integer desde;

    private Integer hasta;

    private Integer idEmpresa;

    public Correlativorecibo() {
    }

    
    public Integer getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public Correlativorecibo(Integer idCuenta) {
        this.idCuenta = idCuenta;
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
	

    public Integer getIdCorrelativorecibo() {
		return idCorrelativorecibo;
	}


	public void setIdCorrelativorecibo(Integer idCorrelativorecibo) {
		this.idCorrelativorecibo = idCorrelativorecibo;
	}


	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correlativorecibo)) {
            return false;
        }
        Correlativorecibo other = (Correlativorecibo) object;
        if ((this.idCorrelativorecibo == null && other.idCuenta != null) || (this.idCorrelativorecibo != null && !this.idCorrelativorecibo.equals(other.idCorrelativorecibo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.idCorrelativorecibo[ idCorrelativorecibo=" + idCorrelativorecibo + " ]";
    }
    
}
