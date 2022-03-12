/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Karina
 */
@Entity
@Table(name = "parametro")
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "FROM Parametro p where p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Parametro.findByIdParametro", query = "FROM Parametro p WHERE p.idParametro = :idParametro and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Parametro.findByName", query = "FROM Parametro p WHERE p.nombre = :nombre and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Parametro.findByValor", query = "FROM Parametro p WHERE p.valor = :valor and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Parametro.findByAct", query = "FROM Parametro p WHERE p.act = :act and p.idEmpresa=:idEmpresa" )})
public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdParametro")
    private Integer idParametro;
    @Size(max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "Valor")
    private String valor;
    @Size(max = 1)
    @Column(name = "Act")
    private String act;
    
    @Column(name = "idEmpresa")
    private Integer idEmpresa;

    public Parametro() {
    }

    public Parametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }
    
    

    public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return valor;
    }
    
}
