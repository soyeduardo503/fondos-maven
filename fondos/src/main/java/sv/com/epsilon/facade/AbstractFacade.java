/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.GsonBuilder;

import sv.com.epsilon.util.HibernateUtil;
import sv.com.epsilon.util.Log;
/**
 *
 * @author CEMartinez
 */
public abstract class AbstractFacade<T> {

	private Class<T> entityClass;
	protected  Session session ;
	private String token;
	private Integer idEmpresa=1;
	
	private String URLBASE="http://localhost:8282/WSFondos";

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;

	}

	public void init(String token,Integer idEmpresa) {
		this.token=token;
		this.idEmpresa=idEmpresa;
	}
	

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	//	public void open(){
//		getSession();
//	}
	protected  void getSession() {
		 try {
			//session.createQuery("SELECT SYSDATE()");
			 session=HibernateUtil.getSessionFactory().openSession();
			// Log.info(session);
			 Log.info("---------------------------------------");
//    		 if(session!=null&&session.isOpen()&&session.isConnected()) {
//    			 session.createQuery("SELECT SYSDATE()").uniqueResult();
//    			 return session;
//    		 }
//    			
//    		else
//    			 session = HibernateUtil.getSessionFactory().getCurrentSession();
    	    } catch (Exception ex) {
    	        ex.printStackTrace();
    	    	session = HibernateUtil.getSessionFactory().openSession();
    	        
    	    }
        //return session;
	}
	
	
//	public Session getSessionActualy() {
//		//getSession().flush();
//		getSession().close();
//		session=null;
//		return getSession();
//	}

//	public WSClientCtrlr getWs() {
//		return ws;
//	}
//
//
//
//	public void setWs(WSClientCtrlr ws) {
//		this.ws = ws;
//	}



	public void create(T entity) {
//		try {
//			String jsonResponse= sendRequest(entity, "POST","/"+entityClass.getSimpleName()+"/", token);
//			//Type type = new TypeToken<T>(){}.getType();
//			entity= new Gson().fromJson(jsonResponse,this.entityClass);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		getSession();
		session.beginTransaction();
		try {
			session.save(entity);
			session.getTransaction().commit();
			
			//getSession().close();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		close();
		
	}
	

	public void persis(T entity) {
//		try {
//			String jsonResponse= sendRequest(entity, "POST","/"+entityClass.getSimpleName()+"/", token);
//			Type type = new TypeToken<T>(){}.getType();
//			entity= new Gson().fromJson(jsonResponse,type);
//			//entity =(T) 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		getSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
			
			//getSession().close();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		close();
	}
//	public void reload(T entity) {
//		//getSession().flush();
//		//getSession().close();
//		getSession();
//		session.refresh(entity);
//		close();
//	}
	
	public void createPersist(T entity) {
		create(entity);
		
//		getSession();
//		session.beginTransaction();
//		try {
//			session.persist(entity);
//			
//			session.getTransaction().commit();
//		//	getSession().close();
//		} catch (Exception e) {
//			Log.info("Error al insertar "+entity.getClass().getSimpleName()+" valor->"+entity);
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			//getSession().flush();
//		}
//		close();
	}
	
	public void createMerge(T entity) throws Exception {
		create(entity);
//		getSession();
//		session.beginTransaction();
//		try {
//			session.merge(entity);
//			
//			session.getTransaction().commit();
//			//getSession().close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		close();
	}
	
	public void saveList(List<T> list) {
		list.forEach(saveObject->{
			persis(saveObject);
			
		});
	}

	public void edit(T entity) {
		persis(entity);
//		getSession();
//		session.beginTransaction();
//		try {
//			session.update(entity);
//			session.getTransaction().commit();
//			//getSession().close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			//getSession().flush();
//		}
//		close();
	}

	public void remove(T entity) throws Exception {
//		 String jsonResponse=sendRequest(entity, "POST","/"+entityClass.getSimpleName()+"/delete/", token);
//		AccionResponse resp = (AccionResponse)	new Gson().fromJson(jsonResponse,AccionResponse.class);
//		if(resp.getStatus()!=1)
//			throw new Exception("Error al eliminar : cod->"+resp.getStatus());
		getSession();
		session.beginTransaction();
		try {
			session.delete(entity);
			session.getTransaction().commit();
			//getSession().close();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		
		}
		close();
	}

	
//	public void commitRemove() {
//		try {
//			getSession();
//			session.getTransaction().commit();
//			//close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			//getSession().flush();
//		}
//	}
	public T findById(Integer id) {
//		 try {
//			String jsonResponse= sendRequest(obj, "POST","/"+entityClass.getSimpleName()+"/id/", token);
//			
//			return new Gson().fromJson(jsonResponse,entityClass);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null; 
//		}
		getSession();
		//Transaction tx =session.beginTransaction();
		try {
			return (T) session.get(entityClass, id);
			
		}finally {
			close();
			
		}
		
	}
	
//	public T findById(Object id) {
//		
//		getSession();
//		//Transaction tx =session.beginTransaction();
//		return findById(Integer.parseInt(String.valueOf(id)));
//		
//	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
//		try {
//			String jsonResponse= sendRequest(null, "GET","/"+entityClass.getSimpleName()+"/all/"+idEmpresa, token);
//			Type listType = new TypeToken<ArrayList<T>>(){}.getType();
//			return  (List<T>) new Gson().fromJson(jsonResponse, listType); 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return new ArrayList<T>();
//		}
		List<T> books = null ;
//		Transaction tx =
		getSession();
		//session.
		try {
			Transaction tx = session.beginTransaction();
			books = (List<T>) session.createQuery("from " + entityClass.getName()).list();
			tx.commit();
			session.evict(books);
			return books;
		}finally {
			close();
		}
	
	}
	
	
	 public List<T> findAllActive() {
//		 try {
//			 	String jsonResponse= sendRequest(null, "GET","/"+entityClass.getSimpleName()+"/act/"+idEmpresa, token);
//			 	Type listType = new TypeToken<ArrayList<T>>(){}.getType();
//				return  (List<T>) new Gson().fromJson(jsonResponse, listType); 
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return new ArrayList<T>();
//			}	
		 
		 getSession();
	    	try {
		    	String qName=entityClass.getSimpleName()+".findByAct";
		         Query t = session.getNamedQuery(qName);
		         t.setParameter("act", "A");
		         t.setParameter("idEmpresa",1);
		        
		        
		        List<T>list= (List<T>)t.list();
//		        List<T>listFill=new ArrayList<T>();
//		        Field[] fields = entityClass.getDeclaredFields();
//		        for(Field f:fields)
//		        if(f.getName().equalsIgnoreCase("act")){
//		        	
//		        	listFill=list;
//		        }
		        return list;
	    	}finally {
	    		close();
	    	}
	    }
	 public List<T> findAllActiveByEmpresa() {
		 return findAllActive();
//			getSession();
//	    	try {
//		    	
//		         Query t = session.createQuery("from " + entityClass.getName()+" c WHERE  c.act=:act and c.idEmpresa=:idEmpresa ").setParameter("act", "A")
//		        		 .setParameter("idEmpresa", idEmpresa);
//		        
//		         
//		        
//		       return  (List<T>)t.list();
//		       
//	    	}finally {
//	    		close();
//	    	}
		}
	
	public void close(){
		
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}
	
	public String sendRequest(String method, String endPoint, String token) throws Exception {
		// TODO Auto-generated method stub
		return sendRequest(null,method,endPoint , token);
	}
	
	public String sendRequest(Object peticion,String method,String endPoint,String token) throws Exception {
		String peticionjson=null;
		if(peticion!=null)
			peticionjson=(new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(peticion));
		String urlWS=this.URLBASE+endPoint.toLowerCase();
//		try {
//		if(PropertiesUtil.value("debugOnbase")!=null&&PropertiesUtil.value("debugOnbase").equals("1"))
			Log.info("Peticion -> "+peticionjson);
			
//		}catch (Exception e) {
//			Log.error(e, "debug onbase");
//		}
		
		Log.info(urlWS);
		
		StringBuilder sb=new StringBuilder();
		URL url = new URL(urlWS);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type",  MediaType.APPLICATION_JSON);
		conn.setRequestProperty("Authorization", token);
		conn.setConnectTimeout(50000);
		conn.setReadTimeout(180000);
		if(peticion!=null)
		{
			OutputStream os = conn.getOutputStream();
			os.write(peticionjson.getBytes());
			os.flush();
		}
		Log.info("Status OK?"+conn.getResponseCode());
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		
		Log.info("Leyendo respuesta .... \n");
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}

		conn.disconnect();


		
		return sb.toString();
	}
	
	
//		if(session!=null){
//			session.flush();
//			session.clear();
//			 session.close();
//			session=null;
//		}
//	}
}
