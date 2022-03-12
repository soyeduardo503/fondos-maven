/**
 * 
 */
package sv.com.epsilon.report.documentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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
	public RptShow() {
		// TODO Auto-generated constructor stub
	}
	public void callReport(Documento documento,Object bean) {
		
		 
		Log.info(cheque);
		Log.info("C:\\\\files\\\\fondos\\\\reportes\\\\cheque.jasper");
		
		
		setUrl										(documento.getPath());					
		Log.info									("cargando el documento "+getUrl());
		setNombreReporte							(documento.toString());	
		
		
		setFormato(documento.getExt());
		
		
		this.setCollecion			(new ArrayList<Object>(Arrays.asList(bean)));
		this.addReport();
		//setUrl										(documento2.getUrl());	
		
		
		
		//this.setCollecion			(new ArrayList<Object>(Arrays.asList(beanDocument)));
		//this.addReport();
		
		mostrar();
	}

private void mostrar() {
	try {
		//this.setFormato(documento.getExtension());
		showReport();
		

	} catch (Exception e) {

		Log.Error("Error al cargar los documentos ",e);		
		new MessageGrowlContext().sendError("Error al generar el documento","problemas en el origen de datos del documento",e);
	}
}


}
