/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.List;

import org.hibernate.Query;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Chequera;
import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.util.Log;

/**
 *
 * @author Zeta
 */

public class ChequeraFacade extends AbstractFacade<Chequera> {


    public ChequeraFacade() {
        super(Chequera.class);
    }

	public List<Chequera> findAllByCount(Cuenta c) {
		getSession();
    	try {
	    	String sql="Select c From Chequera c" +
	    			" Where  c.idCuenta = :c" ;
	    	Query q=session.createQuery(sql).setParameter("c",c.getIdCuenta());
	    	List<Chequera> list=(List<Chequera>)q.list();
	    	if(list!=null)
	    		list.forEach(cs->Log.info(cs.getCorrelativo()+"-"+cs.getNombre()+" - "+cs.getDesde()+" - "+cs.getHasta()+" - "+cs.getIdCuenta()));
	    	return list;
    	}finally {
    		close();
    	}
		
	}

	public Integer findCurrentValue() {
		getSession();
    	try {
	    	String sql="Select c From Chequera c" +
	    			" Where  c.act ='A' " ;
	    	Query q=session.createQuery(sql);
	    	List<Chequera> list=(List<Chequera>)q.list();
	    	if(list!=null)
	    		list.forEach(cs->Log.info(cs.getCorrelativo()+"-"+cs.getNombre()+" - "+cs.getDesde()+" - "+cs.getHasta()+" - "+cs.getIdCuenta()));
	    	return  list.get(0).getCorrelativo();
    	}finally {
    		close();
    	}
		
	}
    
}
