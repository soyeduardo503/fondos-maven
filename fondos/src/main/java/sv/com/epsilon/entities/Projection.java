/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.util.Mes;

/**
 *
 * @author Zeta
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projection implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private Integer idProjection;

    
   
    private Integer month;
    
    
    private Double amount;
    
    
    private String act;
    
    
    
    private Integer idPresupuesto;
    
   
    private Integer idEmpresa;

    

    

    private Integer percent;
    


   @JsonIgnore
    public Mes getMesLetter() {
    	return new Mes(month);

    }
   
	

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idProjection != null ? idProjection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projection)) {
            return false;
        }
        Projection other = (Projection) object;
        if ((this.idProjection == null && other.idProjection != null) || (this.idProjection != null && !this.idProjection.equals(other.idProjection))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.IdProjection[ idProjection=" + idProjection + " ]";
    }
    
}
