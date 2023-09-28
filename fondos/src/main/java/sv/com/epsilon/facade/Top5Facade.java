/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.presupuesto.pojo.Top5Gasto;

/**
 *
 * @author Zeta
 */

public class Top5Facade extends WSClient<Top5Gasto> {


    public Top5Facade() {
        super(Top5Gasto.class);
        this.setNoNameClass(true);
    }
    
    

	public List<Top5Gasto> findByPresupuesto(Integer idPresupuesto) {
		return getList("/kpi/top5Gasto/"+idPresupuesto);
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



	public List<Top5Gasto> findByPresupuestoMensual(Integer idPresupuestoSelected) {
		// TODO Auto-generated method stub
		return getList("/kpi/top5GastoMes/"+idPresupuestoSelected);
	}
    
}
