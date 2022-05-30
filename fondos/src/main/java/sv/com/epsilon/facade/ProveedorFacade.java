/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;


import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.response.NumberResponse;

/**
 *
 * @author Zeta
 */

public class ProveedorFacade  extends WSClient<Proveedor> {


    public ProveedorFacade() {
        super(Proveedor.class);
    }
    
    public String getCode() {
    	NumberResponse number= getNumber("/newcode");
    	if(number.getCod()==0)
    		return "FPT"+number.getValue();
    	else 
    		return "FPT"+01;
    }
   
  
 
}
