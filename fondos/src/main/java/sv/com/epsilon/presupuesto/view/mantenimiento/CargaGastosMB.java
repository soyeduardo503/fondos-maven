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

import lombok.Data;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.presupuesto.ctrlr.CargaGastoCtrlr;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.Log;

/**
 * @author 50364
 *
 */
@ManagedBean
@ViewScoped
@Data
public class CargaGastosMB {

	/**
	 * 
	 */
	private Presupuesto presupuesto;

	private List<Gasto> list;
	

	private UploadedFile fileCSV;

	


	public CargaGastosMB() {
		// TODO Auto-generated constructor stub
	}

	public void asignarPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
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


	public UploadedFile getFileCSV() {
		return fileCSV;
	}

	public void setFileCSV(UploadedFile fileCSV) {
		this.fileCSV = fileCSV;
	}

	

	/**
	 * Se lee el archivo y se convierte a la lista que se guardara
	 */
	public void handleFileUpload(FileUploadEvent event) {

		this.fileCSV = null;
		try {
			UploadedFile file = event.getFile();
			if (file != null && file.getContent() != null && file.getContent().length > 0
					&& file.getFileName() != null) {

				InputStream is = file.getInputStream();

				list = new CargaGastoCtrlr().processFile(is);
				
				FacesMessage msg = new FacesMessage("Completado", this.fileCSV.getFileName() + " fue cargado.");
				FacesContext.getCurrentInstance().addMessage(null, msg);

				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e, "error encontrado");
		} catch (Exception e) {
			// new MessageGrowlContext().sendError("Error en carga", e.getMessage(), e);
			Log.error(e, "error encontrado");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en distribucion de montos",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		new ExecuteForm().update("loadCategoriasFromFile:panelResumen");
	}

	
	
	
}
