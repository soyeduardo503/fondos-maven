/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.HashMap;
import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Rubro;

/**
 *
 * @author Zeta
 */

public class RubroFacade extends WSClient<Rubro> {


    public RubroFacade() {
        super(Rubro.class);
    }
    
    public HashMap<String,Integer> findAllRubroActiveByName(){
    	
    	try {
	    	
	    	List<Rubro> list=this.findAllActive();
	    	HashMap<String,Integer> hash=new HashMap<String,Integer>();
	    	if(list!=null)
	    		list.forEach(cs->hash.put(cs.getNombre(), cs.getIdRubro()));
	    	return hash;
    	}finally {
    	
    	}
    }
    
}
