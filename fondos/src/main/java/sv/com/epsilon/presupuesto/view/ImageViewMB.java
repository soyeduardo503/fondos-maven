/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.facade.ImageBucketFacade;
import sv.com.epsilon.response.NumberResponse;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
public class ImageViewMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size=0;
	private int position=-1;
	private ImageBucketFacade facade=new ImageBucketFacade();
	private List<Integer> list;
	
	
	public List<Integer> getList() {
		return list;
	}


	public void setList(List<Integer> list) {
		this.list = list;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}





	public void load() {
		NumberResponse count = facade.getNumber("/image/count");
		if(count.getCod()!=0) {
			return ;
		}
		if(count.getIntValue()==0){
			new MessageGrowlContext().send("No hay imagenes a mostrar","Nos se han cargado imagenes");
			return ;
			
		}
		int i=0;
		
		size=count.getIntValue();
		position=0;
		list=new ArrayList<>();
		while(i<size) {
			list.add(i);
			i++;
		}
	}
}
