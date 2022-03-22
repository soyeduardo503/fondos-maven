/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Presupuesto;

/**
 *
 * @author Karina
 */

public class PresupuestoFacade  extends WSClient<Presupuesto> {


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
    	return getAct();
    }
    

	public List<Integer> findYears() {
		
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<Integer>> responseEntity = 
					  restTemplate.exchange(
					    url("/years/"),
					    HttpMethod.GET,
					    null,
					    new ParameterizedTypeReference<List<Integer>>() {}
					  );
			if(200!=responseEntity.getStatusCodeValue())
				return new ArrayList<>();
			return  responseEntity.getBody();
		
		//return years;
	}


	
 
}
