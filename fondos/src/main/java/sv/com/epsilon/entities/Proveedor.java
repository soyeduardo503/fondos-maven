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

public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idProveedor;

    private String nombre;

    private String nombreLegal;

    private String tipo;

    private String dui;

    private String nrc;
    
    private String nit;
    
    private Date fechaIngreso;
        
    private String ncuenta;
    
    
    private int idUsuarioCreo;
    
    private int idUSuarioModifico;
    
    private int idEmpresa;

    private int idBanco;

    private String act;
    private String codigo;
    private String retencion;
    
    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    
    public Proveedor(String nombre, String nombreLegal) {
  		super();
  		this.nombre = nombre;
  		this.nombreLegal = nombreLegal;
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
	

	public String getRetencion() {
		return retencion;
	}

	public void setRetencion(String retencion) {
		this.retencion = retencion;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
    
    
    
}
