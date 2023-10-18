/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.BancoFacade;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.util.Log;

/**
 * @author 50364
 *
 */
public class CargaProveedorCtrlr {

	/**
	 * 
	 */
	private final static Integer CODIGO = 0;
	private final static Integer NOMBRE = 1;

	private final static Integer NOMBRE_LEGAL = 2;
	private final static Integer TIPO = 3;
	private final static Integer NIT = 4;
	private final static Integer DUI = 5;
	private final static Integer NCR = 6;
	private final static Integer BANCO = 7;
	private final static Integer CUENTA = 8;
	private final static String JURIDICO = "J";
	private final static String NATURAL = "N";
	private final static Integer RETENCION = 9;
	private Integer codCorrelative=110;

	public CargaProveedorCtrlr() {

	}

	private HashMap<String, Integer> bancos = new BancoFacade().buscarHashBancosActivos();

	public List<Proveedor> processFile(InputStream is, Integer idEmpresa) throws IOException {

		BufferedReader csvReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String line;
		boolean first = true;
		List<Proveedor> list = new ArrayList<Proveedor>();
		while ((line = csvReader.readLine()) != null) {
			try {
				if (!first) {

					String[] values = line.split(";");

					/**
					 * String nombre, String nombreLegal, String tipo, String dui, String nrc,
					 * String nit, String ncuenta, int idEmpresa
					 */
					Integer idBanco = 0;
					String dui = values[TIPO].equals(JURIDICO) ? "" : values[DUI];
					Proveedor proveedor = new Proveedor(values[NOMBRE], values[NOMBRE_LEGAL], values[TIPO], dui,
							 values[NIT],values[NCR], idBanco, "00000", 1);
					if (!values[TIPO].equals(JURIDICO)) {
						proveedor.setRetencion("S");
					}
					
					proveedor.setRetencion(values[RETENCION]);
					if(!exist(proveedor)) {
						proveedor.setCodigo("FTP"+(++codCorrelative));
						list.add(proveedor);
						
					}
				}
				first = false;
				System.out.println("->" + line);
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e,"Error al procesar un proveedor");
			}
		}
		return list;
	}

	private boolean exist(Proveedor proveedor) {
		  try {
			  Optional<Proveedor> res=Optional.of(	new ProveedorFacade().search(proveedor));
		   return res.isPresent();//if exist shoult return true
		  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
