/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Presupuesto;

/**
 *
 * @author Zeta
 */

public class GastoFacade extends WSClient<Gasto> {

   

    public GastoFacade() {
        super(Gasto.class);
    }

	public List<String> findLastByPresupuesto(Presupuesto p) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
