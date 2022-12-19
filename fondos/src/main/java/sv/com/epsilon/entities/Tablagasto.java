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

public class Tablagasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idtablagasto;
    private String nombre;
    private String descripcion;
    private String act;
    
    private Integer idEmpresa;
    public Tablagasto() {
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



	public Integer getIdtablagasto() {
        return idtablagasto;
    }

    public void setIdtablagasto(Integer idtablagasto) {
        this.idtablagasto = idtablagasto;
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

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablagasto != null ? idtablagasto.hashCode() : 0);
        return hash;
    }

   

    @Override
    public String toString() {
        return "sv.com.epsilon.tablagasto[ idtablagasto=" + idtablagasto + " ]";
    }
    
}
