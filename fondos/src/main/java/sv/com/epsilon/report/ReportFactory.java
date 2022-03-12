package sv.com.epsilon.report;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import sv.com.epsilon.application.CatalogosApp;
import sv.com.epsilon.entities.Parametro;
import sv.com.epsilon.facade.ParametroFacade;
import sv.com.epsilon.util.Log;

public class ReportFactory extends AbstractReport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<?> collecion;
	private HashMap<String, Object> parametros;
	private String url;
	private int tipoDataSource;
	
	public ReportFactory(){
		super();
	}
	
	
public  String obtenerUlrCarpetaReportes() {
		

		try {
			return new ParametroFacade().findByName("VAR_URL_DOCS").getValor();
		} catch (Exception e) {
			Log.error("Error en obtener url ", e);
			return "C:\\reportes\\";
		}
	}
	

public  String obtenerURLCarpetaimagenes() {
	

	try {
		return new ParametroFacade().findByName("VAR_URL_IMG").getValor();
	} catch (Exception e) {
		Log.error("Error en obtener url ", e);
		return "C:\\reportes\\";
	}
}
	
	/**
	 * Dispose
	 */
	protected void clean(){
		if(collecion!=null){
			collecion.clear();
			collecion=null;
		}
		if(parametros!=null){
			parametros.clear();
			parametros=null;
		}
//		if(this.reportes!=null){
//			this.reportes.clear();
//			this.reportes=null;
//		}		
//		if(this.jasperPrintList!=null){
//			this.jasperPrintList.clear();
//			this.jasperPrintList=null;
//		}
	}

	/**
	 * Agrega reportes a la lista
	 */
	public void addReport(){					
		add(this);		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTipoDataSource() {
		return tipoDataSource;
	}

	/**
	 * Tipo de datasource 1=JREmptyDataSource; 2=JRBeanCollectionDataSource
	 * 
	 * @param tipoDataSource
	 */
	public void setTipoDataSource(int tipoDataSource) {
		this.tipoDataSource = tipoDataSource;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<?> getCollecion() {
		return collecion;
	}

	/**
	 * 
	 * @param collecion
	 */
	public void setCollecion(List<?> collecion) {
		this.collecion = collecion;
	}

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getParametros() {
		return parametros;
	}

	/**
	 * 
	 * @param parametros
	 */
	public void setParametros(HashMap<String, Object> parametros) {
		this.parametros = parametros;
	}

	/**
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url.replace("//", "/");
	}
}
