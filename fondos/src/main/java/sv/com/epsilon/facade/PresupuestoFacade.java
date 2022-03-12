/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.util.Log;

/**
 *
 * @author Karina
 */

public class PresupuestoFacade  extends AbstractFacade<Presupuesto> {


    public PresupuestoFacade() {
        super(Presupuesto.class);
        //setToken(token);
    }
    
    
//    public List<Presupuesto> findAllActive(Integer idEmpresa) {
//    	return super.findAllActive(idEmpresa);
////    	getSession();
////    	try {
////	    	String qName="Presupuesto.findByAct";
////	    	   Transaction tx = session.beginTransaction();
////	    	  Query tr =session.createQuery("Select p from Presupuesto p where p.act=:act").setParameter("act", "A").setCacheMode(null);
////	         //Query t = session.getNamedQuery(qName).setParameter("act", "A");
////	        
////	        
////	        List<Presupuesto> list= (List<Presupuesto>)tr.list();
////	        tx.commit();
////	        session.evict(list);
////	        list.forEach( t->Log.info(t.getNombrePresupuesto()+" ->"+t.getTotal()+" "+t.getActual()));
////	        
////	        return list;
////    	}finally {
////    		close();
////    	}
//    }
    
    
    public List<Presupuesto> findAllSongActive() {
    	getSession();
    	try {
	    	String qName="Presupuesto.findByAct";
	    	   Transaction tx = session.beginTransaction();
	    	  Query tr =session.createQuery("Select p from Presupuesto p where p.act=:act").setParameter("act", "A").setCacheMode(null);
	         //Query t = session.getNamedQuery(qName).setParameter("act", "A");
	        
	        
	        List<Presupuesto> list= (List<Presupuesto>)tr.list();
	        tx.commit();
	        session.evict(list);
	        list.forEach( t->{Log.info(t.getNombrePresupuesto()+" ->"+t.getTotal()+" "+t.getActual());t.getCategoriaList().forEach(c->printSons(c));});
	        
	        return list;
    	}finally {
    		close();
    	}
    }
    private void printSons(Categoria c) {
    	Log.info("Categoria c"+c.getNombre()+" - "+c.getMonto());
    	if(!c.getCategoriaList().isEmpty()) {
    		c.getCategoriaList().forEach(c1->printSons(c1));
    		
    	}
    }

	public List<Integer> findYears() {
		try {
			String jsonResponse= sendRequest("GET","/presupuesto/year/"+getIdEmpresa(), getToken());
			Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
			return new Gson().fromJson(jsonResponse,listType);
		} catch (Exception e) {
			
			Log.error(e, "Error al llamar los años de los presupuestos");
			return new ArrayList<Integer>(); 
		}
		//return years;
	}


	
 
}
