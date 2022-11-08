/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.facade.RubroFacade;

/**
 * @author 50364
 *
 */
public class CargaCatalogoCtrlr {

	/**
	 * 
	 */
	private final static Integer CODIGO=0;
	private final static Integer NOMBRE=1;
	
	private final static Integer DESCRIPCION=2;
	private final static Integer MONTO=3;
	private final static Integer TIPO=4;
	private Integer idEmpresa=1;
	private HashMap<String,Integer> rubros=new RubroFacade().findAllRubroActiveByName();
	
	public CargaCatalogoCtrlr() {
		
	}

	public List<Categoria> processFile(InputStream is,String codPresupuesto) throws IOException{
		BufferedReader csvReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String line;
		boolean first=true;
		 List<Categoria>  list=new  ArrayList<Categoria>();
		while ((line = csvReader.readLine()) != null) {
				if(!first)
				{
					
					String[] values =line.split(";");
					Double monto=0.0;
					try {
						System.out.println(values[MONTO]);
						monto=new BigDecimal(values[MONTO]).setScale(BigDecimal.ROUND_HALF_UP, 2).doubleValue();
					}catch (Exception e) {
						e.printStackTrace();
						monto=0.0;
					}
					
					Categoria categoria=new Categoria(codPresupuesto+values[CODIGO],values[NOMBRE],values[DESCRIPCION],monto,rubros.get(values[TIPO].trim()));
					categoria.setIdEmpresa(idEmpresa);
					list.add(categoria);
				}
				first=false;
                System.out.println("->"+line);
            }
		return list;
	}
	
	
}
