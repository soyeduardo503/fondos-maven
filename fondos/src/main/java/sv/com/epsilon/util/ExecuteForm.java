/**
 * 
 */
package sv.com.epsilon.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;


/**
 * @author usuario07
 * @date 11/11/2013
 * @time 15:03:28
 * @PCBM
 */
@ManagedBean
@ViewScoped
public   class ExecuteForm  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8648379148711364590L;
	/**
	 * 
	 */
	
	private String mensaje="";
	private String execute="";
	private String update="";
	public ExecuteForm() {

	}

	

	
	public void  mostrarConfirmacion(String update,String execute){		
		this.mostrarConfirmacion(update, execute,null);
	}

	public void  mostrarConfirmacion(String update,String execute,String mensaje){
		this.mensaje=mensaje;
		this.execute=execute;
		this.update=update;
		
		ejecutarAcciones();
	}
	
	public void ExecuteUpdate(String update,String execute){
		this.mensaje=null;
		this.execute=execute;
		this.update=update;
		ejecutarAcciones();
	}
	public void Execute(String execute){
		this.mensaje=null;
		this.execute=execute;
		this.update=null;
		ejecutarAcciones();
	}
	public void Update(String update){
		this.mensaje=null;
		this.execute=null;
		this.update=update;
		ejecutarAcciones();
	}
	
	
	
	
	public void ejecutarOk(String update,String execute){
		this.execute=execute;
		this.update=update;
		ejecutarAcciones();
	}
	public void ejecutarCancelar(String execute){
		this.execute=execute;
		ejecutarAcciones();
	}
	
	public void ejecutarOk(){
		this.execute="PF('DlgMsjAceptar').show();";
		this.update="frmMsjAceptar";
		ejecutarAcciones();
	}

	
	public boolean  ejecutarAcciones(){
		
	//	log.info("Actualizando "+update);
		try {
			if (update != null && !update.equals(""))	
			{
				update(update);
				//PrimeFaces.current().ajax().update(update);
				//PrimeFaces.current().
				//FacesContext.getCurrentInstance().get.update(update);
			}
			if (execute != null && !execute.equals("")){
				//RequestContext context=PrimeFaces.current().;
				execute(execute);
			}
			return true;
		} catch (Exception e) {
			//Log.error("error al actualziar y/o ejecutar", e);
			Log.error(e, "error al actualizar el componente");
			return false;
		}
	}
	
	public void update(String componente) {
		PrimeFaces.Ajax currentAjax = PrimeFaces.current().ajax();
		currentAjax.update(componente);
	}
	
	public void execute(String componente) {
		PrimeFaces.current().executeScript(componente);
	}
	/**
	 * @param args
	 */

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	/**
	 * @return the execute
	 */
	public String getExecute() {
		return execute;
	}



	/**
	 * @param execute the execute to set
	 */
	public void setExecute(String execute) {
		this.execute = execute;
	}



	/**
	 * @return the update
	 */
	public String getUpdate() {
		return update;
	}



	/**
	 * @param update the update to set
	 */
	public void setUpdate(String update) {
		this.update = update;
	}




	public void Update(List<String> update) {
		Log.info(update);
		PrimeFaces.Ajax currentAjax = PrimeFaces.current().ajax();
		currentAjax.update(update);		
		//log.info("Actualizando "+update);
	}




	



	public void Update(String[] updates) {
		Update(new ArrayList<String>(Arrays.asList(updates)));
		
	}


	public void Execute(String[] exes) {
		Execute(new ArrayList<String>(Arrays.asList(exes)));
		
	}
//	public void Execute(List<String> exes) {
//		Execute(exes);
//		
//	}

	public void Execute(List<String> exe) {
		if(exe!=null&&exe.size()>0)
			exe.forEach(wgt->PrimeFaces.current().executeScript(wgt));
		
		
		
	}

	public void ExecuteUpdate(String[] update2, String[] exe) {
		Update(update2);
		Execute(exe);
		
	}
	public void ExecuteUpdate(List<String> update2, List<String> exe) {
		Update(update2);
		Execute(exe);
		
	}

	

	

}
