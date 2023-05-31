/**
 * 
 */
package sv.com.epsilon.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

/**
 * @author usuario07
 * @date 11/09/2013
 * @time 14:42:52
 * @PCBM
 */
@ManagedBean
@ViewScoped
public class MessageGrowlContext implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -7266085555944013770L;

	/**
	 * <xml>
	 */
	private String errorTxt; 


	public MessageGrowlContext() {
	}

	/**
	 * @param text
	 * @param summary
	 * @param detail
	 */
	

	


	public  void send(String summary,String detail){
		try {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,summary,detail);
			Log.info(summary+" \n "+detail);
		
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			Log.error("Error al mandar el mensaje ", e);
			
		}
		
	}
	
	public String getErrorTxt() {
		return errorTxt;
	}

	public void setErrorTxt(String errorTxt) {
		this.errorTxt = errorTxt;
	}

	public  void sendWarning(String summary,String detail){
		try {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,summary,detail);
			Log.info(summary+" \n "+detail);
		
			FacesContext.getCurrentInstance().addMessage(null, msg);
			new ExecuteForm().Update("status");
		} catch (Exception e) {
			Log.error("Error al mandar el mensaje ", e);
			
		}
		
	}
	
	public  void sendError(String summary,String detail, Exception e){
		try {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,summary,detail);
			this.errorTxt=e.getMessage();
			Log.error(summary+" \n "+detail, e);
			//new ExecuteForm().ExecuteUpdate("alertWarning", "PF('dlgWarningDataWgt').show();");
			FacesContext.getCurrentInstance().addMessage("status", msg);
			new ExecuteForm().Update("status");
		} catch (Exception e1) {
			Log.error("Error al mandar el mensaje ", e1);
		
		}
		
	}
	
	public  void send(String summary,String detail,String idGrowl, Object name){
		try {
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,summary,detail);
			Log.info(summary+" \n "+detail);
		
			FacesContext.getCurrentInstance().addMessage(null, msg);
			new ExecuteForm().Update("status");
		} catch (Exception e) {
			Log.error("Error al mandar el mensaje ", e);
			
		}
		
	}
	

}
