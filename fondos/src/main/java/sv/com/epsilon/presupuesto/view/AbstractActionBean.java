/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import sv.com.epsilon.util.ExecuteForm;

/**
 * @author martinezc
 *
 */
public abstract class AbstractActionBean {
	
	private String form="formID";
	private String dlg="dlgId";
	private String formDlg="formDlg";
	
	public AbstractActionBean(Class typeClass) {
		
	}
	
	public void action() {
		
	}
	
	public void show() {
		new ExecuteForm().ExecuteUpdate(formDlg,"PF('"+dlg+"').show();");
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getDlg() {
		return dlg;
	}

	public void setDlg(String dlg) {
		this.dlg = dlg;
	}

	public String getFormDlg() {
		return formDlg;
	}

	public void setFormDlg(String formDlg) {
		this.formDlg = formDlg;
	}
	
	

}
