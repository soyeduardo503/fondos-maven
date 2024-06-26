/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.ctrlr.CodigoCtrlr;
import sv.com.epsilon.presupuesto.pojo.CategoriasBasicas;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.presupuesto.view.dashboard.ProyeccionesMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.GeneradorCodigo;
import sv.com.epsilon.util.MessageGrowlContext;
import sv.com.epsilon.util.ReadProperty;
import sv.com.epsilon.util.RedirectNv;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
@Data
@Slf4j
public class PresupuestoMB extends AbstractMantto<Presupuesto, PresupuestoFacade> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	
	@ManagedProperty(value = "#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	private boolean crearCategoriasBasicas=false;
	private List<Integer> years;
	private String codTemp;
	private Presupuesto clone;
	
	public PresupuestoMB() {
		super(Presupuesto.class,PresupuestoFacade.class);
		
		
		
	}
	
	
	public List<Presupuesto> presupuestoActive(){
		return getList().stream().filter(p->p.getAct().equalsIgnoreCase("A")).collect(Collectors.toList());
	}
	
	public void showClone(Presupuesto cl) {
		this.clone=new Presupuesto();
		clone.setNombrePresupuesto(cl.getNombrePresupuesto());
		clone.setIdPresupuesto(cl.getIdPresupuesto());
		clone.setYear( cl.getYear() +1);
		//clone.setIsPrimary(cl);
	}

	public void clonePresupuesto() {
		try {
			boolean resp = new PresupuestoFacade().action("/duplicate", true,clone);
			if(resp) {
				ReadProperty reader=new ReadProperty();
				usuarioSessionMB.asignarSelected();
				new RedirectNv(reader.read(	"url.properties","mantenimientopresupuesto"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void asignarCodigo(){
		codTemp="";
		String nm;
		if(getItemSelected().getYear()==null)
			codTemp=new SimpleDateFormat("yy").format(new Date());
		else {
			codTemp=(""+getItemSelected().getYear()).substring(2,4);
		}
		if(getItemSelected().getNombrePresupuesto()!=null&&getItemSelected().getNombrePresupuesto().length()>3)
			nm=new CodigoCtrlr().createFromName(getItemSelected().getNombrePresupuesto());
		else
			nm="AAA";
		codTemp+=nm;
		codTemp=codTemp.toUpperCase();
		new ExecuteForm().Update(this.getIdFormNew()+":codigo");
	}

	

	@Override
	public void reset() {
		
		try {
			this.setItemSelected(this.oneNewObject());
			setYearDefault();
			
		} catch (InstantiationException  |  IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error en crear un default", e);
		}
		
	}
	
	
	public void setYearDefault() {
		Integer yearSelected=Calendar.getInstance().get(Calendar.YEAR);
		
		if(Calendar.getInstance().get(Calendar.MONTH)>9) {
			yearSelected++;
		}
		this.getItemSelected().setSizesubs(2);
		
		this.getItemSelected().setYear(yearSelected);
		
	}
	
	
	
	CategoriaFacade categoriaFacade=new CategoriaFacade();
	GeneradorCodigo gc=new GeneradorCodigo();
	@Override
	public void save(){
		if(getItemSelected().getIdPresupuesto()!=null&&getItemSelected().getIdPresupuesto()>0) {
			try {
				new PresupuestoFacade().update(getItemSelected());
				new ExecuteForm().Execute("PF('"+getDialogEdit()+"').hide();");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Error en crear un default", e);
			}
			return;
		}
		Presupuesto item = getItemSelected();
		
		item.setIdEmpresa(1);//TODO update when is ready
		getItemSelected().setCodigo(codTemp);
		getItemSelected().setActual(item.getTotal());
		getItemSelected().setAct("A");
		getItemSelected().setFechaElaboracion(new Date());
		getItemSelected().setValidasub("N");
		getItemSelected().setSizesubs(2);
		Calendar c=Calendar.getInstance();
		c.setTime(		item.getFechaInicio());
		item.setMesCierre(c.get(Calendar.MONTH)+1);
		item.setAbonado(0.0);
		List<Categoria> categorias=new ArrayList<Categoria>();
		try {
			super.save();
			
			
			//categoriaFacade.close();
			updateDialogClose();
		} catch (Exception e) {
			log.error("Error en crear un default", e);
			e.printStackTrace();
			new MessageGrowlContext().send("No se puedo crear", "Presupuesto no creado");
		}
	}

	private void crearSubCategoria(Categoria categoria, List<Categoria> list) throws Exception {
		List<Categoria> categorias=new ArrayList<Categoria>();
		for(Categoria c2:list){
			Categoria cat=new Categoria();
			cat.setNombre(c2.getNombre());
			gc.code(cat);
			cat.setIdPresupuesto(null);
			cat.setMonto(0.0);
			cat.setActual(0.0);
			cat.setAct("A");
			cat.setDescripcion(cat.getNombre());
			cat.setIdCategoriaPadre(categoria);
			List<Categoria> temp=c2.getCategoriaList();
			cat.setCategoriaList(null);
			categoriaFacade.save(cat);
			categorias.add(cat);
			if(temp!=null&&!temp.isEmpty()){
				crearSubCategoria(cat,temp);
			}
		}
		categoria.setCategoriaList(categorias);
		
	}

	@Override
	public void update() {
		new ExecuteForm().Update(getFormsView());
		
	}
	
	@Override
	public void defineHeaders() {
		this.setHeaderDialog("Nuevo Presupuesto");
		this.setHeaderPage("Mantenimiento Presupuestos");
		this.setHeaderTable("Tabla de Presupuesto ");
		setDialogEdit("dlgEditPresupuesto");
		setDialogDelete("DlgDeletePresupuesto");
		setIdFormDelete("idDlgPresupuesto");
		setIdFormEdit("idEditPresupuesto");
		setIdFormNew("idNewPresupuesto");
		setIdWtgNew("idNewPresupuestoWgt");
		setWtgDialog("wgtNewPresupuesto");
	}
	

	@Override
	public void preRender() {
		try 
		{
		if(!FacesContext.getCurrentInstance().isPostback()){	
			if(usuarioSessionMB.getToken()!=null)
				init(usuarioSessionMB.getToken(), usuarioSessionMB.getIdEmpresa());
			initFacade();
			closeFacade();
			this.setList(getFacade().findAllActive());
			this.years=((PresupuestoFacade)getFacade()).findYears();
			//this.callLoad();
			defineHeaders();
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error en crear un preRender", e);
		}
		
	}
	
	public void closeFacade(){
//		if(this.getFacade()!=null)
//			this.getFacade().close();
	}

	@Override
	@PreDestroy
	public void preDestroy() {
		super.destroy();
		
	}
	
	@Override
	public void remove(){
		if(new CategoriaFacade().CountByPresupuesto(getItemSelected())==0){
			try {
				super.remove();
				this.setList(getFacade().findAllActive());
				new ExecuteForm().Update(this.getIdFormList());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Error en Remove", e);
			}
		}
	}

	public void remove(Presupuesto p){
		
			try {
				new PresupuestoFacade().action("/deleteByPresupuesto", true, p);
				this.setList(getFacade().findAllActive());
				new ExecuteForm().Update(this.getIdFormList());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				log.error("Error en remover item", e);
			}
		
	}

	public boolean isCrearCategoriasBasicas() {
		return crearCategoriasBasicas;
	}

	public void setCrearCategoriasBasicas(boolean crearCategoriasBasicas) {
		this.crearCategoriasBasicas = crearCategoriasBasicas;
	}

	@Override
	public Presupuesto getItemSelected(){
		if(super.getItemSelected()!=null&&super.getItemSelected().getYear()==null||super.getItemSelected().getYear().equals(""))
			super.getItemSelected().setYear(Calendar.getInstance().get(Calendar.YEAR));
		return super.getItemSelected();
	}

	@Override
	protected void setValueMod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDefaultValue() {
		this.setYearDefault();
		
	}

	@Override
	public void validateDataValue() throws Exception {
	
		
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public String getCodTemp() {
		return codTemp;
	}

	public void setCodTemp(String codTemp) {
		this.codTemp = codTemp;
	}
	
	
	public String flow(FlowEvent event) {
	       
	            return event.getNewStep();
	        
	 }
	
	public String color(Presupuesto pr) {
		return pr.getIsPrimary()==1?"#FF0099":"#484848";
	}
	
	
	public void makeDefault(Presupuesto p) {
		try {
			new PresupuestoFacade().makeMain(p);
		
			this.getList().forEach(pr->pr.defaultValuePrimary(p.getIdPresupuesto()));
			new ExecuteForm().Update(this.getIdFormList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error en setiar  un default", e);
		}
	}
	



}
