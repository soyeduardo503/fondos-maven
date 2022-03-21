package sv.com.epsilon.presupuesto.pojo;

public class MontoUpdateRequest {

	private  String codigo;
	private Double monto;
	public MontoUpdateRequest(String codigo, Double monto) {
		super();
		this.codigo = codigo;
		this.monto = monto;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	
}
