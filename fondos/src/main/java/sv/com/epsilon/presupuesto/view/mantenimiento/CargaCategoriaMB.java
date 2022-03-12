/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.file.UploadedFile;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.presupuesto.ctrlr.CargaCatalogoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.CargaValidacionCtrlr;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
public class CargaCategoriaMB {

	/**
	 * 
	 */
	private Presupuesto presupuesto;
	
	private List<Categoria> list;
	private List<Categoria> principales;

    private UploadedFile fileCSV;
    
    private Double montoTotal;
    private boolean ok=false;
    
    
    
     

	
	public CargaCategoriaMB() {
		// TODO Auto-generated constructor stub
	}
	public void asignarPresupuesto(Presupuesto presupuesto) {
		this.presupuesto=presupuesto;
	}
	
	public void cargarCategoria() {
		
	}
	
	public String flow(FlowEvent event) {
	       
         return event.getNewStep();
     
	}
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	
	
	
	
	 public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public List<Categoria> getList() {
		return list;
	}
	public void setList(List<Categoria> list) {
		this.list = list;
	}
	public UploadedFile getFileCSV() {
		return fileCSV;
	}
	public void setFileCSV(UploadedFile fileCSV) {
		this.fileCSV = fileCSV;
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
					
		        	list=new CargaCatalogoCtrlr().processFile(is, presupuesto.getCodigo());
		        	CargaValidacionCtrlr ctlr = new CargaValidacionCtrlr();
		        	ctlr.validarCargaCategorias(list);
		        	this.montoTotal=ctlr.getMontoAcumuladoPrincipal();
		        	
		        	if(montoTotal>presupuesto.getTotal()) {
		        		throw new Exception("Monto asignado a categorias principales es mayor al del presupuesto");
		        	}
		            this.fileCSV = file;
		            FacesMessage msg = new FacesMessage("Completado", this.fileCSV.getFileName() + " fue cargado.");
		            FacesContext.getCurrentInstance().addMessage(null, msg);
		            
		            this.principales=ctlr.obtenerCategoriasPrincipales();
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
		principales.forEach(cat->cat.setIdPresupuesto(presupuesto));
		new CategoriaFacade().saveList(this.principales,null);
	}
	
	
}
