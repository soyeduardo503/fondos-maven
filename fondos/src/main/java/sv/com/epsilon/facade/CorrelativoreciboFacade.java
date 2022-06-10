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
import sv.com.epsilon.entities.Correlativorecibo;
import sv.com.epsilon.entities.Correlativorecibo;
import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.response.AccionResponse;
import sv.com.epsilon.util.Log;

/**
 *
 * @author Zeta
 */

public class CorrelativoreciboFacade extends WSClient<Correlativorecibo> {


    public CorrelativoreciboFacade() {
        super(Correlativorecibo.class);
    }

	public List<Correlativorecibo> findAllByCount(Cuenta c) {
		return getList("/cuenta/"+c.getIdCuenta());
		
	}

	public Integer findCurrentValue() {
		
		return new BigInteger( String.valueOf( getNumber("/current_cuenta/"+0).getValue())).intValue();
		
	}

	public void updateCurrent(Integer idCorrelativoreciboSelected) {
		Optional<AccionResponse> response = process("/update/"+idCorrelativoreciboSelected);
		Log.info("current value is updated: "+(response.isPresent()&&response.get().getStatus()==1));
		
		
	}

	public List<Correlativorecibo> findByIdCuenta(Integer idCuenta) {
		return getList("/cuenta/"+idCuenta);
	}

	


    
}
