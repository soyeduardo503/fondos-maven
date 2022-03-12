/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "gasto")
@XmlRootElement 
@NamedQueries({
    @NamedQuery(name = "Gasto.findAll", query = "SELECT g FROM Gasto g Where  g.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Gasto.findByName", query = "SELECT g FROM Gasto g WHERE g.nombre = :nombre and g.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Gasto.findByNombre", query = "SELECT g FROM Gasto g WHERE g.nombre = :nombre and g.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Gasto.findByDescripcion", query = "SELECT g FROM Gasto g WHERE g.descripcion = :descripcion and g.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Gasto.findByNombrePantalla", query = "SELECT g FROM Gasto g WHERE g.nombrePantalla = :nombrePantalla and g.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Gasto.findByAct", query = "SELECT g FROM Gasto g WHERE g.act = :act and g.idEmpresa=:idEmpresa"),
    @NamedQuery(name = "Gasto.findByCodigo", query = "SELECT g FROM Gasto g WHERE g.codigo = :codigo and g.idEmpresa=:idEmpresa"),    
    @NamedQuery(name = "Gasto.findByFecha", query = "SELECT g FROM Gasto g WHERE g.fecha = :fecha and g.idEmpresa=:idEmpresa")
    })
public class Gasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGasto")
    private Integer idGasto;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "NombrePantalla")
    private String nombrePantalla;
    @Column(name = "Act")
    private String act;
    @Column(name = "Codigo")
    private String codigo;
    @Column(name = "cheque")
    private String cheque;
    @Column(name = "total")
    private Double total;
    
    @Column(name = "idEmpresa")
    private String idEmpresa;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGasto")
    private List<Recibo> reciboList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGasto")
    private List<Imagen> imagenList;
    
    @OneToMany(mappedBy = "idGasto",cascade = CascadeType.ALL)
    private List<Movimiento> movimientoList;
    
    @JoinColumn(name = "idTipoDesembolso", referencedColumnName = "idTipoDesembolso")
    @ManyToOne(optional = false)
    private Tipodesembolso idTipoDesembolso;
    
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne(optional = true)
    private Proveedor idProveedor;

    public Gasto() {
    }

    

   

    public Double getTotal() {
		return total;
	}





	public void setTotal(Double total) {
		this.total = total;
	}





	public String getIdEmpresa() {
		return idEmpresa;
	}





	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}





	public Integer getIdGasto() {
		return idGasto;
	}





	public void setIdGasto(Integer idGasto) {
		this.idGasto = idGasto;
	}





	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombrePantalla() {
        return nombrePantalla;
    }

    public void setNombrePantalla(String nombrePantalla) {
        this.nombrePantalla = nombrePantalla;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

  

    public String getCheque() {
		return cheque;
	}





	public void setCheque(String cheque) {
		this.cheque = cheque;
	}





	@XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }

    @XmlTransient
    public List<Imagen> getImagenList() {
        return imagenList;
    }

    public void setImagenList(List<Imagen> imagenList) {
        this.imagenList = imagenList;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }


    
   
    public Proveedor getIdProveedor() {
		return idProveedor;
	}





	public void setIdProveedor(Proveedor idProveedor) {
		this.idProveedor = idProveedor;
	}





	public Tipodesembolso getIdTipoDesembolso() {
		return idTipoDesembolso;
	}

	public void setIdTipoDesembolso(Tipodesembolso idTipoDesembolso) {
		this.idTipoDesembolso = idTipoDesembolso;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idGasto != null ? idGasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gasto)) {
            return false;
        }
        Gasto other = (Gasto) object;
        if ((this.idGasto == null && other.idGasto != null) || (this.idGasto != null && !this.idGasto.equals(other.idGasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.epsilon.Gasto[ idItem=" + idGasto + " ]";
    }
    
}
