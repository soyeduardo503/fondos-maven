package sv.com.epsilon.presupuesto.ctrlr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Projection;
import sv.com.epsilon.util.Mes;

public class ProjectionCtrlr {

	public ProjectionCtrlr() {
		
	}
	
	public List<Projection> makeList(Presupuesto p,Integer idEmpresa){
		List<Projection> list=new ArrayList<>();
		Mes.all().stream().forEach(m->list.add(new Projection(null,m.getIdMes(), 0.0, "A", p.getIdPresupuesto(), idEmpresa,0)));
		return list;
	}

	public void validate(List<Projection> list) throws Exception {
		Optional<Projection> v= list.stream().filter(p->p.getAmount()<0||p.getAmount()==0).findAny();
		if(v.isPresent())
			throw new Exception("montos no pueden ser ceros");
		
	}

}
