/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.file.UploadedFile;

import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.ctrlr.CargaProveedorCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.CargaValidacionCtrlr;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class CargaProveedoresMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8067151038649975541L;

	/**
	 * 
	 */
	
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB session;
	
	private List<Proveedor> list;
	private List<Proveedor> principales;

    private UploadedFile fileCSV;
    
    
    private boolean ok=false;
    
    
    
     

	
	public CargaProveedoresMB() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void cargarCategoria() {
		
	}
	
	public String flow(FlowEvent event) {
	       
         return event.getNewStep();
     
	}
	
	
	
	
	
	public List<Proveedor> getList() {
		return list;
	}
	public void setList(List<Proveedor> list) {
		this.list = list;
	}
	public UploadedFile getFileCSV() {
		return fileCSV;
	}
	public void setFileCSV(UploadedFile fileCSV) {
		this.fileCSV = fileCSV;
	}
	
	
	
	public UsuarioSessionMB getSession() {
		return session;
	}


	public void setSession(UsuarioSessionMB session) {
		this.session = session;
	}


	/**
	 * Se lee el archivo y se convierte a la lista que se guardara
	 * */
	public void handleFileUpload(FileUploadEvent event) {
		
	        this.fileCSV = null;
	        try {
	        	UploadedFile file = event.getFile();
		        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
		        	
					InputStream is = file.getInputStream();
					
		        	list=new CargaProveedorCtrlr().processFile(is,(Integer)(session.getValues().get("idEmpresa")));
		        	CargaValidacionCtrlr ctlr = new CargaValidacionCtrlr();
		        	//ctlr.validarCargaCategorias(list);
		        	//this.montoTotal=ctlr.getMontoAcumuladoPrincipal();
		        	
//		        	if(montoTotal>presupuesto.getTotal()) {
//		        		throw new Exception("Monto asignado a categorias principales es mayor al del presupuesto");
//		        	}
		            this.fileCSV = file;
		            FacesMessage msg = new FacesMessage("Completado", this.fileCSV.getFileName() + " fue cargado.");
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            
		           // this.principales=ctlr.obtenerCategoriasPrincipales();
		            ok=true;
		        }
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				ok=false;
			} catch (Exception e) {
				//new MessageGrowlContext().sendError("Error en carga", e.getMessage(), e);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error en distribucion de montos", e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, msg);
				ok=false;
			}
	        new ExecuteForm().update("loadCategoriasFromFile:panelResumen");
	    }
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
	public void save() {
		principales.forEach(cat->cat.setIdEmpresa(session.getIdEmpresa()));
		try {
			new ProveedorFacade().save(this.principales);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
