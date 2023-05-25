/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.xml.bind.annotation.XmlTransient;

import sv.com.epsilon.ctrlr.annotation.Id;

/**
 *
 * @author Zeta
 */

public class Presupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idPresupuesto;
    
    private String nombrePresupuesto;
    
    private String year;
    
    private double total;
    
    private Double actual;
    
    private Date fechaInicio;
    
    private Date fechaFin;
    
    private Date fechaElaboracion;
    
    private int idUSuario;
    
    private String validasub;
    
    private List<Categoria> categoriaList;
        
    private List<Financiamiento> financiamientoList;

   
    private String act;
    

    
    private String codigo;
    
    
    private Integer idCuenta;
    
    
    private Integer idEmpresa;
    
    private Integer isPrimary;
    
        
    private Integer sizesubs;
    
    private Double abonado;
    
    public Presupuesto() {
    }

    public Presupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Presupuesto(Integer idPresupuesto, String nombrePresupuesto, String year, double total, Date fechaInicio, Date fechaFin, Date fechaElaboracion, int idUSuario) {
        this.idPresupuesto = idPresupuesto;
        this.nombrePresupuesto = nombrePresupuesto;
        this.year = year;
        this.total = total;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaElaboracion = fechaElaboracion;
        this.idUSuario = idUSuario;
    }

    public Presupuesto(int i, Integer idEmpresa) {
		this.idPresupuesto=i;
		this.idEmpresa=idEmpresa;
	}

	public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    
    public String getNombrePresupuesto() {
        return nombrePresupuesto;
    }

    public void setNombrePresupuesto(String nombrePresupuesto) {
        this.nombrePresupuesto = nombrePresupuesto;
    }
    
    @XmlTransient
    public List<Financiamiento> getFinanciamientoList() {
		return financiamientoList;
	}

	public void setFinanciamientoList(List<Financiamiento> financiamientoList) {
		this.financiamientoList = financiamientoList;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getYear() {
    	
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public int getIdUSuario() {
        return idUSuario;
    }

    public void setIdUSuario(int idUSuario) {
        this.idUSuario = idUSuario;
    }

    @XmlTransient
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    

    
    

    public Integer getSizesubs() {
		return sizesubs;
	}

	public void setSizesubs(Integer sizesubs) {
		this.sizesubs = sizesubs;
	}

	public Integer getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	public boolean evalPrincipal() {
		return isPrimary!=null&&isPrimary==1;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	public Double getAbonado() {
		return abonado;
	}

	public void setAbonado(Double abonado) {
		this.abonado = abonado;
	}

	public void defaultValuePrimary(Integer id) {
		if(id==this.getIdPresupuesto())
			this.setIsPrimary(1);
		else
			setIsPrimary(0);
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idPresupuesto != null ? idPresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.idPresupuesto == null && other.idPresupuesto != null) || (this.idPresupuesto != null && !this.idPresupuesto.equals(other.idPresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombrePresupuesto();
    }

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getValidasub() {
		return validasub;
	}

	public void setValidasub(String validasub) {
		this.validasub = validasub;
	}
	
    
	
    
    
}
