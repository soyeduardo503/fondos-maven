/**
 * 
 */
package sv.com.epsilon.presupuesto.ctrlr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Proveedor;
import sv.com.epsilon.entities.Tipodesembolso;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.GastoFacade;
import sv.com.epsilon.facade.ProveedorFacade;
import sv.com.epsilon.presupuesto.pojo.GastoLoad;
import sv.com.epsilon.util.Log;
import sv.com.epsilon.util.helper.LoadGastoHelper;

/**
 * @author 50364
 *
 */
@Slf4j
public class CargaGastoCtrlr {

	/**
	 * 
	 */

	private final static Integer CHEQUE = 0;
	private final static Integer FECHA = 1;
	private final static Integer CODIGO_CAT = 2;
	private final static Integer PROVEEDOR = 7;
	private final static Integer NSUJETO = 4;

	private final static Integer DESCRIPCION = 8;
	private final static Integer MONTO = 6;
	private final static Integer MONTO_INGRESO = 7;
	private final static Integer TIPO = 0;

	private final static String REMESA = "REMESA";
	private final static String TRANSF = "TRANSF";
	private final static String DB = "D/B";
	private final static String separatorField=";";
	

	private Integer idEmpresa = 1;
	private List<Proveedor> proveedores = new ProveedorFacade().findAll();
	private HashMap<String, String> uniq=new HashMap<>(); 
	
	public CargaGastoCtrlr() {

	}
	/*
	public List<Gasto> processFileProveedor(InputStream is) throws Exception {
		
		BufferedReader csvReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String line;
		while ((line = csvReader.readLine()) != null) {
			String[] values = line.split(separatorField);
			Optional<Proveedor> proveedor = proveedores.stream()
					.filter(n -> n.getNombre().equalsIgnoreCase(values[PROVEEDOR].trim()) || n.getNombreLegal().equalsIgnoreCase(values[PROVEEDOR].trim())).findFirst();
			if (!proveedor.isPresent()) {
				log.info("->"+values[PROVEEDOR].trim());
				uniq.put(values[PROVEEDOR].trim(), values[PROVEEDOR].trim());
			}
		}
		log.info("List:");
		log.info(uniq.keySet()+"");
		return new ArrayList<>();
	}
	*/
	StringBuilder errors=new StringBuilder();
	public List<Gasto> processFile(InputStream is,boolean readJson)throws Exception {
		BufferedReader jsonReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		StringBuilder sb=new StringBuilder();
		String line;
		
		List<Gasto> list=new ArrayList<>();
		while((line=jsonReader.readLine())!=null) {
			sb.append(line);
		}
		
		 Stream<GastoLoad> loadObjects = Stream.of( new ObjectMapper().readValue(sb.toString(), GastoLoad[].class));
		 loadObjects.filter(load->load.getMontoAbono().isEmpty()).forEach(v-> {
			try {
				list.add(LoadGastoHelper.assigData(v,proveedores,uniq));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		log.info("Errors->");
		errors=new StringBuilder();
		uniq.forEach((k,v)->errors.append("\"proveedor\":"+"\""+v+"\","));
		log.info(errors.toString());
		return list;
	}

	@Deprecated/*
	public List<Gasto> processFile(InputStream is) throws Exception {
		BufferedReader csvReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String line;
		List<String> listNoFound=new ArrayList<>();
		List<Gasto> list = new ArrayList<Gasto>();
		while ((line = csvReader.readLine()) != null) {
			Gasto g = new Gasto();
			try {
				String[] values = line.split(",");

				g.setAct("A");
				if (!values[CHEQUE].equals(TRANSF)) {
					g.setCheque(Integer.valueOf(values[CHEQUE]));
					g.setIdTipoDesembolso(new Tipodesembolso(1));

				} else {
					g.setCheque(0);
					g.setIdTipoDesembolso(new Tipodesembolso(3));
				}
				Double monto = 0.0;
				try {

					monto = new BigDecimal(values[MONTO]).setScale(BigDecimal.ROUND_HALF_UP, 2).doubleValue();
				} catch (Exception e) {
					e.printStackTrace();
					monto = 0.0;
				}
				g.setTotal(monto);
				g.setDescripcion(values[DESCRIPCION].replace("*",","));
				g.setNombre(values[DESCRIPCION].replace("*",","));
				g.setFechaRegistro(new Date());
				g.setFecha(parseDate(values[FECHA]));
				g.setIdEmpresa(1);
				g.setKpresupuesto(1);
				g.setStatus("A");
				

				Optional<Proveedor> proveedor = proveedores.stream()
						.filter(n -> n.getNombre().equalsIgnoreCase(values[PROVEEDOR].trim()) || n.getNombreLegal().equalsIgnoreCase(values[PROVEEDOR].trim())).findFirst();
				if (proveedor.isPresent()) {
					g.setIdProveedor(proveedor.get());
					if (g.getIdProveedor().getRetencion().equals("S")) {
						g.setNumeroComprobante(values[NSUJETO].replace("suj/exc#", ""));
					}
				}else {
					Log.info(values[PROVEEDOR].trim());
					if(!listNoFound.contains(values[PROVEEDOR].trim()))
						listNoFound.add(values[PROVEEDOR].trim());
					throw new Exception("No fue posible encontrar proveedor \n"+values[PROVEEDOR].trim());
				}

				Categoria categoria = new CategoriaFacade().obtenerCategoriasFromCodigo( values[CODIGO_CAT].startsWith("23FPT")? values[CODIGO_CAT]:"23FPT"+values[CODIGO_CAT]);
				if(categoria==null) {
					throw new Exception("No fue posible encontrar categoria: "+values[CODIGO_CAT]);
				}
				g=new GastoFacade().save(g);
				
				Movimiento mov = new Movimiento();
				mov.setFecha(g.getFecha());
				mov.setIdCategoria(categoria);
				mov.setFechaRegistro(new Date());
				mov.setIdGasto(g);
				mov.setIdPresupuesto(new Presupuesto(1));
				mov.setIdUsuario(3);
				mov.setMonto(g.getTotal());
				mov.setTipo("D");

				new MovimientoCtrlr().save(mov);
//				System.out.println("->" + line);
			} catch (Exception e) {
				log.info("line->"+line);
				Log.error(e, "Error al procesar linea");
				list.add(g);
			}
			
		}
		Log.info(listNoFound);
		return list;
	}
*/
	private Date parseDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(date);
	}

}
