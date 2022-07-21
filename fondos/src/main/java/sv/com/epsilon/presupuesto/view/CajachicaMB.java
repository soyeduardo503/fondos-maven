package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import sv.com.epsilon.entities.Cajachica;
import sv.com.epsilon.facade.CajachicaFacade;

@ViewScoped
@ManagedBean
public class CajachicaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cajachica selected;
	private List<Cajachica> list;
	private String formList="cajaList";
	private String formCreate="cajaCreate";
	private String headerPage="Pantalla de registro de Caja chica($)";
	private String headerTable="Lista de Cajas";
	private String headerConfirma="crear nueva caja?";
	private String idTable="listOfCaja";
	private String idAdd="addCajaDialog";
	private String wgt="dlgCajaAdd";
	private Integer yearSelected=Calendar.getInstance().get(Calendar.YEAR);
	private List<Integer> listYear=initList();
	private CajachicaFacade cajaFacade=new CajachicaFacade();
	
	
	public CajachicaMB() {
		super();
	}
	private List<Integer> initList() {
		List<Integer> list= new ArrayList<Integer>();
		for(int u=2022;u<=Calendar.getInstance().get(Calendar.YEAR);u++) {
			list.add(yearSelected);
		}
		return list;
	}
	
	public void loadYear() {
		list=cajaFacade.findByYear(yearSelected);
	}
	public void load() {
		list=cajaFacade.findAll();
	}
	public Cajachica getSelected() {
		return selected;
	}
	public void setSelected(Cajachica selected) {
		this.selected = selected;
	}
	public List<Cajachica> getList() {
		return list;
	}
	public void setList(List<Cajachica> list) {
		this.list = list;
	}
	public String getFormList() {
		return formList;
	}
	public void setFormList(String formList) {
		this.formList = formList;
	}
	public String getFormCreate() {
		return formCreate;
	}
	public void setFormCreate(String formCreate) {
		this.formCreate = formCreate;
	}
	
	public Integer getYearSelected() {
		return yearSelected;
	}
	public void setYearSelected(Integer yearSelected) {
		this.yearSelected = yearSelected;
	}
	public List<Integer> getListYear() {
		return listYear;
	}
	public void setListYear(List<Integer> listYear) {
		this.listYear = listYear;
	}
	public String getHeaderPage() {
		return headerPage;
	}
	public void setHeaderPage(String headerPage) {
		this.headerPage = headerPage;
	}
	public String getHeaderTable() {
		return headerTable;
	}
	public void setHeaderTable(String headerTable) {
		this.headerTable = headerTable;
	}
	public String getHeaderConfirma() {
		return headerConfirma;
	}
	public void setHeaderConfirma(String headerConfirma) {
		this.headerConfirma = headerConfirma;
	}
	
	
	public String getIdTable() {
		return idTable;
	}
	public void setIdTable(String idTable) {
		this.idTable = idTable;
	}
	public String getIdAdd() {
		return idAdd;
	}
	public void setIdAdd(String idAdd) {
		this.idAdd = idAdd;
	}
	public String getWgt() {
		return wgt;
	}
	public void setWgt(String wgt) {
		this.wgt = wgt;
	}
	public void closeone() {
		selected.setAct("C");
		
	}
	
	
}
