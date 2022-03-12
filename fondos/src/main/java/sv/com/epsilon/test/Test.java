package sv.com.epsilon.test;

import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.println(new CategoriaFacade().findById(1));
		System.out.println(new PresupuestoFacade().findById(3));
	}

}
