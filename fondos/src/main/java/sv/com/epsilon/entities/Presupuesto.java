/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "presupuesto")
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p Where p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByIdPresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.idPresupuesto = :idPresupuesto "),
    @NamedQuery(name = "Presupuesto.findByName", query = "SELECT p FROM Presupuesto p WHERE p.nombrePresupuesto = :nombrePresupuesto and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByNombrePresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.nombrePresupuesto = :nombrePresupuesto and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByYear", query = "SELECT p FROM Presupuesto p WHERE p.year = :year and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByTotal", query = "SELECT p FROM Presupuesto p WHERE p.total = :total and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByAct", query = "SELECT p FROM Presupuesto p WHERE p.act = :act and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByActual", query = "SELECT p FROM Presupuesto p WHERE p.actual = :actual"),
    @NamedQuery(name = "Presupuesto.findByFechaInicio", query = "SELECT p FROM Presupuesto p WHERE p.fechaInicio = :fechaInicio and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByFechaFin", query = "SELECT p FROM Presupuesto p WHERE p.fechaFin = :fechaFin and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByFechas", query = "SELECT p FROM Presupuesto p WHERE p.fechaFin = :fechaFin and p.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Presupuesto.findByFechaElaboracion", query = "SELECT p FROM Presupuesto p WHERE p.fechaElaboracion = :fechaElaboracion"),
    @NamedQuery(name = "Presupuesto.findByIdUSuario", query = "SELECT p FROM Presupuesto p WHERE p.idUSuario = :idUSuario")})
public class Presupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPresupuesto")
    private Integer idPresupuesto;
    @Basic(optional = false)
    @Column(name = "NombrePresupuesto")
    private String nombrePresupuesto;
    @Basic(optional = false)
    @Column(name = "Year")
    private String year;
    @Basic(optional = false)
    @Column(name = "Total")
    private double total;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Actual")
    private Double actual;
    @Basic(optional = false)
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "FechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "FechaElaboracion")
    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;
    @Basic(optional = false)
    @Column(name = "IdUSuario")
    private int idUSuario;
    @Basic(optional = false)
    @Column(name = "validasub")
    private String validasub;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPresupuesto")
    private List<Categoria> categoriaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPresupuesto")
    private List<Financiamiento> financiamientoList;


    @Basic(optional = false)
    @Column(name = "Act")
    private String act;
    

    @Basic(optional = false)
    @Column(name = "codigo")    
    private String codigo;
    
    @Basic(optional = false)
    @Column(name = "idCuenta")    
    private Integer idCuenta;
    
    @Basic(optional = false)
    @Column(name = "idEmpresa")    
    private Integer idEmpresa;
    
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

    

    

    public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
