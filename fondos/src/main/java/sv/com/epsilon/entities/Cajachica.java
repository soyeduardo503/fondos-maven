/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import lombok.Data;

/**
 *
 * @author Zeta
 */
@Data
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
