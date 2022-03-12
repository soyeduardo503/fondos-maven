/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "proveedor")
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p Where p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Proveedor.findById", query = "SELECT p FROM Proveedor p Where p.idProveedor=:idProveedor and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Proveedor.findByName", query = "SELECT p FROM Proveedor p WHERE p.nombre like :nombre and p.idEmpresa=:idEmpresa"), 
    @NamedQuery(name = "Proveedor.findDocs", query = "SELECT p FROM Proveedor p WHERE p.dui like :doc or p.nrc=:doc or p.nit=:doc and p.idEmpresa=:idEmpresa"),   
    @NamedQuery(name = "Proveedor.findByAct", query = "SELECT p FROM Proveedor p WHERE p.act = 'A' and p.idEmpresa=:idEmpresa")
    })
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProveedor")
    private Integer idProveedor;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nombreLegal")
    private String nombreLegal;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "dui")
    private String dui;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nrc")
    private String nrc;
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = false)
    @Column(name = "ncuenta")    
    private String ncuenta;
    
    @Basic(optional = false)
    @Column(name = "IdUsuarioCreo")
    private int idUsuarioCreo;
    @Basic(optional = false)
    @Column(name = "IdUSuarioModifico")
    private int idUSuarioModifico;

    @Basic(optional = false)
    @Column(name = "IdEmpresa")
    private int idEmpresa;
    @Basic(optional = false)
    @Column(name = "idBanco")
    private int idBanco;

    @Basic(optional = false)
    @Column(name = "Act")
    private String act;
    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    
    
    
    
    
    public Proveedor(String nombre, String nombreLegal, String tipo, String dui,  String nit,String nrc,Integer idBanco, String ncuenta,
			int idEmpresa) {
		super();
		this.nombre = nombre;
		this.nombreLegal = nombreLegal;
		this.tipo = tipo;
		this.dui = dui;
		this.nrc = nrc;
		this.nit = nit;
		this.idBanco=idBanco;
		this.ncuenta = ncuenta;
		this.idEmpresa = idEmpresa;
		this.fechaIngreso=new Date(); 
		this.act="A";
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreLegal() {
		return nombreLegal;
	}

	public void setNombreLegal(String nombreLegal) {
		this.nombreLegal = nombreLegal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getNcuenta() {
		return ncuenta;
	}

	public void setNcuenta(String ncuenta) {
		this.ncuenta = ncuenta;
	}

	public int getIdUsuarioCreo() {
		return idUsuarioCreo;
	}

	public void setIdUsuarioCreo(int idUsuarioCreo) {
		this.idUsuarioCreo = idUsuarioCreo;
	}

	public int getIdUSuarioModifico() {
		return idUSuarioModifico;
	}

	public void setIdUSuarioModifico(int idUSuarioModifico) {
		this.idUSuarioModifico = idUSuarioModifico;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}
	
	
    
   
    public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Proveedor[ idProveedor=" + idProveedor + " ]";
    }

	
    
    
    
}
