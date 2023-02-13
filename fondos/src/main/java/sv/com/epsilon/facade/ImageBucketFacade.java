/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.util.List;

import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.presupuesto.pojo.ImageBucket;

/**
 *
 * @author Zeta
 */

public class ImageBucketFacade extends WSClient<ImageBucket> {


    public ImageBucketFacade() {
        super(ImageBucket.class,"localhost","9001","WSImageHandler",true);
    }
    
   
    public List<ImageBucket> fetch(){
    	List<ImageBucket> list=getList("/image/download/");
    	
    	return list;
    }
    
}
