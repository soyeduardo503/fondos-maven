/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import sv.com.epsilon.entities.Categoria;

/**
 * @author 50364
 *
 */
public class CategoriaCtrlr {

	
	public DefaultDiagramModel createDiagram(Categoria cat) {
		
		//findSons(cat,list);
		
		 DefaultDiagramModel dia=createNodePrincipal(cat);
		return dia;
	}
	
	
	
//	private void findSons(Categoria current, List<Categoria> list) {
//		int i=0;
////		List<Categoria> sons=new ArrayList<Categoria>();
////		while(!list.isEmpty()) {
////			Categoria f=list.get(0);
////			if(f.getIdCategoriaPadre()!=null&& current.equals(f.getIdCategoriaPadre())) {
////				sons.add(f);
////				list.remove(0);
////				
////				findSons(f, list);
////			}else {
////				list.remove(0);
////			}
////			
////		}
//		//current.setCategoriaList(sons);
//	}



	private DefaultDiagramModel createNodePrincipal(Categoria cat) {
		DefaultDiagramModel model=new DefaultDiagramModel();
		
		model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
		 Element p = new Element(cat.getNombre(), "20em", "1em");
		 
	     p.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
	     model.addElement(p);
	     int level=0;
	     addSons(cat,p,level,model);
	     return model;
	}
	
	private Element addNode(Element current,Categoria c,Integer level,Integer col, DefaultDiagramModel model,boolean up,boolean down) {
		
		 Element p = new Element(c.getNombre(), ((5*col))+"em", (5+level)*3+"em");
		 
		 if(up)
			 p.addEndPoint(createEndPoint(EndPointAnchor.TOP));
		 if(down)
			 p.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
	     model.addElement(p);
	     StraightConnector connector = new StraightConnector();
	    // connector.setPaintStyle("{stroke:'#404a4e', strokeWidth:3}");
	    // connector.setHoverPaintStyle("{stroke:'#20282b'}");
	     
	     model.connect(new Connection(current.getEndPoints().get(0), p.getEndPoints().get(level), connector));
	     
	     
	     
	     return p;
	}
	
	 private void addSons(Categoria cat, Element p, int level,DefaultDiagramModel model) {
		if(cat.getCategoriaList().size()>0) {
			int col=0;
			for(Categoria son:cat.getCategoriaList()) {
				Element el = addNode(p, son, level,col++, model,true,son.getCategoriaList().size()>0);
				if(son.getCategoriaList().size()>0)
					addSons(son,el,level+1,model);
			}
		}
		
	}



	private EndPoint createEndPoint(EndPointAnchor anchor) {
	        DotEndPoint endPoint = new DotEndPoint(anchor);
	        endPoint.setStyle("{fill:'#404a4e'}");
	        endPoint.setHoverStyle("{fill:'#20282b'}");

	        return endPoint;
	    }

}
