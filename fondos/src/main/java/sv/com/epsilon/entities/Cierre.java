/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Zeta
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cierre implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCierre;
    


    private Calendar fecha;
   
    

    private Integer mes;

    private Double montoInicial;
    
    private Double montoFinal;

    private String act;
    

    private Integer idUser;
    
    private Integer idPresupuesto;

    private Integer year;

    
    
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idCierre != null ? idCierre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cierre)) {
            return false;
        }
        Cierre other = (Cierre) object;
        if ((this.idCierre == null && other.idCierre != null) || (this.idCierre != null && !this.idCierre.equals(other.idCierre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Cierre[ idCierre=" + idCierre + " ]";
    }
    
}
