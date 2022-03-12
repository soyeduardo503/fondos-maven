package sv.com.epsilon.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

@ManagedBean
@ViewScoped
public class CuentasMB {

	@ManagedProperty(value="#{sesionMB}")
	private UsuarioSessionMB sesionMB;
	 List<String> list=new ArrayList<String>();
	
	public CuentasMB() {
		
	}
	
	public List<String> obtenerListaCuentas(String txt){
		
			list=new ArrayList<String>();
			
//			for(Cuenta c:sesionMB.getPresupuestoSelected().getCuentaList()){
//				if(c.getNumero().startsWith(txt)||c.getBanco().toUpperCase().contains(txt.toUpperCase()))
//					list.add(c.getNumero()+" - "+c.getBanco());
//			}
//		
		return list;
	}
	
	public UsuarioSessionMB getSesionMB() {
		return sesionMB;
	}
	public void setSesionMB(UsuarioSessionMB sesionMB) {
		this.sesionMB = sesionMB;
	}
	
	

}
