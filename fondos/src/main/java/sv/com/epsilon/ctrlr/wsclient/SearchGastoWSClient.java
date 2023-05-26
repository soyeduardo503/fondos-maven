package sv.com.epsilon.ctrlr.wsclient;

import java.util.List;

import sv.com.epsilon.presupuesto.pojo.GastoExt;
import sv.com.epsilon.presupuesto.pojo.SearchGasto;

public class SearchGastoWSClient extends WSClient<SearchGasto>{

	public SearchGastoWSClient() {
		super(SearchGasto.class);
	}
	
	public List<GastoExt> searchParam(SearchGasto search) throws Exception{
		return search(search).getGastos();
	}

}
