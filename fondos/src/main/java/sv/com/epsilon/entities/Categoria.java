/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Zeta
 */
@Entity
@Table(name = "categoria")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Categoria.findByName", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre "),
    @NamedQuery(name = "Categoria.findByDescripcion", query = "SELECT c FROM Categoria c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Categoria.findByCodigo", query = "SELECT c FROM Categoria c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Categoria.findByMonto", query = "SELECT c FROM Categoria c WHERE c.monto = :monto"),
    @NamedQuery(name = "Categoria.findByAct", query = "SELECT c FROM Categoria c WHERE c.act = :act"),
    @NamedQuery(name = "Categoria.findByActual", query = "SELECT c FROM Categoria c WHERE c.actual = :actual")})
public class Categoria  implements Serializable, Comparable<Categoria> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategoria")
    private Integer idCategoria;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "Codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "Monto")
    private double monto;
    @Basic(optional = false)
    @Column(name = "Act")
    private String act;
    @Basic(optional = false)
    @Column(name = "idEmpresa")
    private Integer idEmpresa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Actual")
    private Double actual;
    @Column(name = "nombrePantalla")
    private String nombrePantalla;
    @JoinColumn(name = "idPresupuesto", referencedColumnName = "idPresupuesto")
    @ManyToOne(optional = true)
    private Presupuesto idPresupuesto;
    @OneToMany(mappedBy = "idCategoriaPadre", cascade=CascadeType.PERSIST)
    private List<Categoria> categoriaList;
    @JoinColumn(name = "idCategoriaPadre", referencedColumnName = "idCategoria" , nullable=true)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Categoria idCategoriaPadre;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
//    private List<Gasto> gastoList;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGasto")
//    private List<Imagen> imagenList;
    
    @OneToMany(mappedBy = "idCategoria")
    private List<Movimiento> movimientoList;
    
    
    @Basic(optional = false)
    @Column(name = "idRubro")
    private Integer idRubro ;
    
    public Categoria() {
    }

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    public Categoria(String categoria) {
        this.nombre = categoria;
    }
    
    public Categoria( String codigo, String nombre, String descripcion, double monto,Integer idRubro) {
        
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        
        this.monto = monto;
        this.idRubro=idRubro;
        this.act = "A";
        
    }
    public Categoria(Integer idCategoria, String nombre, String descripcion, String codigo, double monto) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.monto = monto;
        this.act = "A";
    }

    


	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
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
    public String obtenerNombreCompleto(){
    	return nombrePadre(this);
    	
    }
    private String nombrePadre(Categoria cpadre){
    	if(cpadre.getIdCategoriaPadre()==null)
    		return cpadre.getIdPresupuesto().getNombrePresupuesto()+"-"+cpadre.getIdPresupuesto().getYear()+"-"+cpadre.getNombre();
    	else
    		return nombrePadre(cpadre.getIdCategoriaPadre())+"-"+cpadre.getNombre();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public Presupuesto getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Presupuesto idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    @XmlTransient
    public List<Categoria> getCategoriaList() {
    	if(categoriaList==null)
    		categoriaList=new ArrayList<Categoria>();
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public Categoria getIdCategoriaPadre() {
        return idCategoriaPadre;
    }

    public void setIdCategoriaPadre(Categoria idCategoriaPadre) {
        this.idCategoriaPadre = idCategoriaPadre;
    }

   
    
    
    @XmlTransient
    public List<Movimiento> getMovimientoList() {
		return movimientoList;
	}

	public void setMovimientoList(List<Movimiento> movimientoList) {
		this.movimientoList = movimientoList;
	}

	public String getNombrePantalla() {
		return nombrePantalla;
	}

	public void setNombrePantalla(String nombrePantalla) {
		this.nombrePantalla = nombrePantalla;
	}
	
	

	public Integer getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(Integer idRubro) {
		this.idRubro = idRubro;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }

	@Override
	public int compareTo(Categoria o) {
		
		return this.codigo.compareTo(o.getCodigo());
	}
    
}
