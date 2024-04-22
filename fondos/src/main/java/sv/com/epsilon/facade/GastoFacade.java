/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Presupuesto;

/**
 *
 * @author Zeta
 */

@Slf4j
public class GastoFacade extends WSClient<Gasto> {

   

    public GastoFacade() {
        super(Gasto.class);
    }

	public List<String> findLastByPresupuesto(Presupuesto p) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Gasto> findByRange(Integer vinicial, Integer vfinal, Integer tipodesembolso) {
		if(tipodesembolso==0)
			return getList("/range/"+vinicial+"/"+vfinal+"/"+tipodesembolso);
		else
			return getList("/range/"+vinicial+"/"+vfinal);
	}

	public boolean updateFecha(Gasto gastoSelected) {
		try {
			return this.action("/updateFecha", true, gastoSelected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			
		}
		
		return false;
	}
    
}
