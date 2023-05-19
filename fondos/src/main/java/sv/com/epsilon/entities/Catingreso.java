/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;

/**
 *
 * @author Zeta
 */

public class Catingreso implements Serializable {

    private static final long serialVersionUID = 1L;
  
  
    private Integer idCatingreso;
  
    private String nombre;
    
  
    private String codigo;
    
  
    private String descripcion;
  
    private String act;
    
    
    
    private Integer idPresupuesto;
    
    
    private Integer idEmpresa;

    public Catingreso() {
    }

    public Catingreso(Integer idCatingreso) {
        this.idCatingreso = idCatingreso;
    }

    public Catingreso(Integer idCatingreso, String nombre) {
        this.idCatingreso = idCatingreso;
        this.nombre = nombre;
        
    }

    public Integer getIdCatingreso() {
        return idCatingreso;
    }

    public void setIdCatingreso(Integer idCatingreso) {
        this.idCatingreso = idCatingreso;
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
	

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdPresupuesto() {
		return idPresupuesto;
	}

	public void setIdPresupuesto(Integer idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatingreso != null ? idCatingreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catingreso)) {
            return false;
        }
        Catingreso other = (Catingreso) object;
        if ((this.idCatingreso == null && other.idCatingreso != null) || (this.idCatingreso != null && !this.idCatingreso.equals(other.idCatingreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Catingreso[ idCatingreso=" + idCatingreso + " ]";
    }
    
}
