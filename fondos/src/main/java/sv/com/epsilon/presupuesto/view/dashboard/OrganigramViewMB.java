/**
 * 
 */
package sv.com.epsilon.presupuesto.view.dashboard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.OrganigramNode;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.presupuesto.ctrlr.CreteModelOrganigramCtrlr;
import sv.com.epsilon.util.ExecuteForm;

/**
 * @author 50364
 *
 */
@ManagedBean
@RequestScoped
public class OrganigramViewMB {

	/**
	 * 
	 */
	
	private Presupuesto presupuesto;
	private Categoria c;
	private OrganigramNode rootNode;
	private boolean zoom = false;
	 
    private String style = "width: 800px";
	private int leafNodeConnectorHeight = 0;
	private boolean autoScrollToSelection = false;
	private String wgtOrganigrama="DefaultOrg";
	private String formOrganigrama="idDefaultOrg";
	
	public OrganigramViewMB() {
		
	}
	
	public void createModelFromPresupuesto(Presupuesto presupuesto) {
		try {
			rootNode=new CreteModelOrganigramCtrlr().fromPresupuesto(presupuesto);
			new ExecuteForm().ExecuteUpdate(formOrganigrama, "PF('"+wgtOrganigrama+"').show();");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void createModelFromCategoria() {
		try {
			new CategoriaFacade().findListSubCategoria(c);
			rootNode=new CreteModelOrganigramCtrlr().fromCategoria(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ExecuteForm().ExecuteUpdate(formOrganigrama, "PF('"+wgtOrganigrama+"').show();");
	}
	
	public void createModelFromCategoria(Categoria c) {
		CategoriaFacade facade = new CategoriaFacade();
		try {
			facade.findListSubCategoriaWithoutClose(c);
			rootNode=new CreteModelOrganigramCtrlr().fromCategoria(c);
			facade.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ExecuteForm().ExecuteUpdate(formOrganigrama, "PF('"+wgtOrganigrama+"').show();");
	}

	public OrganigramNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(OrganigramNode rootNode) {
		this.rootNode = rootNode;
	}

	public boolean isZoom() {
		return zoom;
	}

	public void setZoom(boolean zoom) {
		this.zoom = zoom;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int getLeafNodeConnectorHeight() {
		return leafNodeConnectorHeight;
	}

	public void setLeafNodeConnectorHeight(int leafNodeConnectorHeight) {
		this.leafNodeConnectorHeight = leafNodeConnectorHeight;
	}

	public boolean isAutoScrollToSelection() {
		return autoScrollToSelection;
	}

	public void setAutoScrollToSelection(boolean autoScrollToSelection) {
		this.autoScrollToSelection = autoScrollToSelection;
	}

	public String getWgtOrganigrama() {
		return wgtOrganigrama;
	}

	public void setWgtOrganigrama(String wgtOrganigrama) {
		this.wgtOrganigrama = wgtOrganigrama;
	}

	public String getFormOrganigrama() {
		return formOrganigrama;
	}

	public void setFormOrganigrama(String formOrganigrama) {
		this.formOrganigrama = formOrganigrama;
	}

	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Categoria getC() {
		return c;
	}

	public void setC(Categoria c) {
		this.c = c;
	}

	
	

	
	
}
