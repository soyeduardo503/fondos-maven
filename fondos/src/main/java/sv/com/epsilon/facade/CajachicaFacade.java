/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.List;
import java.util.Optional;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Cajachica;
import sv.com.epsilon.response.NumberResponse;

/**
 *
 * @author Zeta
 */

public class CajachicaFacade extends WSClient<Cajachica> {


    public CajachicaFacade() {
        super(Cajachica.class);
    }
    
    

	public List<Cajachica> findAllByEmpresa(Integer value) {
		return getAct();
	}
	
	public Double getDisponible(Integer idCajachica) {
		NumberResponse disponible = getNumber("/disponible/"+idCajachica);
		return disponible.getCod()==0?(Double)disponible.getValue():0.0;
	}



	public List<Cajachica> findByYear(Integer yearSelected) {
		
		return  getList("/year/"+yearSelected);
		
	}
    
}
