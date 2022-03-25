/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Transaction;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.presupuesto.pojo.MontoUpdateRequest;
import sv.com.epsilon.util.Log;

/**
 *
 * @author Zeta
 */

public class CategoriaFacade extends WSClient<Categoria> {

    
	
    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    public Categoria obtenerCategoriasFromCategoriasPadre(Categoria categoria) throws Exception{
    	List<Categoria> list=getList("/categorias/children/"+categoria.getCodigo());
    	categoria.setCategoriaList(list);
    	return categoria;
//    	try {
//	    	String sql="Select i From Categoria i" +
//	    			" Where  i.idCategoriaPadre = :categoria" +
//	    			" Order By LENGTH(i.codigo) desc, i.codigo Desc";
//	    	Query q=session.createQuery(sql).setParameter("categoria",categoria);
//	    	List<Categoria> list=(List<Categoria>)q.list();
//	    	return list.size()>0?list.get(0):new Categoria();
//    	}finally {
//    		close();
//    	}
    
    }

	public List<Categoria> findByPresupuesto(Presupuesto idPresupuesto) {
		return getList("/categorias/presupuesto/id/"+idPresupuesto.getIdPresupuesto());
		/*
		getSession();
		try {
			String sql="Select i From Categoria i" +
	    			" Where i.idPresupuesto=:presupuesto " +
	    			" Order By LENGTH(i.codigo) desc, i.codigo Desc";
	    	Query q=session.createQuery(sql).setParameter("presupuesto",idPresupuesto);
	    	List<Categoria> list=(List<Categoria>)q.list();
	    	return list;
		}finally {
			close();
		}*/
	}
	
	public List<Categoria> findByCodPresupuesto(Presupuesto idPresupuesto) {
		return getList("/categorias/presupuesto/codigo/"+idPresupuesto.getCodigo());
		/*
		getSession();
		try {
			String sql="Select i From Categoria i" +
	    			" Where i.codigo like :codPresupuesto " +
	    			" Order By i.codigo ";
	    	Query q=session.createQuery(sql).setParameter("codPresupuesto",idPresupuesto.getCodigo()+"%"
	    			);
	    	List<Categoria> list=(List<Categoria>)q.list();
	    	return list;
		}finally {
			close();
		}*/
	}
	
	public List<Categoria> findByPresupuestoWithoutClose(Presupuesto idPresupuesto) {
		return findByPresupuesto(idPresupuesto);/*
		getSession();
		try {
			String sql="Select i From Categoria i" +
	    			" Where i.idPresupuesto=:presupuesto " +
	    			" Order By LENGTH(i.codigo) desc, i.codigo Desc";
	    	Query q=session.createQuery(sql).setParameter("presupuesto",idPresupuesto);
	    	List<Categoria> list=(List<Categoria>)q.list();
	    	return list;
		}finally {
			
		}*/
	}

//	public List<Categoria> findAllPresupuestoActive() {
//		getSession();
//		try {
//			String sql="Select i From Categoria i , Presupuesto p" +
//	    			" Where i.idPresupuesto=p and p.act='A' " +
//	    			" Order By LENGTH(i.codigo) desc, i.codigo Desc";
//	    	Query q=session.createQuery(sql);
//	    	List<Categoria> list=(List<Categoria>)q.list();
//	    	return list;
//		}finally {
//			close();
//		}
//	}

	public int CountByPresupuesto(Presupuesto p) {
		return count(p.getIdPresupuesto(), "/count/id_presupuesto");
//		try {
//			String sql="Select COUNT(i) From Categoria i , Presupuesto p" +
//	    			" Where i.idPresupuesto=p and p.act='A' and p=:presupuesto " ;
//	    			
//	    	 Query q = session.createQuery(sql).setParameter("presupuesto", p);
//			 return new BigDecimal((Long)q.uniqueResult()).intValue();
//		}finally {
//			close();
//		}
		//return 0;
	}

	public void findListSubCategoria(Categoria cat)  {
		try {
			obtenerCategoriasFromCategoriasPadre(cat);
		} catch (Exception e) {
			Log.info("Could be find children");
		}
//		getSession();
//		try {
//			String sql="Select i From Categoria i " +
//	    			" Where i.idCategoriaPadre=:cat " +
//	    			" Order By LENGTH(i.codigo) desc, i.codigo Desc";
//			
//	    	Query q=session.createQuery(sql).setParameter("cat", cat);
//	    	List<Categoria> list=(List<Categoria>)q.list();
//	    	cat.setCategoriaList( list);
//		}finally {
//			close();
//		}
	}
	public void findListRecursive(Categoria cat) {
		try {
			obtenerCategoriasFromCategoriasPadre(cat);
		} catch (Exception e) {
			Log.info("Error al cargar subcategorias");
		}
		if(cat.getCategoriaList()!=null&&cat.getCategoriaList().size()>0) {
			cat.getCategoriaList().forEach(catCurrent->{
				findListRecursive(catCurrent);
			});
		}
	}
//	public void findListSubCategoriaWithoutClose(Categoria cat) {
//		
//		getSession();
//		try {
//			String sql="Select i From Categoria i " +
//	    			" Where i.idCategoriaPadre=:cat " +
//	    			" Order By LENGTH(i.codigo) desc, i.codigo Desc";
//			
//	    	Query q=session.createQuery(sql).setParameter("cat", cat);
//	    	List<Categoria> list=(List<Categoria>)q.list();
//	    	cat.setCategoriaList( list);
//		}finally {
//			
//		}
//	}
	
	
	public void findListSubCategoriaByCod(Categoria cat) {
		findListRecursive(cat);
	}
//		
//		getSession();
//		try {
//			String sql="Select i From Categoria i " +
//	    			" Where i=:cat " +
//	    			" Order by i.idCategoriaPadre";
//			
//	    	Query q=session.createQuery(sql).setParameter("cat", cat);
//	    	Categoria cs=(Categoria) q.uniqueResult();
//	    	
//	    	loadChildren(cs);
//	    	//}
//	    	cat.setCategoriaList(cs.getCategoriaList());
//		}finally {
//			close();
//		}
//	}
//	private void loadChildren(Categoria c) {
//		Log.info(c.getNombre()+"-"+c.getCodigo()+"-"+c.getMonto());
//		if(c.getCategoriaList().size()>0)
//			for(Categoria children:c.getCategoriaList()) {
//				loadChildren(children);
//			}
//	}

	public int CountBySubCategoria(Categoria cat) {
		return count(cat.getIdCategoria(), "/count/children");
		
	}

	public List<Categoria> findBiggest5Gastos(Presupuesto p) {
		return getList("/top5/"+p.getIdPresupuesto());
//		getSession();
//		try {
//			String sql=" from categoria c where c.idPresupuesto==:p order by monto  " ;
//	    			
//	    	 Query q = session.createQuery(sql).setParameter("p", p);
//	    	 
//	    	 
//	    	 List<Categoria> l= q.list();
//			 l.forEach(c->Log.info(c.getNombre()+" "+c.getMonto()));
//			 return l;
//		}finally {
//			close();
//		}
		
	}

	public BigDecimal getMontoDisponible(String cod) {
		return mount( "/mount/"+cod);
	}

	public void saveList(List<Categoria> principales, Categoria padre) {
	

			
			principales.forEach(
						cat->{if(padre!=null)
							cat.setIdCategoriaPadre(padre); 
						try {
							this.save(cat);
							if(cat.getCategoriaList().size()>0) 
								this.saveList(cat.getCategoriaList(), cat); 
						} catch (Exception e) {
							e.printStackTrace();
						}
				});					
	
		
		
	}

	public List<Categoria> findAllChildrenSelectableActive() {
	  return list("/count/children/act");
	}

	public void updateMontoDisponible(Double monto, String codigoPadre) {
		
		try {
			update(new MontoUpdateRequest(codigoPadre, monto),"/disponible");
		} catch (Exception e) {
			Log.error(e, "Error al update ");
		}
	}
	public Categoria obtenerCategoriasFromCodigo(String cod){
    	Optional<Categoria> c= find("/codigo/"+cod);
    	return c.isPresent()?c.get():new Categoria();
    }

	
	public void close() {
		
	}
	
}
