package sv.com.epsilon.presupuesto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndicadorGastoReal {

	private String mes;
	private Double real;
	private Double proyection;
	

}
