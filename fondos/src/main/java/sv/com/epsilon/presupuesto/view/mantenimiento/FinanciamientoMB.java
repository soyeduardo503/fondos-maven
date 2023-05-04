/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.GeneradorCodigo;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class FinanciamientoMB extends AbstractMantto<Categoria, CategoriaFacade> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	
	private GeneradorCodigo generardorCodigo=new GeneradorCodigo();
	
	public FinanciamientoMB() {
		super(Categoria.class,CategoriaFacade.class);
	}
	
	public void asignarCodigo(){
		generardorCodigo.code(this.getItemSelected());
		new ExecuteForm().Update(this.getIdFormNew()+":codigo");
	}

	@Override
	public void reset() {
		
		this.setItemSelected(new Categoria());
		
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nueva Categoria");
		this.setHeaderPage("Mantenimiento Categorias (Articulos,Tipo de objeto, Clase )");
		this.setHeaderTable("Tipo de Categoria productos");
		
	}

	@Override
	public void preRender() {
		try {
			
			this.callLoad();
			defineHeaders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	@PreDestroy
	public void preDestroy() {
		super.destroy();
		
	}

	@Override
	protected void setValueMod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDefaultValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateDataValue() throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
