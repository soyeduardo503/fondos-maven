package sv.com.epsilon.report.pojo;

import lombok.Data;

@Data
public class Documento {

	private String path;
	private String ext;
	private String nombre;
	private Object object;
	public Documento() {
		
	}
	

	@Override
	public String toString() {
		return nombre;
	}
	
}
