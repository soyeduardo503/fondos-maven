/**
 * 
 */
package sv.com.epsilon.report.pojo;

import java.util.List;

import lombok.Data;
import sv.com.epsilon.entities.Gasto;

/**
 * @author martinezc
 *
 */
@Data
public class GastoPeriodo {

	private List<Gasto> list;
	private String institutionName;
}
