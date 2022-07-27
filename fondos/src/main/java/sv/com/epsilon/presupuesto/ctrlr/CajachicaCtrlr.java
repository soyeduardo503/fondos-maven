package sv.com.epsilon.presupuesto.ctrlr;

import java.util.List;

import sv.com.epsilon.entities.Cajachica;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.facade.CajachicaFacade;
import sv.com.epsilon.facade.GastoFacade;

public class CajachicaCtrlr {

	private GastoFacade gastoFacade=new GastoFacade();
	private CajachicaFacade facade=new CajachicaFacade();
	private final static Integer tipoDesembolso=2;
	public List<Gasto> loadGastoByCC(Cajachica cc){
		return gastoFacade.findByRange(cc.getVinicial(),cc.getVfinal(),tipoDesembolso);
	}
}
