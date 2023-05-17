/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import sv.com.epsilon.ctrlr.annotation.Id;

/**
 *
 * @author Zeta
 */

public class Categoria  implements Serializable, Comparable<Categoria> {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idCategoria;

    private String nombre;

    private String descripcion;
    

    private String codigo;

    private double monto;

    private String act;

    private Integer idEmpresa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private Double actual;

    private String nombrePantalla;

    private Presupuesto idPresupuesto;

    private List<Categoria> categoriaList;

    private Categoria idCategoriaPadre;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
//    private List<Gasto> gastoList;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGasto")
//    private List<Imagen> imagenList;
    

    private List<Movimiento> movimientoList;
    
    

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
        return actual!=null?actual:0;
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
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
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
