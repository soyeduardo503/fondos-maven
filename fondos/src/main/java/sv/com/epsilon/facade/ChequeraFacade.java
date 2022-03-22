/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.math.BigInteger;
import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Chequera;
import sv.com.epsilon.entities.Cuenta;

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
		return new BigInteger( String.valueOf( getNumber("/current/"+chequera.getIdCuenta()).getValue())).intValue();
		
	}


    
}
