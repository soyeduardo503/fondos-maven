/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;


import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Projection;

/**
 *
 * @author Karina
 */

public class ProjectionFacade extends WSClient<Projection> {

//    Session session = HibernateUtil.getSessionFactory().openSession();
//    
//
//    @Override
//    protected Session getSession() {
//        return session;
//    }

    public ProjectionFacade() {
        super(Projection.class);
    }

	
	public List<Projection> getByPresupuesto(Integer idPresupuesto){
		return getList("/byPresupuesto/"+idPresupuesto);
	}
    
}
