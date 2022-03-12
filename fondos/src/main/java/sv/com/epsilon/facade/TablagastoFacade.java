/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.io.Serializable;

import sv.com.epsilon.entities.Tablagasto;

/**
 *
 * @author Zeta
 */

public class TablagastoFacade extends AbstractFacade<Tablagasto> implements Serializable{

 
    /**
	 * 
	 */
	private static final long serialVersionUID = -7784740488931748957L;

	public TablagastoFacade() {
        super(Tablagasto.class);
    }
    
}
