package sv.com.epsilon.ctrlr.wsclient;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import sv.com.epsilon.entities.Categoria;
import sv.com.epsilon.response.AccionResponse;
import sv.com.epsilon.response.NumberResponse;
import sv.com.epsilon.util.Log;

public class WSClient<T> {

	private Class<T>  typeClass;
	private static String SERVER="localhost";
	private static String PORT="8000";
	private static String CONTEXT="WSFondos";
	private final static String URL_BASE="http://"+SERVER+":"+PORT+"/"+CONTEXT;
	private String token="";
	private Integer idEmpresa=1;
	
	
	public WSClient(Class<T> cl) {
		this.typeClass=cl;
	}
	
	

	public T save(T object) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(object);
		//request.getHeaders().add(, CONTEXT);
		
		Optional<?> resp = Optional.ofNullable( restTemplate.postForObject(url("/"),request,typeClass));
		if(!resp.isPresent())
			throw new Exception("Error when save the object");
		else
			object=(T) resp.get();
		return object;
	}
	
	public boolean update(Object object,String endpoint) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(object);
		//request.getHeaders().add(, CONTEXT);
		
		Optional<AccionResponse> resp = Optional.ofNullable( restTemplate.postForObject(url(endpoint),request,AccionResponse.class));
		if(!resp.isPresent())
			throw new Exception("Error when update the object");
		else
			return  resp.get().getStatus()==0;
	}
	
	public List<T> findAllActive(){
		return getAct();
	}
	
	
	public T findById(Integer id) {
		Optional<T> ob = find("/id/"+id);
		return ob.isPresent()? ob.get(): newObject() ;
	}
	
	private T newObject() {
		try {
			return typeClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Optional<T> get(String endpoint) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T> responseEntity = 
				  restTemplate.exchange(
				    url(endpoint),
				    HttpMethod.GET,
				    null,
				    typeClass
				  );
		if(200!=responseEntity.getStatusCodeValue())
			return Optional.empty();
		return  Optional.ofNullable( responseEntity.getBody());
	}
	public Optional<T> find(String endpoint) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T> responseEntity = 
				  restTemplate.exchange(
				    url(endpoint),
				    HttpMethod.GET,
				    null,
				    typeClass
				  );
		if(200!=responseEntity.getStatusCodeValue())
			return Optional.empty();
		return  Optional.ofNullable( responseEntity.getBody());
	}
	
	public boolean update(Object object) throws Exception {
		return update(object,url("/update"));
		
	}
	
	public void save(List<T> object) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(object);
		Optional<?> resp = Optional.ofNullable( restTemplate.postForObject(url("s/"),request,typeClass));
		if(!resp.isPresent()) {
			throw new Exception("Error al intentar guardar list");
		}
		
	
	}
	
	public T search(T object) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<?> request = new HttpEntity<>(object);
		Optional<T> resp = Optional.ofNullable( restTemplate.postForObject(url("/search"),request,typeClass));
		
		if(!resp.isPresent()) {
			throw new Exception("Error al intentar guardar list");
		}
		return resp.get();
		
	
	}
	
	public Optional<AccionResponse> process(String endpoint)  {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AccionResponse> responseEntity = 
				  restTemplate.exchange(
				    url(endpoint),
				    HttpMethod.GET,
				    null,
				    AccionResponse.class
				  );
		if(200!=responseEntity.getStatusCodeValue())
			return Optional.empty();
		return  Optional.ofNullable(responseEntity.getBody());
	}
	
	public List<T> getList(String endpoint)  {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = 
				  restTemplate.exchange(
				    url(endpoint),
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<String>() {}
				  );//new TypeReference<
		if(200!=responseEntity.getStatusCodeValue())
			return new ArrayList<T>();
		try {
			return  deserialize(responseEntity.getBody());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<T>();
		}
	}
	
	public List<T>  deserialize(String json ) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
	    CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, typeClass);
	    List<T> ts = mapper.readValue(json, listType);
	    return ts;   
	}
	
	public Integer count(Integer id) {
		return count(id,"/count");
	}
	
	public List<T> findAll(){
		return getAll();
	}
	public NumberResponse getNumber(String endpoint) {
		RestTemplate restTemplate = new RestTemplate();
		NumberResponse object =  restTemplate.getForObject(url(endpoint),NumberResponse.class);
		return object;
	}
	public Integer count(int id,String endpoint) {
		RestTemplate restTemplate = new RestTemplate();
		NumberResponse object =  restTemplate.getForObject(url(endpoint+"/"+id),NumberResponse.class);
		if(object.getCod()!=0)
			return 0;
		return Integer.valueOf(String.valueOf( object.getValue()));
	}
	
	public BigDecimal mount(String endpoint) {
		RestTemplate restTemplate = new RestTemplate();
		NumberResponse object =  restTemplate.getForObject(url(endpoint),NumberResponse.class);
		if(object.getCod()!=0)
			return new BigDecimal(0);
		return new BigDecimal(String.valueOf( object.getValue()));
	}
	
	public List<T> getAct()  {
		return getList("/act/");
		
	}
	
	public List<T> getAll()   {
		return getList("/all/");
	}
	
	public List<T> list(String endpoint){
		return getList(endpoint);
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
	
	
	
	public String url(String endPoint) {
		Log.info(URL_BASE+"/"+this.typeClass.getSimpleName().toLowerCase()+endPoint);
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
	public void init(String token, Integer idEmpresa) {
		this.token=token;
		this.idEmpresa=idEmpresa;
	}
}
