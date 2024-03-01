package sv.com.epsilon.presupuesto.ctrlr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;

@Slf4j
public class AsignacionMontosCtrlr {

	
	public static void calcularMontos(List<Categoria> list,Categoria catMod,Presupuesto p) {
		Categoria padre= list.stream().filter(c->c.getCodigo().equalsIgnoreCase(catMod.getCodigo().substring(0, 7))).findFirst().get();
//		List<Categoria> catHijos =list.stream().filter(c->c.getCodigo().length()==9&& c.getCodigo().startsWith(padre.getCodigo())).collect(Collectors.toList());;
		DoubleStream totalHijos = list.stream().filter(c->c.getCodigo().length()==9&& c.getCodigo().startsWith(padre.getCodigo())).mapToDouble(s->s.getMonto());
		padre.setMonto(totalHijos.sum());
		Stream<Categoria> l = list.stream().filter(s->s.getCodigo().length()==7);
		
		DoubleStream montoTotal = l.mapToDouble(s->s.getMonto());
		p.setTotal(new BigDecimal(  montoTotal.sum()).setScale(2, RoundingMode.HALF_UP).doubleValue());
		
		
		
	}
	
	
	public static Integer calcularPorcentaje(Categoria c, Presupuesto p ) {
		return (int) ((c.getMonto()/p.getTotal())*100);
	}
	
	
	public void save(Presupuesto presupuesto, List<Categoria> list) throws Exception {
		
			list.forEach(l->			{
				try {
					new CategoriaFacade().save(l);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		
		try {
			new PresupuestoFacade().saveAmount(presupuesto);
		}catch (Exception e) {
			log.error("Error al guardar el monto del presupuesto", e);
			throw new Exception("Error al guardar informacion del presupuesto");
		}
	}

}
