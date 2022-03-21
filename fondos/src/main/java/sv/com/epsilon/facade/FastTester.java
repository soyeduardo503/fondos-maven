package sv.com.epsilon.facade;

import java.util.List;

public class FastTester {

	public static void main(String[] args) {
		List<?> all = new FinanciamientoFacade().getAll();
		System.out.println(all);
	}
}
