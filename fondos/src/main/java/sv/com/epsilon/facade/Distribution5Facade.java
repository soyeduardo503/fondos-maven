/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.presupuesto.pojo.Distribution;

/**
 *
 * @author Zeta
 */

public class Distribution5Facade extends WSClient<Distribution> {


    public Distribution5Facade() {
        super(Distribution.class);
        this.setNoNameClass(true);
    }
    
    

	public List<Distribution> findByPresupuesto(String codPresupuesto) {
		return getList("/kpi/distribution/"+codPresupuesto);

	}
    
}
