/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import sv.com.epsilon.ctrlr.annotation.Id;

/**
 *
 * @author Zeta
 */

public class Gasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idGasto;
    
    private String nombre;
    
    private String descripcion;
    
    private String nombrePantalla;
    
    private String act;
    
    private String codigo;
    
    private Integer cheque;
    
    private Double total;
        
    private Integer idEmpresa;
    
    private Calendar fecha;
    private Date fechaRegistro;
    
    
    private List<Recibo> reciboList;
    
    private List<Imagen> imagenList;
    
    private List<Movimiento> movimientoList;
        
    private Tipodesembolso idTipoDesembolso;
    
    private Proveedor idProveedor;
    
    private Integer kpresupuesto;
    private String status;
    private String numeroComprobante;

    public Gasto() {
    }



    public Gasto(Integer idGasto, String nombre, String descripcion, String nombrePantalla, String act, String codigo,
			Integer cheque, Double total, String idEmpresa, Calendar fecha, Date fechaRegistro, List<Recibo> reciboList,
			List<Imagen> imagenList, List<Movimiento> movimientoList, Tipodesembolso idTipoDesembolso,
			Proveedor idProveedor, Integer kpresupuesto, String status, String numeroComprobante) {
		super();
		this.idGasto = idGasto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nombrePantalla = nombrePantalla;
		this.act = act;
		this.codigo = codigo;
		this.cheque = cheque;
		this.total = total;
		this.idEmpresa = Integer.parseInt( idEmpresa);
//		Calendar c=Calendar.getInstance();
//		c.setTime(fecha);
		Calendar cr=Calendar.getInstance();
		cr.setTime(fechaRegistro);
		this.fecha = fecha;
		this.fechaRegistro =fechaRegistro;
		this.reciboList = reciboList;
		this.imagenList = imagenList;
		this.movimientoList = movimientoList;
		this.idTipoDesembolso = idTipoDesembolso;
		this.idProveedor = idProveedor;
		this.kpresupuesto = kpresupuesto;
		this.status = status;
		this.numeroComprobante = numeroComprobante;
	}
    
    public Gasto(Integer idGasto, String nombre, String descripcion, String nombrePantalla, String act, String codigo,
			Integer cheque, Double total, Integer idEmpresa, Calendar fecha, Date fechaRegistro, List<Recibo> reciboList,
			List<Imagen> imagenList, List<Movimiento> movimientoList, Tipodesembolso idTipoDesembolso,
			Proveedor idProveedor, Integer kpresupuesto, String status, String numeroComprobante) {
		super();
		this.idGasto = idGasto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nombrePantalla = nombrePantalla;
		this.act = act;
		this.codigo = codigo;
		this.cheque = cheque;
		this.total = total;
		this.idEmpresa = idEmpresa;
//		Calendar c=Calendar.getInstance();
//		c.setTime(fecha);
		Calendar cr=Calendar.getInstance();
		cr.setTime(fechaRegistro);
		this.fecha = fecha;
		this.fechaRegistro =fechaRegistro;
		this.reciboList = reciboList;
		this.imagenList = imagenList;
		this.movimientoList = movimientoList;
		this.idTipoDesembolso = idTipoDesembolso;
		this.idProveedor = idProveedor;
		this.kpresupuesto = kpresupuesto;
		this.status = status;
		this.numeroComprobante = numeroComprobante;
	}
   

    public Gasto(Integer idGasto) {
		super();
		this.idGasto = idGasto;
	}








	public Date getFechaRegistro() {
		return fechaRegistro;
	}





	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}





	public Double getTotal() {
		return total;
	}





	public void setTotal(Double total) {
		this.total = total;
	}





	public Integer getIdEmpresa() {
		return idEmpresa;
	}





	public void setIdEmpresa(Integer idEmpresa) {
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

    
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

  

    public Integer getCheque() {
		return cheque;
	}





	public void setCheque(Integer cheque) {
		this.cheque = cheque;
	}





	public String getStatus() {
		return status;
	}






	public void setStatus(String status) {
		this.status = status;
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
    	if(idProveedor==null)
    		idProveedor=new Proveedor();
		return idProveedor;
	}





	public void setIdProveedor(Proveedor idProveedor) {
		this.idProveedor = idProveedor;
	}



	


	public Integer getKpresupuesto() {
		return kpresupuesto;
	}






	public void setKpresupuesto(Integer kpresupuesto) {
		this.kpresupuesto = kpresupuesto;
	}






	public Tipodesembolso getIdTipoDesembolso() {
		return idTipoDesembolso;
	}

	public void setIdTipoDesembolso(Tipodesembolso idTipoDesembolso) {
		this.idTipoDesembolso = idTipoDesembolso;
	}
	
	

	public String getNumeroComprobante() {
		return numeroComprobante;
	}






	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
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
