package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.staticvalues.Customers;

public class ProveedorCtrlr {

	public ProveedorCtrlr() {
		// TODO Auto-generated constructor stub
	}
	
	private HashMap<String,String> list=new HashMap<>();


	public void checkProveedor() {
		Stream<String> pvd=Stream.of(proveedores.split(","));
		pvd.forEach(s->{
			try {
				if(s.contains("COMISION POR PLANILLA")) {
					s="BANCO AGRICOLA";
				}
				validateExist( s.replace(",", "").trim());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.err.println(s);
				list.put(s,s);
			}
		});
		System.out.println(list.keySet());
	}	
	
	public static void validateExist(String prName) throws Exception {
		Proveedor pro=new Proveedor(prName, prName);
		Proveedor resp = new ProveedorFacade().search(pro);
		if(resp.getIdProveedor()==null) {
			prName=prName.replace(",", "");
			 pro=new Proveedor(prName, prName);
			resp = new ProveedorFacade().search(pro);
		}
		if(resp.getIdProveedor()==null) {
			System.out.println(prName);
		}
	}
	public static void main(String[] args) {
	
		new ProveedorCtrlr().checkProveedor();
	}
	String proveedores=Customers.VALUES.replace(",","");
}
