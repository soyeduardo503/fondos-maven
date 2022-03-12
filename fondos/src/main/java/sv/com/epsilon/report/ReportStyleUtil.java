/**
 * 
 */
package sv.com.epsilon.report;

import java.io.Serializable;

/**
 * @author usuario07
 * @date 12/02/2014
 * @time 15:35:52
 * @PCBM sv.com.bbmass.pcbm.util.ReportStyleUtil
 */
public class ReportStyleUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ReportStyleUtil() {
		
	}
	
	public String bold(String txt){
		return "<style isBold='true' pdfFontName='Helvetica-Bold'>"+txt+"</style>";
	}
	
	public String line(String txt){
		return "<style isUnderline='true' pdfFontName='Helvetica-Bold'>"+txt+"</style>";
	}

	public String removeBold(String garantias) {
		return garantias.replaceAll("<style isBold='true' pdfFontName='Helvetica-Bold'>","").replaceAll("</style>","");
	}
	
	public String removeLine(String garantias) {
		return garantias.replaceAll("<style isUnderline='true' pdfFontName='Helvetica-Bold'>","").replaceAll("</style>","");
	}
	

}
