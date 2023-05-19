/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Catingreso;

/**
 *
 * @author Zeta
 */

public class CatingresoFacade extends WSClient<Catingreso> {


    public CatingresoFacade() {
        super(Catingreso.class);
    }
    
    

	
    
}
