/**
 * 
 */
package sv.com.epsilon.presupuesto.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author martinezc
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageBucket {

	private String code;
	private byte[] image;
}
