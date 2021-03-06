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

import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.facade.BancoFacade;

/**
 * @author 50364
 *
 */
public class CargaProveedorCtrlr {

	/**
	 * 
	 */
	private final static Integer CODIGO=0;
	private final static Integer NOMBRE=1;
	
	private final static Integer NOMBRE_LEGAL=2;
	private final static Integer TIPO=3;
	private final static Integer NIT=4;
	private final static Integer DUI=5;
	private final static Integer NCR=6;
	private final static Integer BANCO=7;
	private final static Integer CUENTA=8;
	private final static String JURIDICO="J";
	private final static String NATURAL="N";
	
	public CargaProveedorCtrlr() {
		
	}
	private HashMap<String,Integer> bancos=new BancoFacade().buscarHashBancosActivos();

	public List<Proveedor> processFile(InputStream is,Integer idEmpresa) throws IOException{
		BufferedReader csvReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String line;
		boolean first=true;
		 List<Proveedor>  list=new  ArrayList<Proveedor>();
		while ((line = csvReader.readLine()) != null) {
				if(!first)
				{
					
					String[] values =line.split(";");
					
					/**
					 * String nombre, String nombreLegal, String tipo, String dui, String nrc, String nit, String ncuenta,
			int idEmpresa
					 * */
					Integer idBanco=bancos.get(values[BANCO].toString())!=null?bancos.get(values[BANCO].toString()):0;
					String dui=values[TIPO].equals(JURIDICO)?"": values[DUI];
					Proveedor proveedor=new Proveedor(values[NOMBRE],values[NOMBRE_LEGAL],values[TIPO],dui,values[NCR],
							values[NIT],idBanco,values[CUENTA],idEmpresa);
					list.add(proveedor);
				}
				first=false;
                System.out.println("->"+line);
            }
		return list;
	}
	
	
}
