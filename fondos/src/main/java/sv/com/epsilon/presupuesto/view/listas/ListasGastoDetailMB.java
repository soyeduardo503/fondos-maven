/**
 * 
 */
package sv.com.epsilon.presupuesto.view.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.epsilon.entities.Cajachica;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.util.ExecuteForm;
/**
 * @author martinezc
 *
 */
@ViewScoped
@ManagedBean
public class ListasGastoDetailMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Gasto> list;
	


	private Integer idStarter=0;
	private Integer idFinisher=0;
	private Integer type=0;
	private String idForm="idFormDetailGs";
	private String wgtDlg="wgtDlgGs";
	private String idTable="idTableGs";
	private GastoCtrlr ctrlr=new GastoCtrlr();
	private Cajachica cc;

	
	
	public ListasGastoDetailMB() {
		
	}

	public List<Gasto> getList() {
		return list;
	}

	public void setList(List<Gasto> list) {
		this.list = list;
	}

	public Integer getIdStarter() {
		return idStarter;
	}

	public void setIdStarter(Integer idStarter) {
		this.idStarter = idStarter;
	}

	public Integer getIdFinisher() {
		return idFinisher;
	}

	public void setIdFinisher(Integer idFinisher) {
		this.idFinisher = idFinisher;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIdForm() {
		return idForm;
	}

	public void setIdForm(String idForm) {
		this.idForm = idForm;
	}

	public String getWgtDlg() {
		return wgtDlg;
	}

	public void setWgtDlg(String wgtDlg) {
		this.wgtDlg = wgtDlg;
	}

	public String getIdTable() {
		return idTable;
	}

	public void setIdTable(String idTable) {
		this.idTable = idTable;
	}

	public GastoCtrlr getCtrlr() {
		return ctrlr;
	}

	public void setCtrlr(GastoCtrlr ctrlr) {
		this.ctrlr = ctrlr;
	}

	public Cajachica getCc() {
		return cc;
	}

	public void setCc(Cajachica cc) {
		this.cc = cc;
	}

	public void loadGastos() {
		
		list=ctrlr.find(idStarter,  idFinisher,type);
	}

	public void loadGastos(Integer idStarter,Integer idFinisher, Integer type) {
		
		list=ctrlr.find(idStarter,  idFinisher,type);
		System.out.println("list load");
		new ExecuteForm().ExecuteUpdate(Arrays.asList(new String[] {this.idForm}),createListDialogOpen());
	}

	
	public void loadGastosCC() {
		loadGastos(cc.getVinicial(),cc.getVfinal(), 2);
		
	}
	
	private List<String> createListDialogOpen() {
		List<String> pfAction=new ArrayList<String>();
		pfAction.add("PF('"+wgtDlg+"').show();"); 
		 
		return pfAction;
	}
	
	
}
