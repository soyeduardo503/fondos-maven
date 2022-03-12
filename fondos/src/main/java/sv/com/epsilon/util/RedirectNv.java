/**
 * 
 */
package sv.com.epsilon.util;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sv.com.epsilon.ctrlr.wsclient.AppCtrlr;
import sv.com.epsilon.session.pojo.SistemaResponse;

/**
 * @author Zeta
 *
 */
@ManagedBean
@RequestScoped
 public final class RedirectNv implements Serializable {

	/**
	 * 
	 */
	 private String url;
	 private static String urlMain=new ReadProperty().read(	"url.properties", "mainPage");
	 private static String urlLogin=new ReadProperty().read(	"url.properties", "logoutPage");
	 private static final long serialVersionUID = -733566713187657308L;


	/**
	 * 
	 */
	public RedirectNv() {
		
	}

	public  RedirectNv(String url) {
		System.out.println("ingresando a redirigir:"+ url);
	
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + url);
			
		} catch (Exception e) {

			Log.error("Error al intentar enviar a la direccion \n: "+url, e);
		}
        finally{
        	url=null;
        }
	}
	
	public void Redirect(String url){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (Exception e) {
		
			//Log.error("Error al intentar enviar a la direccion \n: "+url, e);
		}
        finally{
        	url=null;
        }
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public static void goMain(String context,String token){
		try {
			SistemaResponse sis = new AppCtrlr().obtenerPagePrincipal(context, token);
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + sis.getPagePrincipal());
		} catch (Exception e) {
			new MessageGrowlContext().send("Error ", new ReadProperty("error.properties","noFoundPage").toString());
		}
        finally{
        	
        }
	}
	
	public static void logout(){
		try {
			Log.info("go to "+urlLogin);
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + urlLogin);
		} catch (IOException e) {
			new MessageGrowlContext().send("Error ", new ReadProperty("error.properties","noFoundPage").toString());
		}
        finally{
        	
        }
	}

	public static void Go(String url) throws IOException {
		Log.info("go to "+urlMain);
		FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + url);

		
	}

	
}
