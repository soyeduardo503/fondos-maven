package sv.com.epsilon.presupuesto.view.report;

import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.presupuesto.pojo.Report;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ViewScoped
@ManagedBean
public class GeneralReportMB extends ReportViewMB{

	private Presupuesto presupuesto;
	
	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB session;
	
	private Report dataReport;
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()){
			presupuesto= session.getPresupuestoSelected();
			
			WSClient<Report> facade=new WSClient<Report>(Report.class);
			facade.setNoNameClass(true);
			Optional<Report> reportOpt= facade.get("/report/byPresupuesto/"+presupuesto.getIdPresupuesto());
			if(reportOpt.isPresent()) {
				setDataReport(reportOpt.get());
				getDataReport().getPresupuesto().setTitle(presupuesto.getNombrePresupuesto() +"-"+presupuesto.getYear());
			}
		}
	}

}
