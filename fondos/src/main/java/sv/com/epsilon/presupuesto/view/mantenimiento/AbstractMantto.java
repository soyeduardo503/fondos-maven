/**
 * 
 */
package sv.com.epsilon.presupuesto.view.mantenimiento;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Basic;

import org.primefaces.event.RowEditEvent;

import sv.com.epsilon.util.ExecuteForm;
import sv.com.epsilon.util.Log;
import sv.com.epsilon.util.MessageGrowlContext;

/**
 * @author usuario07
 *
 */
public abstract class  AbstractMantto<K,T> implements Serializable {

	/**
	 * 
	 */
	
	/**
	 * 
	 */
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6239371123106878335L;
	/**
	 * 
	 */
	private List<K> list;
	private T facade;
	private K itemSelected;
	private Class<T> c;
	private Class<K> k;
	private String callMethodfindAll="findAll";
	private String callMethod;
	private String idFormList="formList";
	private String idFormNew="idFormNew";
	private String idTable="idTableData";
	private String wtgDialog="dlgNewObject";
	private String[] formsView=new String[]{idFormList,idFormNew};
	private String[] objectsView=new String[]{idTable};
	private String headerPage="";
	private String headerTable="";
	private String headerDialog="";
	private String messageConfirm="Guardado con exito";
	private String messageError="Ocurrio un error";
	private String idWtgNew="idWgtNew";
	private String idFormDelete="formDefaultDelete";
	private String dialogDelete="defaultDialogDelete";
	private String idFormEdit="idFormEdit";
	private String dialogEdit="defaultEditDialog";
	private List<String> dialogActive=new ArrayList<>();
	private List<String> formActive=new ArrayList<>();
	
	private String token;
	private Integer idEmpresa;
	
	private boolean stateActive=true;
	private String detailNotifySuccess="Proceso completado!!!!";
	private String headerNotifySuccess="Se guardo correctamente";
	public boolean isStateActive() {
		return stateActive;
	}

	public void setStateActive(boolean stateActive) {
		this.stateActive = stateActive;
	}
	
	
	public abstract void reset();
	
	public abstract void update();
	public abstract void preRender();
	public abstract void preDestroy();
	public abstract void defineHeaders();
	public abstract void validateDataValue() throws Exception;
	
	
	public AbstractMantto(Class<K> k,Class<T> c) {
		this.c=c;
		this.k=k;
		createItem();
		defineHeaders();
	
	}
	
	public  void init(String token,Integer idEmpresa) {
		this.idEmpresa=idEmpresa;
		this.token=token;
	}
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	private K createItem() {
		try {
			resetObj();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.itemSelected;
		
	}
	public void callLoad() throws Exception{
		//if(!FacesContext.getCurrentInstance().isPostback())
			load();
	}
	
	public void openNew() {
		try {
			resetObj();
			setDialogActive(this.getWtgDialog());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ExecuteForm().ExecuteUpdate(Arrays.asList(new String[] {this.idFormNew}),createListDialogOpen());
	}
	
	
	
	public void openEdit(K editItem) {
		
		this.setItemSelected(editItem);
		 try {
//			String state=(String) k.getMethod("getAct", new Class[]{}).invoke(editItem, new Object[]{});
//			if("A".equals(state))
//				this.stateActive=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 setDialogActive(dialogEdit);
		new ExecuteForm().ExecuteUpdate(idFormNew, "PF('"+dialogEdit+"').show();");
	}
	
	public void openEdit() {
		
		//this.setItemSelected(editItem);
		 try {
			String state=(String) k.getMethod("getAct", new Class[]{}).invoke(itemSelected, new Object[]{});
			if("A".equals(state))
				this.stateActive=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 setDialogActive(dialogEdit);
		new ExecuteForm().ExecuteUpdate(idFormEdit, "PF('"+dialogEdit+"').show();");
	}
	
	
	public void updateDialogNew(){
		setDialogActive(wtgDialog);
		new ExecuteForm().ExecuteUpdate(idFormNew, "PF('"+wtgDialog+"').show();");
	}
	
	public void onEditRow(RowEditEvent obj){
		itemSelected=(K) obj.getObject();
		try {
			edit();
			new MessageGrowlContext().send("Datos Actualizados", "Datos Actualizados");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new MessageGrowlContext().sendError("Error al actualizar", "Error al actualizar", e);
		}
	}
	
	public void updateDialogClose(){
		try {
			this.resetObj();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		new ExecuteForm().ExecuteUpdate(Arrays.asList( new String[] {idFormList}),createListDialogClose());
	}
	
	
	
	public void load() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		initFacade();
		callMethod=callMethodfindAll;
		list=(List<K>) facade.getClass().getMethod(callMethodfindAll, new Class[]{}).invoke(facade, new Object[]{});
	}
	protected void initFacade() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		if(facade==null)
			facade=c.newInstance();
		facade.getClass().getMethod("init", new Class[]{String.class,Integer.class}).invoke(facade, new Object[]{token,idEmpresa});
	}
	
	public void saveWithoutclose() throws Exception{
		validateDataValue();
		initFacade();
		callMethod="persis";
		//Method[] m = facade.getClass().getMethods();
//		System.out.println("facade "+facade.getClass());
//		for (Method method : m) {
//			System.out.println("Method:"+method.toString());
//			
//		}
//		System.out.println(m);
		facade.getClass().getMethod(callMethod, new Class[]{Object.class}).invoke(facade, new Object[]{itemSelected});
		if(list!=null)
			this.list.add(itemSelected);
	
	}
	
	public void save() throws Exception{
		validateDataValue();
		initFacade();
		k.getMethod("setAct", new Class[]{String.class}).invoke(itemSelected, new Object[]{stateActive?"A":"I"});
		 
		callMethod="save";
		//Method[] m = facade.getClass().getMethods();
//		System.out.println("facade "+facade.getClass());
//		for (Method method : m) {
//			System.out.println("Method:"+method.toString());
//			
//		}
//		System.out.println(m);
		facade.getClass().getMethod(callMethod, new Class[]{Object.class}).invoke(facade, new Object[]{itemSelected});
		if(list!=null)
			this.list.add(itemSelected);
		updateDialogClose();
	}
	
	public void edit() throws Exception{
		initFacade();
		callMethod="save";
		
//		System.out.println("facade "+facade.getClass());
//		for (Method method : m) {
//			System.out.println("Method:"+method.toString());
//			
//		}
//		System.out.println(m);
		facade.getClass().getMethod(callMethod, new Class[]{Object.class}).invoke(facade, new Object[]{itemSelected});
		new ExecuteForm().Update(idFormList+":"+idTable);
	}
	
	public void showSuccess() {
		new MessageGrowlContext().send(headerNotifySuccess,detailNotifySuccess);
	}
	
	public void cancel() throws Exception{
		
		updateDialogClose();
	}
	
	public  void resetObj() throws InstantiationException, IllegalAccessException{
		
		setItemSelected( this.k.newInstance());
		setDefaultValue();
	}
	
	public K oneNewObject() throws InstantiationException, IllegalAccessException{
		return ( this.k.newInstance());
	}
	
	public void remove() throws Exception{
		initFacade();
		callMethod="remove";
		facade.getClass().getMethod(callMethod, new Class[]{Object.class}).invoke(facade, new Object[]{itemSelected});
		this.list.remove(itemSelected);
		updateDialogClose();
	}

	public void openDeleteDialog() {
		Log.info(this.itemSelected);
		this.setDialogActive(dialogDelete);
		new ExecuteForm().mostrarConfirmacion(this.idFormDelete, "PF('"+this.dialogDelete+"').show();");
	}
	
	
	public List<K> getList() {
		return list;
	}

	public void setList(List<K> list) {
		this.list = list;
	}

	public T getFacade() {
		return facade;
	}

	public void setFacade(T facade) {
		this.facade = facade;
	}

	public K getItemSelected() {
		if(itemSelected==null)
			try {
				resetObj();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		return itemSelected;
	}
	
	 protected abstract void setValueMod();
	 protected abstract void setDefaultValue();

	public void setItemSelected(K itemSelected) {
		this.itemSelected = itemSelected;
	}
	public String getIdFormList() {
		return idFormList;
	}
	public void setIdFormList(String idFormList) {
		this.idFormList = idFormList;
	}
	public String getIdFormNew() {
		return idFormNew;
	}
	public void setIdFormNew(String idFormNew) {
		this.idFormNew = idFormNew;
	}
	public String[] getFormsView() {
		return formsView;
	}
	public void setFormsView(String[] formsView) {
		this.formsView = formsView;
	}
	
	
	public String getWtgDialog() {
		return wtgDialog;
	}
	public void setWtgDialog(String wtgDialog) {
		this.wtgDialog = wtgDialog;
	}
	
	
	public String getHeaderPage() {
		return headerPage;
	}

	public void setHeaderPage(String headerPage) {
		this.headerPage = headerPage;
	}

	public String getHeaderTable() {
		return headerTable;
	}

	public void setHeaderTable(String headerTable) {
		this.headerTable = headerTable;
	}

	public String getHeaderDialog() {
		return headerDialog;
	}

	public void setHeaderDialog(String headerDialog) {
		this.headerDialog = headerDialog;
	}

	public String getMessageConfirm() {
		return messageConfirm;
	}

	public void setMessageConfirm(String messageConfirm) {
		this.messageConfirm = messageConfirm;
	}
	


	public String getNameCompleteWtg(){
		return ":"+this.idFormNew+":"+idWtgNew;
	}
	

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public String getIdWtgNew() {
		return idWtgNew;
	}

	public void setIdWtgNew(String idWtgNew) {
		this.idWtgNew = idWtgNew;
	}

	public String getIdTable() {
		return idTable;
	}

	public void setIdTable(String idTable) {
		this.idTable = idTable;
	}

	public String[] getObjectsView() {
		return objectsView;
	}

	public void setObjectsView(String[] objectsView) {
		this.objectsView = objectsView;
	}
	
	public List<String> valuesDiffNull(){
		List<String> valuesNull=new ArrayList<String>();
		List<Field> camposValidar = Arrays.asList(k.getFields());
		
		
		
		return valuesNull;
	}
	
//	public String validateNotNull(Field f) {
//		
//		
//		   Basic ann = f.getAnnotation(javax.persistence.Basic.class);
//		   if(ann!=null&& ann.optional()==false) {
//			   
//		   }
//		//facade.getClass().getMethod(callMethod, new Class[]{Object.class}).invoke(facade, new Object[]{itemSelected})
//	}
	
	
	public void destroy() {
		this.c=null;
		this.callMethod=null;
		this.callMethodfindAll=null;
//		
//		try {
//			//facade.getClass().getMethod("TrasactionCommit", new Class[]{}).invoke(facade, new Object[]{});
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		this.facade=null;
		this.formsView=null;
		this.itemSelected=null;
		this.idFormList=null;
		this.idFormNew=null;
		this.list=null;
		this.wtgDialog=null;
		this.formActive=null;
		this.dialogActive=null;
		
		
	}

	public String getIdFormDelete() {
		return idFormDelete;
	}

	public void setIdFormDelete(String idFormDelete) {
		this.idFormDelete = idFormDelete;
	}

	public String getDialogDelete() {
		return dialogDelete;
	}

	public void setDialogDelete(String dialogDelete) {
		this.dialogDelete = dialogDelete;
	}

	public String getIdFormEdit() {
		return idFormEdit;
	}

	public void setIdFormEdit(String idFormEdit) {
		this.idFormEdit = idFormEdit;
	}

	public String getDialogEdit() {
		return dialogEdit;
	}

	public void setDialogEdit(String dialogEdit) {
		this.dialogEdit = dialogEdit;
	}

	
	
	
	private List<String> createListDialogClose() {
		List<String> pfAction=new ArrayList<String>();
		dialogActive.forEach(dlg->pfAction.add("PF('"+dlg+"').hide();")); 
		 
		return pfAction;
	}
	
	private List<String> createListDialogOpen() {
		List<String> pfAction=new ArrayList<String>();
		dialogActive.forEach(dlg->pfAction.add("PF('"+dlg+"').show();")); 
		 
		return pfAction;
	}
	
	private List<String> createListFormUpdate() {
		return formActive;
	}
	
	private void setFormActive(String forms) {
		this.formActive=Arrays.asList(new String[] {forms});
	}
	private void setFormActive(String[] forms) {
		this.formActive=Arrays.asList(forms);
	}
	private void setDialogActive(String dlgs) {
		this.dialogActive=Arrays.asList(new String[] {dlgs});
	}
	private void setDialogActive(String[] dlgs) {
		this.dialogActive=Arrays.asList(dlgs);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
