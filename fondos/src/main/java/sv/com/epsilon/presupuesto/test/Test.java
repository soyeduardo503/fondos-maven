/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.presupuesto.test;

import sv.com.epsilon.presupuesto.ctrlr.CodigoCtrlr;




/**
 *
 * @author Zeta
 */
public class Test {
    
	public static void main(String[] args) throws Exception {
	
		System.out.println(new CodigoCtrlr().getCodigoPadre("FPT01"));
	}
//		{	String nombrePresupuesto="FUNDACION PABLO TESAK";
//		String cod="";
//			if(nombrePresupuesto.split(" ").length>1) {
//				if(nombrePresupuesto.split(" ").length==2) {
//					String[] nms= nombrePresupuesto.split(" ");
//					cod= nms[0].substring(0, 2)+nms[1].substring(0, 1);
//				}
//				if(nombrePresupuesto.split(" ").length==3) {
//					String[] nms= nombrePresupuesto.split(" ");
//					cod= nms[0].substring(0, 1)+nms[1].substring(0, 1)+nms[2].substring(0, 1);
//				}
//			}else
//				cod= nombrePresupuesto.substring(0,3);
//			
//		
//		System.out.print(cod);
//	}
	

	
	
//		new Test().crear();
//		Presupuesto p=new Presupuesto(1);
//		
//		System.out.println(new CategoriaFacade().CountByPresupuesto(p));
//		//System.out.println(new CategoriaFacade().findAllPresupuestoActive());
//		//ViewNode v=new ViewNode("Test", 1, 31000,11000, 31000-11000, "123");
//		//System.out.println(v.getColorDisponible());
////		Presupuesto presupuesto=new PresupuestoFacade().find(1);
////		BigDecimal b=new BigDecimal(presupuesto.getTotal()-presupuesto.getActual()).divide(new BigDecimal(presupuesto.getTotal())).multiply(new BigDecimal(100));
////		System.out.println(b.intValue());
////		//System.out.println(new TablagastoFacade().findAllActive().size());
//		
//
//	}
//	
//	public void crear(){
//		Presupuesto p=new PresupuestoFacade().findById(16);
//		Categoria cat=new Categoria();
//		new GeneradorCodigo().makeCode(cat);
//		cat.setIdPresupuesto(p);
//		cat.setMonto(0.0);
//		cat.setActual(0.0);
//		cat.setAct("A");
//		cat.setNombre("Prueba");
//		cat.setDescripcion(cat.getNombre());
//		List<Categoria> temp=cat.getCategoriaList();
//		cat.setCategoriaList(null);
//		cat.setIdCategoriaPadre(null);
//		CategoriaFacade cf = new CategoriaFacade();
//		cf.persis(cat);
//		cf.close();
//		
//	}
	
}
