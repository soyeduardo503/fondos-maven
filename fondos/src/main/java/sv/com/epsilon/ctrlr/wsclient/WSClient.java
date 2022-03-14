package sv.com.epsilon.ctrlr.wsclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sv.com.epsilon.entities.Cuenta;
import sv.com.epsilon.response.AccionResponse;

public class WSClient<T> {

	private Class<T>  typeClass;
	private static String SERVER="localhost";
	private static String PORT="8181";
	private static String CONTEXT="WSEpsilonUSER";
	private final static String URL_BASE="http://"+SERVER+":"+PORT+"/"+CONTEXT;
	public WSClient(Class<T> cl) {
		this.typeClass=cl;
	}

	public void save(T object) throws Exception {
		WebTarget client = createClient( "/save");
		Response rs = client.request(MediaType.APPLICATION_JSON).post(Entity.entity(object, MediaType.APPLICATION_JSON));
		if(rs.getStatus()!=201) {
			throw new Exception("Error al intentar guardar");
		}
		 ResponseAction<?> resp = (ResponseAction<T>) rs.getEntity();
		 if(resp.getCod()==0) {
			 object=(T)resp.getPersist();
		 }
	}
	
	public void save(List<T> object) throws Exception {
		WebTarget client = createClient( "/save");
		Response rs = client.request(MediaType.APPLICATION_JSON).post(Entity.entity(object, MediaType.APPLICATION_JSON));
		if(rs.getStatus()!=201) {
			throw new Exception("Error al intentar guardar");
		}
		object=(List<T>) rs.getEntity();
	}
	
	public List<T> getAll() {
		 Optional<ArrayList<T>> rsList = Optional.ofNullable(  createClient("/get" ).path("/all").request(MediaType.APPLICATION_JSON).get(new ArrayList<T>().getClass()));
		 if(rsList.isPresent())
			 return  rsList.get();
		 return new ArrayList<>();
	}
	public T getById(int id) {
		 return  createClient("/get").path("/"+id).request(MediaType.APPLICATION_JSON).get(typeClass);
	}
	
	public boolean delete(T object) {
		Response rs = createClient("/delete").request(MediaType.APPLICATION_JSON).post(Entity.entity(object, MediaType.APPLICATION_JSON));
		if(rs.getStatus()!=201) {
			
		}
		ResponseAction<T> respAction = (ResponseAction<T>)rs.getEntity();
		if(respAction.getCod()==0)
			return true;
		else
			return false;
	}
	
	private WebTarget  createClient() {
		Client client = ClientBuilder.newClient();
		return  client.target(URL_BASE+"/"+typeClass.getSimpleName());
	}
	
	private WebTarget  createClient(String endPoint) {
		Client client = ClientBuilder.newClient();
		return  client.target(URL_BASE).path(typeClass.getSimpleName()+"/"+endPoint);
		
	}
	
	public Integer testConnection() {
		Client client = ClientBuilder.newClient();
		return client.target(URL_BASE).path("/startedup").request(MediaType.APPLICATION_JSON).get(AccionResponse.class).getStatus();
		
	}
	public static void main(String[] args) {
		System.out.println(new WSClient<Cuenta>(Cuenta.class).testConnection());
	}
}
