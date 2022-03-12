package sv.com.epsilon.util;

import java.util.ArrayList;
import java.util.List;

public class Constantes {
	
	public String C_ORANGE="#F39611;";
	public String C_BLUE="#326DDF;";
	public String C_PINK="#E02364;";
	public String C_GREEN="#7DC931;";
	public String C_BLUESKY="#2D8EE3";

	public static List<String> semanal() {
		List<String> semanal = new ArrayList();
		String mespref = "";
		if (Util.mesActual() <= 9) {
			mespref = "0";
		}
		semanal.add("01" + mespref + Util.mesActual() + Util.anioActual());
		semanal.add("02" + mespref + Util.mesActual() + Util.anioActual());
		semanal.add("03" + mespref + Util.mesActual() + Util.anioActual());
		semanal.add("04" + mespref + Util.mesActual() + Util.anioActual());
		return semanal;
	}

	public static List<String> quincenal() {
		List<String> quincenal = new ArrayList();
		String mespref = "";
		if (Util.mesActual() <= 9) {
			mespref = "0";
		}
		quincenal.add("01" + mespref + Util.mesActual() + Util.anioActual());
		quincenal.add("02" + mespref + Util.mesActual() + Util.anioActual());
		return quincenal;
	}

	public static List<String> mensual() {
		List<String> mensual = new ArrayList();
		String mespref = "";
		if (Util.mesActual() <= 9) {
			mespref = "0";
		}
		mensual.add("01" + mespref + Util.mesActual() + Util.anioActual());
		return mensual;
	}
	
	public enum TipoPago{
		
		CHEQUE(1,"CHEQUE"),
		EFECTIVO(2,"EFECTIVO"),
		TRANSFERENCIA(3,"TRANSFERENCIA");
		private Integer codigo;
		private String nombre;
		private TipoPago(Integer codigo, String nombre) {
			this.codigo = codigo;
			this.nombre = nombre;
		}
		public Integer getCodigo() {
			return codigo;
		}
		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		
		
	}

}
