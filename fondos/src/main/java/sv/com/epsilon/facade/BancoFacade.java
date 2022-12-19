/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.HashMap;
import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Banco;

/**
 *
 * @author Zeta
 */

public class BancoFacade extends WSClient<Banco> {


    public BancoFacade() {
        super(Banco.class);
    }
    
    public HashMap<String,Integer> buscarHashBancosActivos(){
    	List<Banco> list = this.findAllActive();
    	HashMap<String,Integer> hash=new HashMap<>();
    	list.forEach(b->{hash.put(b.getNombre(), b.getIdBanco());});
    	return hash;
    }

	public List<Banco> findAllByEmpresa(Integer value) {
		return getAct();
//		getSession();
//		try {
//			String query="SELECT c FROM Banco c WHERE c.idEmpresa = :idEmpresa";
//			if(value==null) {
//				return this.findAll();
//			}
//			
//			Query t = session.createQuery(query).setParameter("idEmpresa", value);
//			return t.list();
//		}finally {
//			close();
//		}
	}
    
}
