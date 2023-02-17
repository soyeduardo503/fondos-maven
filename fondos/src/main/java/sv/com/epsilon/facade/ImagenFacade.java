/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.ArrayList;
import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Imagen;
import sv.com.epsilon.presupuesto.pojo.ImageBucket;

/**
 *
 * @author Zeta
 */

public class ImagenFacade extends WSClient<Imagen> {

   

    public ImagenFacade() {
        super(Imagen.class);
    }

	public boolean save(List<ImageBucket> listImages,Gasto gasto) {
		List<Imagen> list=new ArrayList<>();
		listImages.forEach(
			img->{
				list.add(new Imagen(img.getImage(),gasto));
			}
		);
		
		try {
			this.save(list);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}
    
}
