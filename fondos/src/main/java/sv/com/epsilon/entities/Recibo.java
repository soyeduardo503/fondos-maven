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

public class Recibo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer idRecibo;
    
    private String nombre;
    
    private Date fechaPantalla;
    
    private String tipoPersona;
    
    private String dui;
    
    private String nit;
    
    private String iva;
    
    private String act;
    private Integer idEmpresa;
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
