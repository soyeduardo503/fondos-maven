/**
 * 
 */
package sv.com.epsilon.presupuesto.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.entities.Tipodesembolso;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.ChequeraFacade;
import sv.com.epsilon.presupuesto.ctrlr.CodigoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.MovimientoCtrlr;
import sv.com.epsilon.presupuesto.pojo.CategoriaGasto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.presupuesto.view.autocomplete.CategoriaAutocompleteMB;
import sv.com.epsilon.report.documentos.RptShow;
import sv.com.epsilon.report.pojo.Cheque;
import sv.com.epsilon.report.pojo.Documento;
import sv.com.epsilon.util.Constantes.TipoPago;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.Log;
import sv.com.epsilon.util.MessageGrowlContext;
import sv.com.epsilon.util.NumberToLetter;
import sv.com.epsilon.util.Util;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
public class IngresoGastoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4005321057957924342L;

	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB sesionMB;
	
	@ManagedProperty(value="#{categoriaAutocompleteMB}")
	private CategoriaAutocompleteMB categoriaAutocompleteMB;
	
	private Gasto gasto=new Gasto();
	private Movimiento movimiento=new Movimiento();
	private List<CategoriaGasto> list=new ArrayList<CategoriaGasto>();
	private CategoriaGasto detalle=new CategoriaGasto();
	private String categoriaTxt="";
	private String mensaje;
	private boolean showCheque;
	private boolean showTransferencia;
	private boolean showRecibo;
	private String form="IDFormGasto";
	
	BigDecimal monto=new BigDecimal(0);
	{
		movimiento.setMonto(0.0);
	}
	/**
	 * 
	 */
	public IngresoGastoMB() {
		
	}
	public UsuarioSessionMB getSesionMB() {
		return sesionMB;
	}
	public void setSesionMB(UsuarioSessionMB sesionMB) {
		this.sesionMB = sesionMB;
	}
	
	

	public Gasto getGasto() {
		return gasto;
	}
	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}
	
	
	
	public Movimiento getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
	
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	
	public CategoriaGasto getDetalle() {
		return detalle;
	}
	public void setDetalle(CategoriaGasto detalle) {
		this.detalle = detalle;
	}
	
	
	
	public void addCategoria(){
		//categoriaTxt=detalle.getTxtCategoria();
		if(!"".equals(categoriaTxt)){
			//String[] cods=categoriaTxt.split("-");
			CategoriaGasto cg=this.categoriaAutocompleteMB.obtenerCategoria(this.categoriaTxt);
			BigDecimal disponible=new CategoriaFacade().getMontoDisponible(CodigoCtrlr.getCodigoPadre( cg.getCategoria().getCodigo()));
		//CategoriaGasto cg=new CategoriaGasto(c, new Double(this.movimiento.getMonto()));
		try {
			if(disponible.doubleValue()<=0) {
				new ExecuteForm().execute("PF('DlgGasto').show();");
				return ;
			}
			if(disponible.doubleValue()<=0) {
				this.mensaje="Se requiere el monto del gasto!!!";
				new ExecuteForm().execute("PF('DlgRequired').show();");
				return ;
			}
			if(foundCategoria(cg)) {
				this.mensaje="Esta Caetgoria ya fue agregada!!!";
				new ExecuteForm().execute("PF('DlgRequired').show();");
				return ;
			}
			Double montoAplicable=this.movimiento.getMonto();
			if(cg!=null ){
				
				Log.info("Disponible :"+disponible.doubleValue());
				
				Log.info("Mov:"+this.movimiento.getMonto());
				if(disponible.doubleValue()<montoAplicable) {
					new ExecuteForm().execute("PF('DlgGasto').show();");
				}else {
								cg.setDisponible(disponible.doubleValue());
								this.list.add(cg);
								recalcularMonto();
								this.categoriaTxt="";
								
				}
	//			
	//			recalcularMonto();
				
			}
		}catch(Exception e) {
			Log.error(e,"Error en carga de archivo");
		}
		
		}
	}
	
	public void recalcularTotal() {
		recalcularMonto();
		
	}
	
	public void actualizarCheque(Tipodesembolso idTipoDesembolso) {
		this.gasto.setCheque("");
		showCheque=false;
		showRecibo=false;
		showTransferencia=false;
		ChequeraFacade facade = new ChequeraFacade();
		facade.setIdEmpresa(sesionMB.getIdEmpresa());
		facade.setToken(sesionMB.getToken());
		switch (idTipoDesembolso.getIdTipoDesembolso()) {
		case 1: 
			showCheque=true;			
			gasto.setCheque( facade.findCurrentValue().toString());
			break;
		case 2:			
			showTransferencia=true;
			
			break;
		case 3: 
			
			showRecibo=true;
			
			
			break;	
		default:
			break;
		}
	}
	
	private boolean foundCategoria(CategoriaGasto cg) {
		for(int i=0;i<list.size();i++) {
			if(cg.getCategoria().getIdCategoria()==list.get(i).getCategoria().getIdCategoria())
				return true;
		}
		return false;
	}
	public void removeCategoriaGasto(CategoriaGasto cg) {
		list.remove(cg);
		recalcularMonto();
		new ExecuteForm().update(":IDFormGasto:idOrgienCategorias");
	}
	
	
	

	private void recalcularMonto() {
		if(list.size()<1)
			return ;
		monto=new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP) ;
		
		
			
		list.forEach(gasto->{		
				monto=Util.addAndRound2(monto, new BigDecimal(gasto.getMonto()));
		});
		
		gasto.setTotal(monto.doubleValue());
		
		
	}
	public void upload(FileUploadEvent event) {
		try {
			copyFile(event.getFile().getFileName(), event.getFile().getInputStream());
			FacesMessage message = new FacesMessage("El archivo se ha subido con éxito!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			//copyFile(categoriaTxt, null);
		} catch (IOException e) {
			Log.error(e,"Error en carga de archivo");
		}

		}
		public void copyFile(String fileName, InputStream in) {
		try {
			String destination="C:\\files\\img\\epsilon\\";
			OutputStream out = new FileOutputStream(new File(destination + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
			out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
			System.out.println("El archivo se ha creado con éxito!");
	
			DateFormat dateFormat = new SimpleDateFormat("yyyy–MM–dd HH_mm_ss");
			Date date = new Date();
			String ruta1 = destination + fileName;
			String ruta2 = destination + dateFormat.format(date)+"–"+fileName;
			System.out.println("Archivo: "+ruta1+" Renombrado a: "+ruta2);
			File archivo=new File(ruta1);
			archivo.renameTo(new File(ruta2));
		} catch (IOException e) {
			Log.error(e,"Error en carga de archivo");
		}
		}
		
		public void print() {
			Documento doc=new Documento();
			doc.setExt("PDF");
			doc.setNombre("Cheque FPT");
			if(new File("C:\\\\files\\\\fondos\\\\reportes\\\\cheque.jasper").exists())
				doc.setPath("C:\\\\files\\\\fondos\\\\reportes\\\\cheque.jasper");
			else
				doc.setPath("/opt/epsilon/reporte/cheque.jasper");
			Cheque cheque=new Cheque();
			String cantidadLetras=new NumberToLetter().Convertir(String.valueOf(gasto.getTotal()),true, true, true);
			cheque.setCantidad(gasto.getTotal());

			cheque.setCantidadLetras(cantidadLetras);
			cheque.setConcepto(this.gasto.getNombre());
			cheque.setProveedor(gasto.getIdProveedor().getNombreLegal());
			new RptShow().callReport(doc, cheque);
		}
		
		public List<CategoriaGasto> getList() {
			return list;
		}
		public void setList(List<CategoriaGasto> list) {
			this.list = list;
		}
		public String getCategoriaTxt() {
			return categoriaTxt;
		}
		public void setCategoriaTxt(String categoriaTxt) {
			this.categoriaTxt = categoriaTxt;
		}
		public CategoriaAutocompleteMB getCategoriaAutocompleteMB() {
			return categoriaAutocompleteMB;
		}
		public void setCategoriaAutocompleteMB(
				CategoriaAutocompleteMB categoriaAutocompleteMB) {
			this.categoriaAutocompleteMB = categoriaAutocompleteMB;
		}
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
		
		public void save() {
			try {
			//	gasto.setMovimientoList(createMovimientos());
				GastoCtrlr.save(gasto);
				createMovimientos();
				if(gasto.getIdTipoDesembolso().getIdTipoDesembolso()==1)
					print();
			} catch (Exception e) {
				new MessageGrowlContext().sendError("Error guardando informacion: "+e.getMessage(), e.getMessage(), e);
			}
		}
		
		public List<Movimiento> createMovimientos(){
			List<Movimiento> mvts=new ArrayList<>();
			this.list.forEach(temp->{
				Movimiento mov=new Movimiento();
				mov.setIdCategoria(		temp.getCategoria());
				mov.setFecha(gasto.getFecha());
				mov.setTipo("D");//debito
				mov.setMonto(temp.getMonto());
				mov.setFechaRegistro(new Date());
				mov.setCuenta("99999");
				mov.setIdUsuario(this.sesionMB.getIdUser());
				MovimientoCtrlr.save(mov);
				//mvts.add(mov);
				//mov.setIdGasto(gasto)
				
			});
			return mvts;
		}
		
		

		
	
	
}
