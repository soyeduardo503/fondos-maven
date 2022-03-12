package sv.com.epsilon.presupuesto.ctrlr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.facade.ParametroFacade;

public class CargaValidacionCtrlr {

	private SortedMap<String, Categoria> principal=new TreeMap<String,Categoria>();
	
	private final static Integer LENGHT_PRINCIPAL=7;
	private final static Integer SIZE_SUBCOD=new BigInteger(new ParametroFacade().findByName("VAR_SIZE_SCOD").getValor()).intValue();
	
	private Double montoAcumuladoPrincipal=0.0;
	private List<Categoria> abandonados=new ArrayList<Categoria>();
	
	public CargaValidacionCtrlr() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Double getMontoAcumuladoPrincipal() {
		return montoAcumuladoPrincipal;
	}



	public void setMontoAcumuladoPrincipal(Double montoAcumuladoPrincipal) {
		this.montoAcumuladoPrincipal = montoAcumuladoPrincipal;
	}


	public List<Categoria> obtenerCategoriasPrincipales(){
		List<Categoria> p=new ArrayList<>();
		principal.forEach((name,value)->p.add(value));
		return p;
	}

	public void validarCargaCategorias(List<Categoria> list) throws Exception {
		Collections.sort(list);
		for(Categoria cat:list) {
			if(cat.getCodigo().length()==LENGHT_PRINCIPAL) {
				if(principal.get(cat.getCodigo())!=null) {
					throw new Exception("Codigo principal duplicado "+cat.getCodigo());
				}else {
					principal.put(cat.getCodigo(), cat);
				}
			}else {
				/*se buscara el padre*/
				String codigoCatPadre=cat.getCodigo().substring(0, LENGHT_PRINCIPAL);
				Categoria catPadre = principal.get(codigoCatPadre);
				if(catPadre==null)
					abandonados.add(cat);
				else
					buscarPadre(catPadre, cat);
			}
			
			/*
			 * 
			 * **/
			
			
		}
		if(abandonados.size()>0) {
			throw new Exception("Se encontraron item sin padres");
		}
		
		Iterator iterator =principal.keySet().iterator();
		
		while(iterator.hasNext()) {
			String key   = (String) iterator.next();
			this.validarMontos(principal.get(key));
			this.montoAcumuladoPrincipal+=principal.get(key).getMonto();
		}
//			principal.forEach((name, value) ->{
//				try {
//					validarMontos(value);
//				} catch (Exception e) {
//					throw new Exception(e);
//				}
//			});
//		
			
		
	
		
		System.out.println(principal.size());
		
	}
	
	
	
	private void buscarPadre(Categoria principal,Categoria cat) throws Exception {
		String codigoPadre=principal.getCodigo();
		String codigoHijo=cat.getCodigo();
		if(codigoHijo.length()-codigoPadre.length()>SIZE_SUBCOD) {
			/**
			 * Si el codigo es mayor que el parametrizado para subcodigos es porque no es el padre  y se envia a buscar, pero si la categoria padre no tiene hijos 
			 * el cat es huerfano y hay que reventar
			 * */
			if(principal.getCategoriaList().size()==0) {
				throw new Exception("Existe categorias huerfanas codigo categoria: "+cat.getCodigo());
			}
			else {
				for(Categoria cnuevoPadre: principal.getCategoriaList()) {
					if(cat.getCodigo().contains(cnuevoPadre.getCodigo())) {
							buscarPadre(cnuevoPadre, cat);
							return ;
					}
				}
				/*
				 * 
				 * No se encontro coincidencia 
				 * 
				 * Agregar los huerfanos
				 * 
				 * **/
				abandonados.add(cat);
				return ;
			}
			
			
			
		}else { /**
		Es el padre
		*/
			if(codigoPadre.equals(codigoHijo.substring(0, codigoHijo.length()-SIZE_SUBCOD))) {
				principal.getCategoriaList().add(cat);
				return ;
			}else {
				abandonados.add(cat);
			}
			
		}
		
	}
	
	
	private void validarMontos(Categoria cat) throws Exception {
		
		Double montoTotal=cat.getMonto();
		Double montoAcumulado=0.0;
		
		for(Categoria c:cat.getCategoriaList()) {
			montoAcumulado=+c.getMonto();
			if(c.getCategoriaList().size()>0) {
					validarMontos(c);
				}
		}
		if(cat.getCategoriaList().size()>0) {
			if(montoAcumulado>montoTotal) {
				throw new Exception("Monto distribuido supera al monto de la categoria :"+cat.getNombre());
			}
		}
		
	}
	
	
	

}
