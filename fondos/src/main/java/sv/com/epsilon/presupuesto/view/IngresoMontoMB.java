/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.presupuesto.ctrlr.AsignacionCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.DisponibleCtrlr;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class IngresoMontoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4005321057957924342L;

	@ManagedProperty(value="#{sesionMB}")
	private UsuarioSessionMB sesionMB;
	
	
	private Categoria categoria=new Categoria();
	private Double monto=0.0;
	private String mensaje;
	
	
	
	
	/**
	 * 
	 */
	public IngresoMontoMB() {
		
	}
	public UsuarioSessionMB getSesionMB() {
		return sesionMB;
	}
	public void setSesionMB(UsuarioSessionMB sesionMB) {
		this.sesionMB = sesionMB;
	}
	public String  nombreCategoria() {
		if(categoria!=null&&categoria.getIdCategoria()!=null)
			return categoria.obtenerNombreCompleto();
		else
			return "";
	}
	
	public void abrirDialogo(Categoria c){
		this.categoria=c;
		new ExecuteForm().ExecuteUpdate( "IDDlgIngreso","PF(DlgIngresoMonto).show()");
		 //oncomplete="PF('DlgIngresoMonto').show();" update="IDFrmTableDetail:IDDlgIngreso"
	}
	
	public Double obtenerDisponibleAsignado(Categoria c){
//		if(!c.getCategoriaList().isEmpty()){
//			return DisponibleCtrlr.disponible(c);
//		}
		return 0.0;
	}
	public Double obtenerDisponibleAsignado(Presupuesto p){
//		if(!p.getCategoriaList().isEmpty()){
//			return DisponibleCtrlr.disponible(p);
//		}
		return 0.0;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void asignarMonto(){
		mensaje="";
		if(monto>0&&categoria!=null){
			if(!new AsignacionCtrlr().validarAsignacion(categoria, monto)){
				mensaje="Error monto mayor al disponible";
			}
		}
		if(monto<=0){
			mensaje="Monto menor a cero!!!";
			
		}
		if(!"".equals(mensaje)){
			new ExecuteForm().ExecuteUpdate( "IDMessageIngreso","PF(WgtMessageIngreso).show()");
		}
	}
	
		
	
	
}
