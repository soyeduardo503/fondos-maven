package sv.com.epsilon.presupuesto.view.autocomplete;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.websocket.Session;

import net.sf.jasperreports.charts.util.CategoryChartHyperlinkProvider;
import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.entities.Presupuesto;
import sv.com.epsilon.facade.CategoriaFacade;
import sv.com.epsilon.facade.PresupuestoFacade;
import sv.com.epsilon.presupuesto.pojo.CategoriaGasto;
import sv.com.epsilon.presupuesto.session.UsuarioSessionMB;

@ManagedBean
@ViewScoped
public class CategoriaAutocompleteMB {

	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB sesionMB;
	private String principalSelected;
	 private List<String> list=new ArrayList<String>();
	 private List<String> categorias=new ArrayList<String>();
	 private HashMap<String,CategoriaGasto> categoriasCod=new HashMap<String,CategoriaGasto>();
	 //private List<CategoriaGasto> aplicables=new ArrayList<CategoriaGasto>();
	 private List<Categoria> principales=null;
	
	public CategoriaAutocompleteMB() {
		
	}
	
	public void preRender(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			Optional<Presupuesto> preDeault = new PresupuestoFacade().defaultValue();
			Presupuesto p=new Presupuesto(1);
			p.setCodigo("23FPT");
			if(preDeault.isPresent()) {
				p=preDeault.get();
			}
			List<Categoria> categorias=new CategoriaFacade().findAllChildrenSelectableActive(p.getCodigo());
			principales=new CategoriaFacade().findPrincipalByCodPresupuesto(p.getIdPresupuesto());
			
			//for(Presupuesto presupuesto: presupuestos){
//				String cod=new StringBuilder(presupuesto.getNombrePresupuesto()).toString();
			if(categorias.stream().findFirst().isPresent())
				principalSelected=principales.get(0).getCodigo();
				for(Categoria c:categorias){
//					if(c.getMonto()==0)
						categoriaAplicable(c);
						
				}
			
		}
	}
	
	public void searchChildrenAplicable() {
		categoriasCod=new HashMap<>();
		List<Categoria> categorias=new CategoriaFacade().childrenAplicable(principalSelected);
		categorias.forEach(cat->{categoriaAplicable(cat);});
		
	}
	
	private void categoriaAplicable(Categoria c) {
		//if(c.getCategoriaList().isEmpty()){
			String cods=c.getCodigo()+"-"+c.getNombre();
			CategoriaGasto gasto=new CategoriaGasto();
			gasto.setYear(String.valueOf( Calendar.getInstance().get(Calendar.YEAR)));
			gasto.setCategoria(c);
			categoriasCod.put(cods, gasto);
//		}else{
//			for(Categoria cat:c.getCategoriaList()){
//				
//				categoriaAplicable(cat,cod+"-"+c.getNombre(),p,year);
//			}
//		}
	}

	public List<String> obtenerListaCuentas(String txt){
		
			list=new ArrayList<String>();
			 Set<String> listTemp = categoriasCod.keySet();
			for(String c: listTemp){
				if(c.toUpperCase().contains(txt.toUpperCase())){
					list.add(c);
				}
			}
		
		return list;
	}
	
	public UsuarioSessionMB getSesionMB() {
		return sesionMB;
	}
	public void setSesionMB(UsuarioSessionMB sesionMB) {
		this.sesionMB = sesionMB;
	}

//	public List<CategoriaGasto> getAplicables() {
//		return aplicables;
//	}
//
//	public void setAplicables(List<CategoriaGasto> aplicables) {
//		this.aplicables = aplicables;
//	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public CategoriaGasto obtenerCategoria(String categoriaTxt) {
		return categoriasCod.get(categoriaTxt);
		
	}

	public HashMap<String, CategoriaGasto> getCategoriasCod() {
		return categoriasCod;
	}

	public void setCategoriasCod(HashMap<String, CategoriaGasto> categoriasCod) {
		this.categoriasCod = categoriasCod;
	}

	public String getPrincipalSelected() {
		return principalSelected;
	}

	public void setPrincipalSelected(String principalSelected) {
		this.principalSelected = principalSelected;
	}

	
	
	

}
