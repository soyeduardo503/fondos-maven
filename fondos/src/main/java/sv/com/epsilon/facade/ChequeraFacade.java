/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Chequera;
import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.response.AccionResponse;
import sv.com.epsilon.util.Log;

/**
 *
 * @author Zeta
 */

public class ChequeraFacade extends WSClient<Chequera> {


    public ChequeraFacade() {
        super(Chequera.class);
    }

	public List<Chequera> findAllByCount(Cuenta c) {
		return getList("/cuenta/"+c.getIdCuenta());
		
	}

	public Integer findCurrentValue(Chequera chequera) {
		
		return new BigInteger( String.valueOf( getNumber("/current/"+chequera.getIdChequera()).getValue())).intValue();
		
	}

	public void updateCurrent(Chequera chequeraSelected) {
		Optional<AccionResponse> response = process("/chequera/update/");
		Log.info("current value is updated: "+(response.isPresent()&&response.get().getStatus()==1));
		
		
	}

	public List<Chequera> findByIdCuenta(Integer idCuenta) {
		// TODO Auto-generated method stub
		return getList("/cuenta/"+idCuenta);
	}

	


    
}
