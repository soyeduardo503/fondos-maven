/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;


import java.util.Optional;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Parametro;

/**
 *
 * @author Karina
 */

public class ParametroFacade extends WSClient<Parametro> {

//    Session session = HibernateUtil.getSessionFactory().openSession();
//    
//
//    @Override
//    protected Session getSession() {
//        return session;
//    }

    public ParametroFacade() {
        super(Parametro.class);
    }

	public Parametro findByName(String nombre) {
		Optional<Parametro> param = get("/parametro/nombre/"+nombre);
		return param.isPresent()?param.get():null;
	}
	
	public String getValueByName(String nombre) {
		return findByName(nombre).getValor();
	}
    
}
