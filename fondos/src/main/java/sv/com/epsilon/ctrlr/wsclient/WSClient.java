package sv.com.epsilon.ctrlr.wsclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.response.AccionResponse;
import sv.com.epsilon.response.NumberResponse;

public class WSClient<T> {

	private Class<T>  typeClass;
	private static String SERVER="localhost";
	private static String PORT="8000";
	private static String CONTEXT="WSFondos";
	private final static String URL_BASE="http://"+SERVER+":"+PORT+"/"+CONTEXT;
	
	
	public WSClient(Class<T> cl) {
		this.typeClass=cl;
	}

	public void save(T object) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(object);
		//request.getHeaders().add(, CONTEXT);
		
		Optional<?> resp = Optional.ofNullable( restTemplate.postForObject(url("/"),request,typeClass));
		if(!resp.isPresent())
			throw new Exception("Error when save the object");
		else
			object=(T) resp.get();
	}
	
	public void save(List<T> object) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(object);
		Optional<?> resp = Optional.ofNullable( restTemplate.postForObject(url("s/"),request,typeClass));
		if(!resp.isPresent()) {
			throw new Exception("Error al intentar guardar list");
		}
		
	
	}
	
	public List<T> getList(String endpoint)  {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<T>> responseEntity = 
				  restTemplate.exchange(
				    url(endpoint),
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<List<T>>() {}
				  );
		if(200!=responseEntity.getStatusCodeValue())
			return new ArrayList<T>();
		return  responseEntity.getBody();
	}
	
	public Integer count(Integer id) {
		return count(id,"/count");
	}
	
	public List<T> findAll(){
		return this.getAll();
	}
	
	public Integer count(int id,String endpoint) {
		RestTemplate restTemplate = new RestTemplate();
		NumberResponse object =  restTemplate.getForObject(url(endpoint+"/"+id),NumberResponse.class);
		if(object.getCod()!=0)
			return 0;
		return Integer.valueOf(String.valueOf( object.getValue()));
	}
	
	public List<T> getAct()  throws Exception {
		return getList("/act/");
		
	}
	
	public List<T> getAll()  throws Exception {
		return getList("/all/");
	}
	public T getById(int id) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		Optional<T> object = Optional.ofNullable( restTemplate.getForObject(url("/id/"+id),typeClass));
		if(!object.isPresent())
			throw new Exception("Error al obtener objeto by id");
		return object.get();
	}
	
	public boolean delete(T delete) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(delete);
		Optional<AccionResponse> resp = Optional.ofNullable( restTemplate.postForObject(url("/delete"),request,AccionResponse.class));
		if(!resp.isPresent())
			throw new Exception("Error when save the object");
		else
		if(resp.get().getStatus()==0)
			return true;
		else
			return false;
	}
	
	
	
	private String url(String endPoint) {
		return URL_BASE+"/"+this.typeClass.getSimpleName().toLowerCase()+endPoint;
	}
	
	public Integer testConnection() {
		RestTemplate restTemplate = new RestTemplate();
		AccionResponse object = restTemplate.getForObject(URL_BASE+"/startedup",AccionResponse.class);
		
		return object.getStatus();
		
	}
	public static void main(String[] args) throws Exception {
		
		List<Categoria> list = new WSClient<>(Categoria.class).getAll();
		System.out.println(list.size());
	}
}
