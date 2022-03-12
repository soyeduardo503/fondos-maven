/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.ArrayList;
import java.util.List;

import sv.com.epsilon.entities.Categoria;

/**
 * @author usuario07
 *
 */
public class CategoriasBasicas {

	/**
	 * 
	 */
	public   List<Categoria> categorias=crearLista();
	public static List<Categoria> crearLista(){
		List<Categoria> categorias=new ArrayList<Categoria>();
		Categoria admin = new Categoria("Administracion");
		Categoria diarios = new Categoria("Gastos Diarios");
		Categoria manto = new Categoria("Mantenimiento");
		
		admin.setCategoriaList(new ArrayList<Categoria>());
		diarios.setCategoriaList(new ArrayList<Categoria>());
		manto.setCategoriaList(new ArrayList<Categoria>());
		
		admin.getCategoriaList().add(new Categoria("Salario"));
		admin.getCategoriaList().add(new Categoria("Papeleria"));
		admin.getCategoriaList().add(new Categoria("Combustible"));
		admin.getCategoriaList().add(new Categoria("informatica"));
		admin.getCategoriaList().add(new Categoria("Categoria 6"));
		
		diarios.getCategoriaList().add(new Categoria("Comida empleados"));
		diarios.getCategoriaList().add(new Categoria("Gasolina"));
		diarios.getCategoriaList().add(new Categoria("Comida niños"));
		
		
		manto.getCategoriaList().add(new Categoria("Repuestos"));
		manto.getCategoriaList().add(new Categoria("Mano de obra"));
		manto.getCategoriaList().add(new Categoria("Aceite"));
		manto.getCategoriaList().add(new Categoria("Llantas"));
		
		
		categorias.add(admin);
		categorias.add(diarios);
		categorias.add(manto);
		return categorias;
	}
	public CategoriasBasicas() {
		
	}
	
	
	
	
	
	
	

}
