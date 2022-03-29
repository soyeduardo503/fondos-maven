package sv.com.epsilon.ctrlr.wsclient;

import sv.com.epsilon.presupuesto.pojo.SearchGasto;

public class SearchGastoWSClient extends WSClient<SearchGasto>{

	public SearchGastoWSClient() {
		super(SearchGasto.class);
	}

}
