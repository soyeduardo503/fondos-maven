/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.presupuesto.ctrlr.AsignacionCtrlr;

/**
 * @author usuario07
 *
 */
public class NodeModel {

	/**
	 * 
	 */
	public NodeModel() {
		
	}
	
	public TreeNode crearEstructura(Presupuesto presupuesto){
		int hijos=presupuesto.getCategoriaList()!=null?presupuesto.getCategoriaList().size():0;
		double disponible=presupuesto.getTotal()-presupuesto.getActual();
		int asignado= AsignacionCtrlr.percentComplete(presupuesto);
		ViewNode nodoRoot=new ViewNode(presupuesto.getNombrePresupuesto(),hijos,presupuesto.getTotal(),presupuesto.getActual(),disponible,null,presupuesto,asignado,presupuesto.getCodigo(),0.0);
		
		TreeNode root =new DefaultTreeNode(nodoRoot,null);
		 crearNodosRecursivos(presupuesto.getCategoriaList(),root,presupuesto.getTotal());
			
		
		return root;
	}
	
	
	
	public TreeNode crearEstructura(Categoria catOrigen){
		int hijos=catOrigen.getCategoriaList()!=null?catOrigen.getCategoriaList().size():0;
		double asignable=catOrigen.getMonto()-catOrigen.getActual();
		int asignado= AsignacionCtrlr.percentCompleteCategoria(catOrigen);
		ViewNode nodoRoot=new ViewNode(catOrigen.getNombre(),hijos,catOrigen.getMonto(),catOrigen.getActual(),asignable,null,catOrigen,asignado,catOrigen.getCodigo(),0.0);
		
		TreeNode root =new DefaultTreeNode(nodoRoot,null);
		crearNodosRecursivos(catOrigen.getCategoriaList(),root,catOrigen.getMonto());
			
		
		return root;
	}
	
	public TreeNode crearEstructura(Presupuesto presupuesto,String filtro){
		int hijos=presupuesto.getCategoriaList()!=null?presupuesto.getCategoriaList().size():0;
		double disponible=presupuesto.getTotal()-presupuesto.getActual();
		ViewNode nodoRoot=new ViewNode(presupuesto.getNombrePresupuesto(),hijos,presupuesto.getTotal(),presupuesto.getActual(),disponible,null,presupuesto,100,presupuesto.getCodigo(),0.0 );
		
		TreeNode root =new DefaultTreeNode(nodoRoot,null);
		for(Categoria c:presupuesto.getCategoriaList())
		 crearNodosRecursivos(c,root,filtro,presupuesto.getYear(),presupuesto.getTotal());
			
		
		return root;
	}
	
//Se envian los hijos y el total del padre para calculo de porcentaje asignado
	private void crearNodosRecursivos(List<Categoria> list,			TreeNode nodoRoot,Double montoTotal) {
		if(list!=null&&!list.isEmpty()){
			for(Categoria cat:list ){
				int percent=AsignacionCtrlr.calcPercent(montoTotal, cat.getMonto());
				int hijos=cat.getCategoriaList()!=null?cat.getCategoriaList().size():0;
				ViewNode nodo=new ViewNode(cat.getNombre(),hijos,cat.getMonto(),cat.getActual(),cat.getMonto()-cat.getActual(),cat,cat,percent,cat.getCodigo(),montoTotal);
				TreeNode node=new DefaultTreeNode(nodo, nodoRoot);
				if(cat.getCategoriaList()!=null&&!cat.getCategoriaList().isEmpty()){
					
					crearNodosRecursivos(cat.getCategoriaList(), node,cat.getMonto());
				}
				
				
			}
		}
		
	}
	
	private boolean crearNodosRecursivos(Categoria cat,
			TreeNode nodoRoot,String filtro,String concat,Double montoTotal) {
		
		if(cat.getCategoriaList()==null||cat.getCategoriaList().isEmpty()){
			if(cat.getNombre().toUpperCase().contains(filtro.toUpperCase())){
				int percent=AsignacionCtrlr.calcPercent(montoTotal, cat.getMonto());
				concat+="-"+cat.getNombre();
				//int hijos=cat.getCategoriaList()!=null?cat.getCategoriaList().size():0;
				ViewNode nodo=new ViewNode(concat,0,cat.getMonto(),cat.getActual(),cat.getMonto()-cat.getActual(),cat,cat,percent,cat.getCodigo(),montoTotal);
				new DefaultTreeNode(nodo, nodoRoot);
				return true;
			}else
				return false;
		}else{
			for(Categoria c:cat.getCategoriaList() ){
				
					crearNodosRecursivos(c, nodoRoot,filtro,concat,c.getMonto());
					
				}
				
			}
		return true;
		
	
	}
	
	
	
	

}
