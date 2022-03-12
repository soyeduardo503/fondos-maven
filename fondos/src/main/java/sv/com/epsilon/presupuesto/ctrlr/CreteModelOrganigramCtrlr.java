package sv.com.epsilon.presupuesto.ctrlr;

import java.util.List;

import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;


import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;

public class CreteModelOrganigramCtrlr {

	


	public CreteModelOrganigramCtrlr() {
		
	}
	
	public OrganigramNode fromPresupuesto(Presupuesto presupuesto) throws Exception {
		OrganigramNode root=new   DefaultOrganigramNode("root", presupuesto.getNombrePresupuesto(), null);
		
		findSons(presupuesto.getCategoriaList(), root);
		
		root.setCollapsible(false);
		root.setDroppable(true);
		return root;
	}
	
	public OrganigramNode fromCategoria(Categoria categoria) throws Exception{
		OrganigramNode root=new   DefaultOrganigramNode("root", categoria, null);
		
		findSons(categoria.getCategoriaList(), root);
		
		root.setCollapsible(false);
		root.setDroppable(true);
		return root;
	}
	
	
	 private void findSons(List<Categoria> categoriaList, OrganigramNode parent) throws Exception{
		if(categoriaList!=null) {
			OrganigramNode org;
			for(Categoria c:categoriaList) {
				org=addDivision(parent, c);
				if(c.getCategoriaList().size()>0)
					findSons(c.getCategoriaList(), org);
			}
		}
		
	}

	private OrganigramNode addDivision(OrganigramNode parent, Categoria name) throws Exception{
	        OrganigramNode divisionNode = new DefaultOrganigramNode("division", name, parent);
	        divisionNode.setDroppable(true);
	        divisionNode.setDraggable(true);
	        divisionNode.setSelectable(true);
	        divisionNode.setCollapsible(false);

	       

	        return divisionNode;
	    }

}
