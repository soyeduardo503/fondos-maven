package sv.com.epsilon.presupuesto.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import lombok.Data;
import sv.com.epsilon.entities.Cajachica;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.facade.CajachicaFacade;
import sv.com.epsilon.presupuesto.ctrlr.CajachicaCtrlr;

@ViewScoped
@ManagedBean
@Data
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
	private String idDetail="idWgtDetail";
	private String idWgtDetail="wgtVarDetailCC";
	private String idFormDetail="idWgtDetailform";
	private String idTableDetail="idTableDetail";
	private String idPage="idPageCC";
	private String wgt="dlgCajaAdd";
	private Integer yearSelected=Calendar.getInstance().get(Calendar.YEAR);
	private List<Integer> listYear=initList();
	private CajachicaFacade cajaFacade=new CajachicaFacade();
	private List<Gasto> listGasto=new ArrayList<Gasto>();
	
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
	
	public void loadDetalle(Cajachica cc) {
		this.selected=cc;
		listGasto=new CajachicaCtrlr().loadGastoByCC(selected);
	}
	
	public void loadYear() {
		list=cajaFacade.findByYear(yearSelected);
	}
	public void load() {
		list=cajaFacade.findAll();
	}
	
	public void closeone() {
		selected.setAct("C");
		
	}
	
	
}
