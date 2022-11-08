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
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import org.primefaces.event.FileUploadEvent;

import sv.com.epsilon.entities.Banco;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Chequera;
import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.entities.Gasto;
import sv.com.epsilon.entities.Movimiento;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.entities.Tipodesembolso;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.ChequeraFacade;
import sv.com.epsilon.facade.CorrelativoreciboFacade;
import sv.com.epsilon.facade.TipodesembolsoFacade;
import sv.com.epsilon.presupuesto.ctrlr.CategoriaGastoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.CodigoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.GastoCtrlr;
import sv.com.epsilon.presupuesto.ctrlr.MovimientoCtrlr;
import sv.com.epsilon.presupuesto.pojo.CategoriaGasto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.presupuesto.view.autocomplete.CategoriaAutocompleteMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.Log;
import sv.com.epsilon.util.MessageGrowlContext;
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
	private List<Banco> listBanco;
	private Banco bancoSelected;
	private List<Cuenta> listCuenta;
	private Cuenta cuentaSelected;
	private List<Chequera> listChequera;
	private Integer idChequeraSelected;
	private Categoria categoriaPadreSelected;
	private Gasto gasto=new Gasto();
	private Movimiento movimiento=new Movimiento();
	private List<CategoriaGasto> list=new ArrayList<CategoriaGasto>();
	private CategoriaGasto detalle=new CategoriaGasto();
	private String categoriaTxt="";
	private String mensaje;
	private HashMap<String,Object> filter;
	private boolean showCheque;
	private boolean showTransferencia;
	private boolean showRecibo;
	private String form="IDFormGasto";
	private boolean automaticChequera=false;
	private Date currentDate=Calendar.getInstance().getTime();
	private Presupuesto presupuestoSelected;
	private List<Presupuesto> listPresupuesto;
	
	private List<Categoria> listCategoriaPrincipal;
	private List<Tipodesembolso> listTipoDesembolso;
	private List<String> imgBase64;
	
	private boolean disablePost=true;
	
	private String code=initCode();
	
	private Integer idGastoSelected;
	
	BigDecimal monto=new BigDecimal(0);

	private boolean chequeraBloq;
	{
		movimiento.setMonto(0.0);
	}
	
	public void preRender() {
		if(!FacesContext.getCurrentInstance().isPostback()) {
			this.gasto.setFecha(new Date());
			gasto.setFechaRegistro(new Date());
			GastoCtrlr.loadin(this);
			listTipoDesembolso= new TipodesembolsoFacade().findAllActive();
			presupuestoSelected=sesionMB.getPresupuestoSelected();
		
			
		}
		
	}
	
	private String initCode() {
		StringBuilder sb=new StringBuilder();
		Random ran = new Random();
		String[] letter=new String[] {"A","P","J","R"};
		
		sb.append(letter[(char)ran.nextInt(3)]).append(letter[(char)ran.nextInt(3)]).append(letter[(char)ran.nextInt(3)]).append(System.currentTimeMillis());
				
		return sb.toString();
	}

	public void load(Gasto g) {
		this.gasto=g;
		this.list= new CategoriaGastoCtrlr().convert(g.getIdGasto());
		new ExecuteForm().update(form);
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
	
	
	

	public List<Tipodesembolso> getListTipoDesembolso() {
		return listTipoDesembolso;
	}

	public void setListTipoDesembolso(List<Tipodesembolso> listTipoDesembolso) {
		this.listTipoDesembolso = listTipoDesembolso;
	}

	public Integer getIdGastoSelected() {
		return idGastoSelected;
	}

	public void setIdGastoSelected(Integer idGastoSelected) {
		this.idGastoSelected = idGastoSelected;
	}

	public boolean isAutomaticChequera() {
		return automaticChequera;
	}

	public void setAutomaticChequera(boolean automaticChequera) {
		this.automaticChequera = automaticChequera;
	}

	public Gasto getGasto() {
		return gasto;
	}
	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}
	
	
	
	public boolean isDisablePost() {
		return disablePost;
	}

	public void setDisablePost(boolean disablePost) {
		this.disablePost = disablePost;
	}

	public Presupuesto getPresupuestoSelected() {
		return presupuestoSelected;
	}

	public void setPresupuestoSelected(Presupuesto presupuestoSelected) {
		this.presupuestoSelected = presupuestoSelected;
	}

	public List<Presupuesto> getListPresupuesto() {
		return listPresupuesto;
	}

	public void setListPresupuesto(List<Presupuesto> listPresupuesto) {
		this.listPresupuesto = listPresupuesto;
	}

	public List<Categoria> getListCategoriaPrincipal() {
		return listCategoriaPrincipal;
	}

	public void setListCategoriaPrincipal(List<Categoria> listCategoriaPrincipal) {
		this.listCategoriaPrincipal = listCategoriaPrincipal;
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
	
	
	
	public List<Banco> getListBanco() {
		return listBanco;
	}

	public void setListBanco(List<Banco> listBanco) {
		this.listBanco = listBanco;
	}

	public Banco getBancoSelected() {
		return bancoSelected;
	}

	public void setBancoSelected(Banco bancoSelected) {
		this.bancoSelected = bancoSelected;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
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
	
	public void updateNumberCurrentCheque() {
		ChequeraFacade facade = new ChequeraFacade();
		if(idChequeraSelected!=null&&idChequeraSelected>0)
			gasto.setCheque(facade.findCurrentValue(idChequeraSelected));
	}
	
	public void actualizarCheque(Tipodesembolso idTipoDesembolso) {
		this.gasto.setCheque(null);
		showCheque=false;
		showRecibo=false;
		showTransferencia=false;
		ChequeraFacade facade = new ChequeraFacade();
		CorrelativoreciboFacade correFacade=new CorrelativoreciboFacade();
		switch (idTipoDesembolso.getIdTipoDesembolso()) {
		case 1:
			setIdChequeraSelected(getListChequera().get(0).getIdChequera());
			gasto.setCheque(facade.findCurrentValue(idChequeraSelected));
			
			return ;
			
		case 2:
			gasto.setCheque(correFacade.findCurrentValue());
			return ;
		default:
			this.setIdChequeraSelected(0);
			this.gasto.setCheque(null);
			chequeraBloq=true;
			break;
		}
		
		


		

	}
	
	
	
	
	
	public boolean isChequeraBloq() {
		return chequeraBloq;
	}

	public void setChequeraBloq(boolean chequeraBloq) {
		this.chequeraBloq = chequeraBloq;
	}

	public List<Chequera> getListChequera() {
		return listChequera;
	}
	public void setListChequera(List<Chequera> listChequera) {
		this.listChequera = listChequera;
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
				monto=Util.addAndRound2(monto, new BigDecimal(gasto.getMonto()!=null?gasto.getMonto():0.0));
		});
		
		gasto.setTotal(monto.doubleValue());
		
		
	}
	
	 
	public void upload(FileUploadEvent event) {
		try {
			copyFile(event.getFile().getFileName(), event.getFile().getInputStream());
			FacesMessage message = new FacesMessage("El archivo se ha subido con exito!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			//copyFile(categoriaTxt, null);
		} catch (IOException e) {
			Log.error(e,"Error en carga de archivo");
		}finally {
			new ExecuteForm().Update(":"+this.form+":img-content");
		}

		}
		public void copyFile(String fileName, InputStream in) {
		try {
			if(imgBase64==null) {
				imgBase64=new ArrayList<String>();
			}
			
			
			
			
			
			imgBase64.add(Base64.getEncoder().encodeToString(in.readAllBytes()));
			
			in.close();
			
			System.out.println("El archivo se ha creado con �xito!");
	
			
			
			
			
			
		} catch (IOException e) {
			Log.error(e,"Error en carga de archivo");
		}
		}
		
		
		
		
		

		public List<String> getImgBase64() {
			return imgBase64;
		}

		public void setImgBase64(List<String> imgBase64) {
			this.imgBase64 = imgBase64;
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
		
		
		
		
		public Integer getIdChequeraSelected() {
			return idChequeraSelected;
		}

		public void setIdChequeraSelected(Integer idChequeraSelected) {
			this.idChequeraSelected = idChequeraSelected;
		}

		public List<Cuenta> getListCuenta() {
			return listCuenta;
		}
		public void setListCuenta(List<Cuenta> listCuenta) {
			this.listCuenta = listCuenta;
		}
		public Cuenta getCuentaSelected() {
			return cuentaSelected;
		}
		public void setCuentaSelected(Cuenta cuentaSelected) {
			this.cuentaSelected = cuentaSelected;
		}
		public Categoria getCategoriaPadreSelected() {
			return categoriaPadreSelected;
		}
		public void setCategoriaPadreSelected(Categoria categoriaPadreSelected) {
			this.categoriaPadreSelected = categoriaPadreSelected;
		}
		public void save() {
			try {
				
				gasto.setNombre(gasto.getDescripcion());
				gasto.setKpresupuesto(getPresupuestoSelected().getIdPresupuesto());
				Integer id=GastoCtrlr.save(gasto);
				this.gasto.setIdGasto(id);
				List<Movimiento> listMovToSave = createMovimientos();
				try {
					listMovToSave.forEach(mov->{
						try {
							mov.setIdGasto(new Gasto(id));
							mov.setIdPresupuesto(presupuestoSelected);
							new MovimientoCtrlr().save(mov);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				} catch (Exception e) {
					Log.error(e, "Error en creacion de movimientos");
				}
				if(gasto.getIdTipoDesembolso().getIdTipoDesembolso()==1) {
					new ChequeraFacade().updateCurrent(idChequeraSelected);
					
				}
				
				
				new MessageGrowlContext().send("Gasto Registrado!!!", "Se guardo la informacion");
				disablePost=false;
				this.code= initCode();
			} catch (Exception e) {
				new MessageGrowlContext().sendError("Error guardando informacion: "+e.getMessage(), e.getMessage(), e);
			}
		}
		
		public void emptyForm() {
			this.gasto=new Gasto();
			this.categoriaTxt="";
			this.chequeraBloq=true;
			gasto.setIdTipoDesembolso(null);
			list=new ArrayList<CategoriaGasto>();
			disablePost=true;
			new ExecuteForm().update(this.form);
			
			
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
				mov.setCuenta(cuentaSelected.getNumero());
				mov.setIdUsuario(this.sesionMB.getIdUser());
				mov.setIdGasto(gasto);
				mvts.add(mov);
				//mov.setIdGasto(gasto)
				
			});
			return mvts;
		}
		
		public void load() {
			//TODO create a load a gasto saved
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		
		

		
		
	
	
}
