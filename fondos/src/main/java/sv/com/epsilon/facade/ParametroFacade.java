/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;


import java.util.List;

import org.hibernate.Query;

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
		getSession();
		try {
		   Query Query = session.createQuery("Select p FROM Parametro p WHERE p.nombre = :nombre");
		  	  Query.setParameter("nombre", nombre);
		  	  return (Parametro) Query.uniqueResult();
		}finally {
			close();
		}
	}
	
	public String getValueByName(String nombre) {
		getSession();
		try {
		   Query Query = session.createQuery("Select  valor FROM Parametro p WHERE p.nombre = :nombre");
		  	  Query.setParameter("nombre", nombre);
		  	  List<String> list= Query.list();
		  	  
		  	  if(list.isEmpty())
		  		  return "";
		  	  else		  		  
		  		  return list.get(0) ;
		}finally {
			close();
		}
	}
    
}
