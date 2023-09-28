package sv.com.epsilon.presupuesto.pojo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleGastoMes {

	private Categoria categoria;
	
	private Double monto;
	private String year;
	private Double disponible;
	private Presupuesto presupuesto;
	private String txtCategoria;
	private List<MontoMes> montos;
	
	
	
	public Double acumulado() {
		BigDecimal bd=new BigDecimal(0);
		if(montos!=null&& montos.size()>0) {
			bd= montos.stream().map(x->new BigDecimal( x.getAmount())).reduce(BigDecimal.ZERO,BigDecimal::add);
		}
		return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public Double byMes(Integer i) {
		Optional<MontoMes> mm= montos==null?Optional.empty(): montos.stream().filter(l->l.getMonth()==i).findFirst();
		return mm.isPresent()? new BigDecimal(mm.get().getAmount()).setScale(2, RoundingMode.HALF_UP).doubleValue():0.0;
	}
	
	
	



	
	
}
