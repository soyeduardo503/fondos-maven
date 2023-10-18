/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Cierre;
import sv.com.epsilon.util.Mes;

/**
 *
 * @author Zeta
 */

public class CierreFacade extends WSClient<Cierre> {


    public CierreFacade() {
        super(Cierre.class);
    }
    
    
    public Optional<Cierre> find(Integer idPresupuesto,Integer month) {
    	return this.find("/byMonth/"+idPresupuesto+"/"+month);
    }

	public List<Mes> findMesesCerrados(Integer idPresupuesto) {
		 List<Integer> list=(List<Integer>)this.list("/mesesCerrados/"+idPresupuesto+"/",Integer.class);
		 List<Mes> listMes=new ArrayList<>();
		 list.forEach(m->listMes.add(new Mes(m)));
		return listMes;
	}
    

	
    
}
