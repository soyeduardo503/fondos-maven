/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.presupuesto.pojo.ImageBucket;

/**
 *
 * @author Zeta
 */
@Slf4j
public class ImageBucketFacade extends WSClient<ImageBucket> {


    public ImageBucketFacade() {
        super(ImageBucket.class,"localhost","9001","WSImageHandler",true);
    }
    
   
    public List<ImageBucket> fetch(){
    	try {
    		List<ImageBucket> list=getList("/image/download/");
    		return list;
    	}catch (Exception e) {
			log.error("Error al cargar imagenes", e);
		}
    	
    	return new ArrayList<>();
    }
    
}
