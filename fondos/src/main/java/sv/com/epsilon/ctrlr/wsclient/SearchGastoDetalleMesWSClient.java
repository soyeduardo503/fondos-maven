package sv.com.epsilon.ctrlr.wsclient;

import java.util.List;

import sv.com.epsilon.presupuesto.pojo.DetalleGastoMes;
import sv.com.epsilon.presupuesto.pojo.SearchParameterGastoMensual;

public class SearchGastoDetalleMesWSClient extends WSClient<SearchParameterGastoMensual>{

	public SearchGastoDetalleMesWSClient() {
		super(SearchParameterGastoMensual.class);
	}
	
	public List<DetalleGastoMes> searchParam(SearchParameterGastoMensual search) throws Exception{
		return search(search).getList();
	}

}
