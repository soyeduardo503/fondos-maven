/**
 * 
 */
package sv.com.epsilon.report.documentos;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import sv.com.epsilon.report.ReportFactory;
import sv.com.epsilon.report.pojo.Cheque;
import sv.com.epsilon.report.pojo.Documento;
import sv.com.epsilon.util.Log;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author 50364
 *
 */
public class RptShow extends ReportFactory  implements Serializable{

	/**
	 * 
	 */
	private Cheque cheque;
	private byte[] reportValue;
	
	public RptShow() {
		// TODO Auto-generated constructor stub
	}
	public void callReport(Documento documento,Object bean) throws Exception {
		
		 
		Log.info(cheque);
		Log.info("C:\\\\files\\\\fondos\\\\reportes\\\\cheque.jasper");
		
		
		setUrl										(documento.getPath());					
		Log.info									("cargando el documento "+getUrl());
		setNombreReporte							(documento.toString());	
		
		setMostrarEnPantalla(false);
		setFormato(documento.getExt());
		
		
		this.setCollecion			(new ArrayList<Object>(Arrays.asList(bean)));
		this.addReport();
		//setUrl										(documento2.getUrl());	
		
		
		
		//this.setCollecion			(new ArrayList<Object>(Arrays.asList(beanDocument)));
		//this.addReport();
		
		mostrar();
	}
	
//	 public StreamedContent getPdf(Documento documento,Object bean) {
//		 String path;
//		try {
//			path = pathPDF+callReport(documento, bean);
//			File pdfProcedured = new File(path);
//			  
//			return  new DefaultStreamedContent(new FileInputStream( pdfProcedured), "application/pdf");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	 return null;
//		  
//	 }
	 byte[] pdf=null;
	 public StreamedContent defaultPDF() {
		 Path pdfPath=null;
		 if(new File("C:\\files\\pdf\\empty.pdf").exists())
			 pdfPath = Paths.get("C:\\files\\pdf\\empty.pdf");
		 else{
			 pdfPath = Paths.get("/opt/epsilon/pdf/empty.pdf");
		 }
		 
		try {
			pdf = Files.readAllBytes(pdfPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return DefaultStreamedContent.builder()
	                .contentType("application/pdf")
	                .stream(() ->  new ByteArrayInputStream(pdf))
	                .build();
	 }

	   

	  

private void mostrar() {
	try {
		//this.setFormato(documento.getExtension());
		setMostrarEnPantalla(false);
		showReport();		

	} catch (Exception e) {

		Log.Error("Error al cargar los documentos ",e);		
		new MessageGrowlContext().sendError("Error al generar el documento","problemas en el origen de datos del documento",e);
	}
}
public byte[] getReportValue() {
	return reportValue;
}
public void setReportValue(byte[] reportValue) {
	this.reportValue = reportValue;
}




}
