/**
 * 
 */
package sv.com.epsilon.presupuesto.view.report;

import java.io.File;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.model.StreamedContent;

import lombok.Data;
import sv.com.epsilon.report.documentos.RptShow;
import sv.com.epsilon.report.pojo.Documento;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.Log;

/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
@Data
public class ReportViewMB {

	private StreamedContent reportValue =init();
	private Object object;
	private String path="C:\\files\\fondos\\reportes\\";
	private String titlePdf;
	private String report;
	private String ext;
	
	
	private StreamedContent init()
	{
		  File testPdfFile = new File("C:\\files\\pdf\\empty.pdf");
//		  try {
//			  
//			//return  new DefaultStreamedContent(new FileInputStream(testPdfFile), "application/pdf");
//			
//		} catch (FileNotFoundException e) {
//			
//			e.printStackTrace();
//		}
		  return null;
		
	}
	private Documento create() {
		Documento doc=new Documento();
		doc.setExt(ext);
		doc.setNombre(titlePdf);
		doc.setObject(object);
		
		if(new File(path+report).exists())
			doc.setPath(path+report);
		else
			doc.setPath("/opt/epsilon/reporte/cheque.jasper");
		
		
		Log.info("The report is made");
		return doc;
	}
	
	public void print(){
		Documento doc = create();
		
		
				
		
		try {
			new RptShow().callReport(doc, doc.getObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//mostrar();
	}
	
	
	public StreamedContent getReportValue() {
		return reportValue;
	}
	public void setReportValue(StreamedContent reportValue) {
		this.reportValue = reportValue;
	}
	
	public void view() {
		try {
			//
			new ExecuteForm().ExecuteUpdate("previewDocument", "PF('previewDlg').show();");
		} catch (Exception e) {
			Log.info("Don't show the report, the report fail when was making");
			e.printStackTrace();
		}
	}
	
	
	
}
