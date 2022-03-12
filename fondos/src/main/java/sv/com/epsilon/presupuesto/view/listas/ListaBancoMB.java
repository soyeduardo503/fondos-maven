/**
 * 
 */
package sv.com.epsilon.presupuesto.view.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Banco;
import sv.com.epsilon.facade.BancoFacade;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class ListaBancoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	
	private List<Banco> list=new ArrayList<Banco>();
	
	private BancoFacade facade=new BancoFacade();



	
	public ListaBancoMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void load() {
		list=facade.findAllActive();
	}

	public List<Banco> getList() {
		return list;
	}

	public void setList(List<Banco> list) {
		this.list = list;
	}

	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}

	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}

	public Banco find(Integer idBanco) {
		return facade.findById(idBanco);
	}
	
}
