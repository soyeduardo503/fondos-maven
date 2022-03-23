/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.Optional;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.response.AccionResponse;

/**
 *
 * @author Zeta
 */

public class MovimientoFacade extends WSClient<Movimiento> {


    public MovimientoFacade() {
        super(Movimiento.class);
    }

	public void save(Movimiento mov) throws Exception{
		save(mov);
		Optional<AccionResponse> eval = process( "/categoria/update/"+mov.getIdCategoria().getIdCategoria()+"/"+mov.getMonto());
		if(!eval.isPresent()) {
			throw new Exception("Error al guardar datos relacionados al movimiento");
		}
	}
    
}
