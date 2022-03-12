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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "tablagasto")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Tablagasto.findByAct", query = "SELECT t FROM Tablagasto t WHERE t.act = :act and t.idEmpresa=:idEmpresa"),
@NamedQuery(name = "Tablagasto.findAll", query = "SELECT r FROM Tablagasto r where r.idEmpresa=:idEmpresa"),
@NamedQuery(name = "Tablagasto.findByIdTablagasto", query = "SELECT r FROM Tablagasto r WHERE r.idtablagasto = :idtablagasto and r.idEmpresa=:idEmpresa"),
@NamedQuery(name = "Tablagasto.findByName", query = "SELECT r FROM Tablagasto r WHERE r.nombre = :nombre and r.idEmpresa=:idEmpresa"),
   })
public class Tablagasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtablagasto")
    private Integer idtablagasto;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Act")
    private String act;
    
    @Column(name = "idEmpresa")
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
