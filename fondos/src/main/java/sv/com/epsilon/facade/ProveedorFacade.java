/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.util.Log;

/**
 *
 * @author Zeta
 */

public class ProveedorFacade  extends AbstractFacade<Proveedor> {


    public ProveedorFacade() {
        super(Proveedor.class);
    }
    
    @Override
    public List<Proveedor> findAllActive() {
    	getSession();
    	try {
	    	
	    	   Transaction tx = session.beginTransaction();
	    	  Query tr =session.createQuery("Select p from Presupuesto p where p.act=:act").setParameter("act", "A").setCacheMode(null);
	         //Query t = session.getNamedQuery(qName).setParameter("act", "A");
	        
	        
	        List<Proveedor> list= (List<Proveedor>)tr.list();
	        tx.commit();
	        session.evict(list);
	        list.forEach( t->Log.info(t.getNombre()+" ->"+t.getIdProveedor()+" "+t.getNombreLegal()));
	        
	        return list;
    	}finally {
    		close();
    	}
    }
    
    @Override
    public List<Proveedor> findAll() {
    	getSession();
    	try {
	    	
	    	  Transaction tx = session.beginTransaction();
	    	  Query tr =session.createQuery("Select p from Proveedor p").setCacheMode(null);
	         //Query t = session.getNamedQuery(qName).setParameter("act", "A");
	        
	        
	        List<Proveedor> list= (List<Proveedor>)tr.list();
	        tx.commit();
	        session.evict(list);
	        list.forEach( t->Log.info(t.getNombre()+" ->"+t.getIdProveedor()+" "+t.getNombreLegal()+" "+t.getDui()+" "+t.getTipo()+" "+t.getNit()+" "+t.getNcuenta()+" "+t.getNrc()));
	        
	        return list;
    	}finally {
    		close();
    	}
    }
  
 
}
