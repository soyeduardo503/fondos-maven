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

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.ctrlr.CodigoCtrlr;
import sv.com.epsilon.presupuesto.pojo.CategoriasBasicas;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;
import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.GeneradorCodigo;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author usuario07
 *
 */
@ManagedBean
@ViewScoped
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
	private boolean crearCategoriasBasicas;
	private List<Integer> years;
	private String codTemp;
	
	public PresupuestoMB() {
		super(Presupuesto.class,PresupuestoFacade.class);
		
		
		
	}
	
	
	


	public void asignarCodigo(){
		codTemp="";
		String nm;
		if(getItemSelected().getYear()==null)
			codTemp=new SimpleDateFormat("yy").format(new Date());
		else {
			codTemp=getItemSelected().getYear().substring(2,4);
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
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void setYearDefault() {
		Integer yearSelected=Calendar.getInstance().get(Calendar.YEAR);
		
		if(Calendar.getInstance().get(Calendar.MONTH)>9) {
			yearSelected++;
		}
		
		
		this.getItemSelected().setYear(yearSelected+"");
	}
	
	
	
	CategoriaFacade categoriaFacade=new CategoriaFacade();
	GeneradorCodigo gc=new GeneradorCodigo();
	@Override
	public void save(){
		Presupuesto item = getItemSelected();
		item.setIdEmpresa(1);//TODO update when is ready
		getItemSelected().setCodigo(codTemp);
		getItemSelected().setActual(item.getTotal());
		getItemSelected().setAct("A");
		getItemSelected().setFechaElaboracion(new Date());
		getItemSelected().setValidasub("N");
		List<Categoria> categorias=new ArrayList<Categoria>();
		try {
			super.saveWithoutclose();
			if(this.crearCategoriasBasicas){
				List<Categoria> list= CategoriasBasicas.crearLista();
				
				
				for(Categoria categoria:list){
					Categoria cat=new Categoria();
					cat.setNombre(categoria.getNombre());
					gc.makeCode(cat);
					cat.setIdPresupuesto(item);
					cat.setMonto(0.0);
					cat.setActual(0.0);
					cat.setAct("A");
					cat.setDescripcion(cat.getNombre());
					List<Categoria> temp=categoria.getCategoriaList();
					cat.setCategoriaList(null);
					cat.setIdCategoriaPadre(null);
					categoriaFacade.save(cat);
					categorias.add(cat);
					if(temp!=null&&!temp.isEmpty()){
						crearSubCategoria(cat,temp);
					}
				}
			}	
			item.setCategoriaList(categorias);
			//categoriaFacade.close();
			updateDialogClose();
		} catch (Exception e) {

			e.printStackTrace();
			new MessageGrowlContext().send("No se puedo crear", "Presupuesto no creado");
		}
	}

	private void crearSubCategoria(Categoria categoria, List<Categoria> list) throws Exception {
		List<Categoria> categorias=new ArrayList<Categoria>();
		for(Categoria c2:list){
			Categoria cat=new Categoria();
			cat.setNombre(c2.getNombre());
			gc.makeCode(cat);
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			super.getItemSelected().setYear(Calendar.getInstance().get(Calendar.YEAR)+"");
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
	

		public UsuarioSessionMB getUsuarioSessionMB() {
			return usuarioSessionMB;
		}



		public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
			this.usuarioSessionMB = usuarioSessionMB;
		}


}
