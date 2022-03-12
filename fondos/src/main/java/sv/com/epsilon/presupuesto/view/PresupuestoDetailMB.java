/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class PresupuestoDetailMB implements Serializable{

	/**
	 * 
	 */
	@ManagedProperty(value="#{sesionMB}")
	private UsuarioSessionMB session;
	private Presupuesto presupuesto;
	
	private String filtro="";
	private CategoriaFacade facade= new CategoriaFacade();
	private GastoFacade facadeG= new GastoFacade();
	private List<Categoria> top5Gastos=new ArrayList<Categoria>(); 
	private List<String> last5Gastos=new ArrayList<String>();
	
	
	public PresupuestoDetailMB() {

	}
	
	public void preRenderView(){
		
	}
	
	public void cargar(Presupuesto p) {
		 top5Gastos=facade.findBiggest5Gastos(p); 
		 last5Gastos=facadeG.findLastByPresupuesto(p);
		 
	}

	public List<Categoria> getTop5Gastos() {
		return top5Gastos;
	}

	public void setTop5Gastos(List<Categoria> top5Gastos) {
		this.top5Gastos = top5Gastos;
	}

	public List<String> getLast5Gastos() {
		return last5Gastos;
	}

	public void setLast5Gastos(List<String> last5Gastos) {
		this.last5Gastos = last5Gastos;
	}
	
	
	
	
}
