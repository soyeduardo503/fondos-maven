/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.Optional;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Financiamiento;

/**
 *
 * @author Zeta
 */

public class FinanciamientoFacade extends WSClient<Financiamiento> {

   

    public FinanciamientoFacade() {
        super(Financiamiento.class);
    }
    
    public Financiamiento obtenerLastFromCaracteristica(){
//    	getSession();
//    	try {
//    	String sql="Select i From Caracteristica i" +    			
//    			" Order By LENGTH(i.codigo) desc, i.codigo Desc";
//    	Query q=session.createQuery(sql);
//    	List<Financiamiento> list=(List<Financiamiento>)q.list();
//    	return list.size()>0?list.get(0):new Financiamiento();
//    	}finally {
//    		close();
//    	}
    	return new Financiamiento();
    }

	public Optional<Financiamiento> findFirst(Integer idPresupuestoSelected, Integer mesCierre) {
		
		return get("/searchFirst/"+idPresupuestoSelected+"/"+mesCierre);
	}

	
}
