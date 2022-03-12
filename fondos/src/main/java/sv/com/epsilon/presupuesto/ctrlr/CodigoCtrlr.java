/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

/**
 * @author 50364
 *
 */
public class CodigoCtrlr {

	/**
	 * 
	 */
	public CodigoCtrlr() {
		// TODO Auto-generated constructor stub
	}

	public String createFromName(String nombrePresupuesto) {
		if(nombrePresupuesto.split(" ").length>1) {
			if(nombrePresupuesto.split(" ").length==2) {
				String[] nms= nombrePresupuesto.split(" ");
				return nms[0].substring(0, 2)+nms[1].substring(0, 1);
			}
			if(nombrePresupuesto.split(" ").length==3) {
				String[] nms= nombrePresupuesto.split(" ");
				return nms[0].substring(0, 1)+nms[1].substring(0, 1)+nms[2].substring(0, 1);
			}
		}else
			return nombrePresupuesto.substring(0,3);
		return "ABC";
	}
	
	public static String getCodigoPadre(String codigo) {
		return codigo.length()>5?codigo.substring(0, codigo.length()-2):codigo.substring(0, 5);
	}
public static void main(String[] args) {
	System.out.println(new CodigoCtrlr().getCodigoPadre("22FPT0101"));
}
}
