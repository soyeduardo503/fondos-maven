/**
 * 
 */
package sv.com.epsilon.facade;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.ctrlr.wsclient.WSClient;
import sv.com.epsilon.entities.Financiamiento;
import sv.com.epsilon.presupuesto.pojo.SearchIngreso;

/**
 * @author martinezc
 *
 */
@Slf4j
public class SearchIngresosFacade extends WSClient<SearchIngreso>{

	public SearchIngresosFacade() {
		super(SearchIngreso.class);
		// TODO Auto-generated constructor stub
	}
	public List<Financiamiento> invocarBusqueda(SearchIngreso search) {
		
		try {
			return this.search(search).getList();
		} catch (Exception e) {
			log.error("",e);
		}
		return new ArrayList<>();
	}
}
