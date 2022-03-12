package sv.com.epsilon.report;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import sv.com.epsilon.util.Log;

public abstract class AbstractReport implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;
	
	private   String              nombreReporte;
	protected List<ReportFactory> reportes;
	protected List<JasperPrint>   jasperPrintList;	
	private   boolean             mostrarEnPantalla;
	private   String              formato;
	private   String              contentType;
	private   Class<?>            xClass;
	
	
	
	public AbstractReport(){
		mostrarEnPantalla=true;
		formato="pdf";
	}
		
	/**
	 * 
	 * @throws Exception
	 */
	public void showReport() throws Exception{
		InputStream          in                  = null;
		JasperReport         reporte             = null;
		Object               exporter            = null;
		ServletOutputStream  servletOutputStream = null;
		List<Object[]>       parametros          = null;	
		try{						
			Log.info("Imprimiendo Reporte..."+nombreReporte+ "\n# Reportes:"+reportes.size());
			
			
			jasperPrintList=new ArrayList<JasperPrint>();
	
			int c=0;
			for(ReportFactory rf:reportes){
				Log.info("1-Reporte url:"+rf.getUrl()+" Nombre:"+this.nombreReporte);
				in = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(rf.getUrl());
				if(in==null)
					in=new FileInputStream(rf.getUrl());
				reporte = (JasperReport) JRLoader.loadObject(in);
				c++;
				Log.info("Reporte "+c+" Parametros         :"+(rf.getParametros()==null?"null":rf.getParametros().toString()));
				Log.info("Reporte "+c+" Tipo de data source:"+ rf.getTipoDataSource());
				Log.info("Reporte "+c+" Coleccion          :"+(rf.getCollecion()==null?"null":rf.getCollecion().size()));
				Log.info("Reporte "+c+" Bean          :"+(rf.getCollecion()==null?"null":rf.getCollecion().get(0).getClass().getSimpleName()));
				Log.info("Reporte "+c+" URL                :"+ rf.getUrl());
				
				jasperPrintList.add(JasperFillManager.fillReport(reporte, rf.getParametros(),rf.getTipoDataSource()==1?new JREmptyDataSource():new JRBeanCollectionDataSource(rf.getCollecion())));
			
			}			
			
			addExtencion();
			exporter = xClass.newInstance();	
						
			parametros=new ArrayList<Object[]>();
			parametros.add(new Object[]{JRExporterParameter.JASPER_PRINT_LIST,jasperPrintList});			
			servletOutputStream = obtenerServletOutputStream("Content-disposition", this.nombreReporte);
			if(nombreReporte.endsWith(".html"))
				parametros.add(new Object[]{JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE});
			parametros.add(new Object[]{JRExporterParameter.OUTPUT_STREAM,servletOutputStream});							
						
			if(nombreReporte.endsWith(".xls")) {
				parametros.add(new Object[]{JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE});
				parametros.add(new Object[]{JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE});
				parametros.add(new Object[]{JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE});
				
			    //we set the one page per sheet parameter here
				parametros.add(new Object[]{JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE});
				parametros.add(new Object[] {JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE});
				
			}
			addParameters(exporter,parametros);
			exporter.getClass().getMethod("exportReport", (Class[])null).invoke(exporter,(Object[])null);
			FacesContext.getCurrentInstance().getApplication().getStateManager().saveView(FacesContext.getCurrentInstance());
			FacesContext.getCurrentInstance().responseComplete();			
		}catch(Exception ex){
			Log.error("Error al mostrar el reporte",ex );
			throw ex;
		}finally{			
			cerrarServletOutputStream(servletOutputStream);
			clean();
			reportes=null;
			servletOutputStream = null;
			if(in!=null){
				in.close();
				in = null;
			}
			if(parametros!=null){
				parametros.clear();
				parametros=null;
			}
			reporte  = null;
			exporter = null;
		}		
	}
	
	private void addParameters(Object exporter, List<Object[]> parametros){
		try{
			for(Object[] param: parametros)
				exporter.getClass().getMethod("setParameter", new Class[]{JRExporterParameter.class,  Object.class}).invoke(exporter,param);
		}catch(Exception ex){
			Log.error("Error al agregar parametros ",ex);
		}
	}
	
	/**
	 * 
	 * @param rf
	 */
	protected void add(ReportFactory rf){
		if(reportes==null)
			reportes=new ArrayList<ReportFactory>();
		try {
			reportes.add((ReportFactory) rf.clone());
		} catch (Exception ex) {			
			Log.error("Error al agregar reporte ",ex);
		}
	}
	
	/**
	 * 
	 * @param content
	 * @param fileAttachment
	 * @return
	 * @throws Exception
	 */
	private ServletOutputStream obtenerServletOutputStream(String content, String fileAttachment) throws Exception{
	      ServletOutputStream servletOutputStream;
		  try{		
			 HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			 httpServletResponse.setContentType(contentType);
			 Log.info(fileAttachment);
			 httpServletResponse.addHeader(content, (isMostrarEnPantalla()?"inline":"attachment")+"; filename="+ fileAttachment);			 
			 servletOutputStream = httpServletResponse.getOutputStream();
			} catch (Exception e) {
				Log.error("Error al leer el archivo attachment ",e);
				throw new Exception("Error en la clase AbstractReport: "+ e.getMessage(), e.getCause());
			}
			return servletOutputStream;
	}
	
	private void addExtencion() throws Exception{
		try{
			if(formato.equalsIgnoreCase("pdf")){
				this.xClass=JRPdfExporter.class;				
				contentType="application/pdf";
			}
			if(formato.equalsIgnoreCase("html")){
				this.xClass=JRHtmlExporter.class;				
				contentType="text/html";
			}
			if(formato.equalsIgnoreCase("xls")){
				this.xClass=JRXlsExporter.class;	
				
				contentType="application/vnd.ms-excel";
			}
			
			if(formato.equalsIgnoreCase("docx")){
				this.xClass=JRDocxExporter.class;				
				contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			}
			if(formato.equalsIgnoreCase("rtf")){
				this.xClass=JRRtfExporter.class;				
				contentType="application/rtf";
			}
			nombreReporte=nombreReporte.contains(".")?nombreReporte:nombreReporte.concat("."+formato);
		}catch(Exception ex){
			Log.error("Error agregar la extension ",ex);
			throw ex;
		}

	}
	
	/**
	 * 
	 * @param servletOutputStream
	 * @throws Exception
	 */
	private void cerrarServletOutputStream(ServletOutputStream servletOutputStream) throws Exception {
		try {			
			if(servletOutputStream!=null){
				servletOutputStream.flush();
		    	servletOutputStream.close();
		    }
		} catch (Exception e) {
			Log.error("Error cerrar el servlertOutputStream ",e);
			 throw new Exception("Error en la clase AbstractReport: "+ e.getMessage(), e.getCause());
		}    
	}
	/**
	 * Dispose
	 */
	protected abstract void clean();
		
	/**
	 * 
	 * @return
	 */
	public String getNombreReporte() {
		return nombreReporte;
	}

	/**
	 * 
	 * @param nombreReporte
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}	
	
	/**
	 * 
	 * @return
	 */
	public boolean isMostrarEnPantalla() {
		return mostrarEnPantalla;
	}

	/**
	 * 
	 * @param mostrarEnPantalla
	 */
	public void setMostrarEnPantalla(boolean mostrarEnPantalla) {
		this.mostrarEnPantalla = mostrarEnPantalla;
	}

	/**
	 * Retorna el tipo de formato.
	 * 
	 * @return
	 */
	public String getFormato() {
		return formato;
	}

	/**
	 * Son los tipos de formatos doportados para mostrar.
	 * 
	 * pdf, html, docx y rtf
	 * @param formato
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}
}
