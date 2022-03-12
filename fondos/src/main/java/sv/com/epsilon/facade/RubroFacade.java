/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;

import sv.com.epsilon.entities.Chequera;
import sv.com.epsilon.entities.Rubro;
import sv.com.epsilon.util.Log;

/**
 *
 * @author Zeta
 */

public class RubroFacade extends AbstractFacade<Rubro> {


    public RubroFacade() {
        super(Rubro.class);
    }
    
    public HashMap<String,Integer> findAllRubroActiveByName(){
    	getSession();
    	try {
	    	
	    	List<Rubro> list=this.findAllActive();
	    	HashMap<String,Integer> hash=new HashMap<String,Integer>();
	    	if(list!=null)
	    		list.forEach(cs->hash.put(cs.getNombre(), cs.getIdRubro()));
	    	return hash;
    	}finally {
    		close();
    	}
    }
    
}
