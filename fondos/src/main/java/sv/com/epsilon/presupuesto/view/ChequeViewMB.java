/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.report.documentos.RptShow;
import sv.com.epsilon.report.pojo.Cheque;
import sv.com.epsilon.report.pojo.Documento;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.Log;
import sv.com.epsilon.util.Meses;
import sv.com.epsilon.util.NumberToLetter;

/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
public class ChequeViewMB {

	private StreamedContent reportValue =init();
	
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
	private Documento create(Gasto gasto) {
		Documento doc=new Documento();
		doc.setExt("PDF");
		doc.setNombre("Cheque FPT");
		if(new File("C:\\\\files\\\\fondos\\\\reportes\\\\cheque.jasper").exists())
			doc.setPath("C:\\\\files\\\\fondos\\\\reportes\\\\cheque.jasper");
		else
			doc.setPath("/opt/epsilon/reporte/cheque.jasper");
		
		
		Log.info("The report is made");
		return doc;
	}
	public void print(Gasto gasto){
		Documento doc = create(gasto);
		Cheque cheque=new Cheque();
		BigDecimal total=new BigDecimal(gasto.getTotal()).setScale(2,RoundingMode.HALF_UP);
		String cantidadLetras=new NumberToLetter().Convertir(total.toString(),true, true, true);
		
		cheque.setCantidad(gasto.getTotal());

		cheque.setCantidadLetras(cantidadLetras.toUpperCase());
		cheque.setConcepto(gasto.getNombre());
		cheque.setProveedor(gasto.getIdProveedor().getNombreLegal());
		cheque.setFecha(Meses.obtenerTexto(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH)).toUpperCase());
		
		
		try {
			new RptShow().callReport(doc, cheque);
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
